package fr.umlv.m2.jee.jsf.webflow.service.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.umlv.m2.jee.jsf.webflow.service.address.IDisplayAddressService;
import fr.umlv.m2.jee.persistence.address.Address;

@Service("defaultDisplayAddressService")
public class DefaultDisplayAddressService implements IDisplayAddressService {
  private List<Address> addresses = new ArrayList<Address>();

  {
    Address adr1 = new Address(0, "94", "chemin des roses", "Marseille",
        "13500");
    addresses.add(adr1);

    Address adr2 = new Address(1, "74", "rue des Peupliers",
        "Villier-sur-Marne", "94200");
    addresses.add(adr2);
  }

  @Override
  public List<Address> getAddressesByClientId(long clientId) {
    // la variable d'instance 'addresses' et init() seront supprimés.
    // on devra faire une requête pour récupèrer la liste des adresses par id de
    // client.
    return addresses;
  }

  public void add(Address adr) {
    if (addresses.contains(adr))
      return;
    addresses.add(adr);
  }

}
