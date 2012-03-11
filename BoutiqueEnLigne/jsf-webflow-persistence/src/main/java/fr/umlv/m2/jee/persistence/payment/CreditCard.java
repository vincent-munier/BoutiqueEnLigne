package fr.umlv.m2.jee.persistence.payment;

import java.io.Serializable;

import org.joda.time.LocalDate;

public class CreditCard implements Serializable {
  private static final long serialVersionUID = 2583776150150880489L;
  String number;
  LocalDate expirationDate;
  String ownerName;
  String crypto;
  CardType cardType;

  protected enum CardType {
    VISA("Visa"), CB("Carte Bleu"), MC("Master Card");
    protected String label;

    CardType(String pLabel) {
      this.label = pLabel;
    }

    public String getLabel() {
      return this.label;
    }
  }

  public CreditCard(String number, LocalDate expirationDate, String ownerName,
      String crypto) {
    this.number = number;
    this.expirationDate = expirationDate;
    this.ownerName = ownerName;
    this.crypto = crypto;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public String getCrypto() {
    return crypto;
  }

  public void setCrypto(String crypto) {
    this.crypto = crypto;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((crypto == null) ? 0 : crypto.hashCode());
    result = prime * result
        + ((expirationDate == null) ? 0 : expirationDate.hashCode());
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
    return result;
  }

  public CardType getCardType() {
    return cardType;
  }

  public void setCardType(CardType cardType) {
    this.cardType = cardType;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CreditCard other = (CreditCard) obj;
    if (crypto == null) {
      if (other.crypto != null)
        return false;
    } else if (!crypto.equals(other.crypto))
      return false;
    if (expirationDate == null) {
      if (other.expirationDate != null)
        return false;
    } else if (!expirationDate.equals(other.expirationDate))
      return false;
    if (number == null) {
      if (other.number != null)
        return false;
    } else if (!number.equals(other.number))
      return false;
    if (ownerName == null) {
      if (other.ownerName != null)
        return false;
    } else if (!ownerName.equals(other.ownerName))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CreditCard [number=" + number + ", expirationDate="
        + expirationDate + ", ownerName=" + ownerName + ", crypto=" + crypto
        + "]";
  }
}
