package fr.umlv.m2.jee.persistence.address;

import java.io.Serializable;

public class Address implements Serializable {
  private static final long serialVersionUID = -3670236004668771220L;
  long id;
  // all string because we will not do any arithmetic computations on them
  String num;
  String street;
  String city;
  String postalCode;
  String country;

  public Address() {
  }

  public Address(long id, String num, String street, String city,
      String postalCode, String country) {
    this.id = id;
    this.num = num;
    this.street = street;
    this.city = city;
    this.postalCode = postalCode;
    this.country = country;
  }

  public Address(long id, String num, String street, String city,
      String postalCode) {
    this.id = id;
    this.num = num;
    this.street = street;
    this.city = city;
    this.postalCode = postalCode;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "Address [id=" + id + ", num=" + num + ", street=" + street
        + ", city=" + city + ", postalCode=" + postalCode + ", country="
        + country + "]";
  }

  public String formattedString() {
    return num + " " + street + " \n" + postalCode + " " + city + " " + country;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((country == null) ? 0 : country.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((num == null) ? 0 : num.hashCode());
    result = prime * result
        + ((postalCode == null) ? 0 : postalCode.hashCode());
    result = prime * result + ((street == null) ? 0 : street.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Address other = (Address) obj;
    if (city == null) {
      if (other.city != null)
        return false;
    } else if (!city.equals(other.city))
      return false;
    if (country == null) {
      if (other.country != null)
        return false;
    } else if (!country.equals(other.country))
      return false;
    if (id != other.id)
      return false;
    if (num == null) {
      if (other.num != null)
        return false;
    } else if (!num.equals(other.num))
      return false;
    if (postalCode == null) {
      if (other.postalCode != null)
        return false;
    } else if (!postalCode.equals(other.postalCode))
      return false;
    if (street == null) {
      if (other.street != null)
        return false;
    } else if (!street.equals(other.street))
      return false;
    return true;
  }

}
