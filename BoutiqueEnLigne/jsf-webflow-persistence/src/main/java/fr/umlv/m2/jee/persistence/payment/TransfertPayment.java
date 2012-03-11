package fr.umlv.m2.jee.persistence.payment;

import java.io.Serializable;

public class TransfertPayment implements Serializable {
  private static final long serialVersionUID = -3626770050507611437L;
  long id;
  String accountNumber;
  String sortCode;
  String agencyCode;

  public TransfertPayment(long id, String accountNumber, String sortCode,
      String agencyCode) {
    this.id = id;
    this.accountNumber = accountNumber;
    this.sortCode = sortCode;
    this.agencyCode = agencyCode;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getSortCode() {
    return sortCode;
  }

  public void setSortCode(String sortCode) {
    this.sortCode = sortCode;
  }

  public String getAgencyCode() {
    return agencyCode;
  }

  public void setAgencyCode(String agencyCode) {
    this.agencyCode = agencyCode;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((accountNumber == null) ? 0 : accountNumber.hashCode());
    result = prime * result
        + ((agencyCode == null) ? 0 : agencyCode.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((sortCode == null) ? 0 : sortCode.hashCode());
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
    TransfertPayment other = (TransfertPayment) obj;
    if (accountNumber == null) {
      if (other.accountNumber != null)
        return false;
    } else if (!accountNumber.equals(other.accountNumber))
      return false;
    if (agencyCode == null) {
      if (other.agencyCode != null)
        return false;
    } else if (!agencyCode.equals(other.agencyCode))
      return false;
    if (id != other.id)
      return false;
    if (sortCode == null) {
      if (other.sortCode != null)
        return false;
    } else if (!sortCode.equals(other.sortCode))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "TransfertPayment [id=" + id + ", accountNumber=" + accountNumber
        + ", sortCode=" + sortCode + ", agencyCode=" + agencyCode + "]";
  }

}
