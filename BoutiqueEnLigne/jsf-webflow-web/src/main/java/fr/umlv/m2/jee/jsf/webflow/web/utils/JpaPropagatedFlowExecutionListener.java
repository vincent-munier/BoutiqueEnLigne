package fr.umlv.m2.jee.jsf.webflow.web.utils;

import org.springframework.orm.jpa.EntityManagerHolder;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.definition.FlowDefinition;
import org.springframework.webflow.execution.FlowExecutionException;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/*
 * A {@link FlowExecutionListener} that implements the Flow Managed Persistence Context (FMPC) pattern using the
 * standard Java Persistence API (JPA).
 * <p>
 * This implementation uses standard JPA APIs. The general pattern is as follows:
 * <ul>
 * <li>When a flow execution starts, create a new JPA persistence context and bind it to flow scope under the name
 * {@link #PERSISTENCE_CONTEXT_ATTRIBUTE}.
 * <li>Before processing a flow execution request, expose the flow-scoped persistence context as the "current"
 * persistence context for the current thread.
 * <li>When an existing flow pauses, unbind the persistence context from the current thread.
 * <li>When an existing flow ends, commit the changes made to the persistence context in a transaction if the ending
 * state is a commit state. Then, unbind the context and close it.
 * </ul>
 *
 * The general data access pattern implemented here is:
 * <ul>
 * <li>Create a new persistence context when a new flow execution with the 'persistenceContext' attribute starts
 * <li>Load some objects into this persistence context
 * <li>Perform edits to those objects over a series of requests into the flow
 * <li>On successful conversation completion, commit and flush those edits to the database, applying a version check if
 * necessary.
 * </ul>
 *
 * <p>
 * Note: All data access except for the final commit will, by default, be non-transactional. However, a flow may call
 * into a transactional service layer to fetch objects during the conversation in the context of a read-only system
 * transaction if the underlying JPA Transaction Manager supports this. Spring's JPA TransactionManager does support
 * this when working with a Hibernate JPA provider, for example. In that case, Spring will handle setting the FlushMode
 * to MANUAL to ensure any in-progress changes to managed persistent entities are not flushed, while reads of new
 * objects occur transactionally.
 * <p>
 * Care should be taken to prevent premature commits of flow data while the flow is in progress. You would generally not
 * want intermediate flushing to happen, as the nature of a flow implies a transient, isolated resource that can be
 * canceled before it ends. Generally, the only time a read-write transaction should be started is upon successful
 * completion of the flow, triggered by reaching a 'commit' end state.
 *
 * @author Keith Donald
 * @author Juergen Hoeller
 */
public class JpaPropagatedFlowExecutionListener extends FlowExecutionListenerAdapter {

	/**
	 * The name of the attribute the flow {@link EntityManager persistence context} is indexed under.
	 */
	public static final String PERSISTENCE_CONTEXT_ATTRIBUTE = "persistenceContext";

	private EntityManagerFactory entityManagerFactory;

	private TransactionTemplate transactionTemplate;

	/**
	 * Create a new JPA flow execution listener using given JPA Entity Manager factory.
	 *
	 * @param entityManagerFactory
	 *            the entity manager factory to use
	 */
	public JpaPropagatedFlowExecutionListener(EntityManagerFactory entityManagerFactory, PlatformTransactionManager transactionManager) {
		this.entityManagerFactory = entityManagerFactory;
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public void sessionStarting(RequestContext context, FlowSession session, MutableAttributeMap input) {
		if (isPersistenceContext(session.getDefinition())) {
			EntityManager em = null;
			if (session.isRoot() || !isPersistenceContext(session.getParent().getDefinition())) {
				// either root-flow or sub-flow whose parent flow has not declared a persistence context.
				em = entityManagerFactory.createEntityManager();
				bind(em);
			} else {
				// sub-flow whose parent flow has declared a persistence context.
				em = getEntityManager(session.getParent());
			}

			session.getScope().put(PERSISTENCE_CONTEXT_ATTRIBUTE, em);
		}
	}

	public void paused(RequestContext context) {
		if (isPersistenceContext(context.getActiveFlow())) {
			unbind(getEntityManager(context.getFlowExecutionContext().getActiveSession()));
		}
	}

	public void resuming(RequestContext context) {
		if (isPersistenceContext(context.getActiveFlow())) {
			bind(getEntityManager(context.getFlowExecutionContext().getActiveSession()));
		}
	}

	public void sessionEnding(RequestContext context, FlowSession session, String outcome, MutableAttributeMap output) {
		if (isPersistenceContext(session.getDefinition())) {
			final EntityManager em = getEntityManager(session);
			Boolean commitStatus = session.getState().getAttributes().getBoolean("commit");
			if (Boolean.TRUE.equals(commitStatus)) {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) {
						em.joinTransaction();
					}
				});
			}

			if (session.isRoot() || !isPersistenceContext(session.getParent().getDefinition())) {
				unbind(em);
				em.close();
			}
		}
	}

	public void exceptionThrown(RequestContext context, FlowExecutionException exception) {
		if (context.getFlowExecutionContext().isActive()) {
			if (isPersistenceContext(context.getActiveFlow())) {
				unbind(getEntityManager(context.getFlowExecutionContext().getActiveSession()));
			}
		}
	}

	// internal helpers

	private boolean isPersistenceContext(FlowDefinition flow) {
		return flow.getAttributes().contains(PERSISTENCE_CONTEXT_ATTRIBUTE);
	}

	private EntityManager getEntityManager(FlowSession session) {
		return (EntityManager) session.getScope().get(PERSISTENCE_CONTEXT_ATTRIBUTE);
	}

	private void bind(EntityManager em) {
		TransactionSynchronizationManager.bindResource(entityManagerFactory, new EntityManagerHolder(em));
	}

	private void unbind(EntityManager em) {
		if (TransactionSynchronizationManager.hasResource(entityManagerFactory)) {
			TransactionSynchronizationManager.unbindResource(entityManagerFactory);
		}
	}
}
