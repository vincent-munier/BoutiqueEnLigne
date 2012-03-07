package fr.umlv.m2.jee.jsf.webflow.service.address;

import java.util.List;

import fr.umlv.m2.jee.persistence.address.Address;

public interface IDisplayAddressService {

  /**
   * Get all addresses in base for a client id
   * 
   * @param clientId
   *          the client id
   * @return a list of addresses
   */
  List<Address> getAddressesByClientId(long clientId);
}
