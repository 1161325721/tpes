package com.cy.tpes.entity.sysmgr;


import java.util.List;

public class Project {

  private long proid;
  private String pname;
  private long pcharge;
  private long pgender;
  private long did;
  private Department department;
  private List<Item> items;

  public long getProid() {
    return proid;
  }

  public void setProid(long proid) {
    this.proid = proid;
  }


  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }


  public long getPcharge() {
    return pcharge;
  }

  public void setPcharge(long pcharge) {
    this.pcharge = pcharge;
  }


  public long getPgender() {
    return pgender;
  }

  public void setPgender(long pgender) {
    this.pgender = pgender;
  }


  public long getDid() {
    return did;
  }

  public void setDid(long did) {
    this.did = did;
  }

  public Department getDepartment()
  {
    return department;
  }

  public void setDepartment(Department department)
  {
    this.department = department;
  }

  public List<Item> getItems()
  {
    return items;
  }

  public void setItems(List<Item> items)
  {
    this.items = items;
  }
}
