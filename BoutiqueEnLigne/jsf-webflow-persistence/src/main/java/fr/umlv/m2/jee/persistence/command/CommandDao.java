package fr.umlv.m2.jee.persistence.command;

import fr.umlv.m2.jee.database.cassandra.IDao;

public interface CommandDao extends IDao<Long, Command> {
  
}
