package com.cy.tpes.entity.sysmgr;


import java.util.List;

public class Package {

  private long packid;
  private String packname;
  private String packintroduction;
  private long packdiscount;
  private List<Project> projects;

  public long getPackid() {
    return packid;
  }

  public void setPackid(long packid) {
    this.packid = packid;
  }


  public String getPackname() {
    return packname;
  }

  public void setPackname(String packname) {
    this.packname = packname;
  }


  public String getPackintroduction() {
    return packintroduction;
  }

  public void setPackintroduction(String packintroduction) {
    this.packintroduction = packintroduction;
  }


  public long getPackdiscount() {
    return packdiscount;
  }

  public void setPackdiscount(long packdiscount) {
    this.packdiscount = packdiscount;
  }

  public List<Project> getProjects()
  {
    return projects;
  }

  public void setProjects(List<Project> projects)
  {
    this.projects = projects;
  }
}
