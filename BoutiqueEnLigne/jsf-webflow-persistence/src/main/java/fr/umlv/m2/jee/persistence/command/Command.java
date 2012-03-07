package fr.umlv.m2.jee.persistence.command;

import org.joda.time.DateTime;

public class Command {
  private long id;
  private DateTime date;
//  private Map<Long, Product> products 
  public Command() {

  }

  public Command(long id, DateTime date) {
    this.id = id;
    this.date = date;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public DateTime getDate() {
    return date;
  }

  public void setDate(DateTime date) {
    this.date = date;
  }

}
