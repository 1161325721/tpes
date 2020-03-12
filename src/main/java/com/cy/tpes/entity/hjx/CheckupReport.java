package com.cy.tpes.entity.hjx;

import java.util.List;

public class CheckupReport {

  private long crid;
  private long gcid;
  private String crsummary;
  private String crsuggest;
  private String crlifeguide;
  private long workid;
  private String crdate;
  private String temp1;
  private Package aPackage;
  private List<CheckupProject> checkupProjects;
  private Worker worker;

  public Worker getWorker() {
    return worker;
  }

  public void setWorker(Worker worker) {
    this.worker = worker;
  }

  public List<CheckupProject> getCheckupProjects() {
    return checkupProjects;
  }

  public void setCheckupProjects(List<CheckupProject> checkupProjects) {
    this.checkupProjects = checkupProjects;
  }

  public Package getaPackage() {
    return aPackage;
  }

  public void setaPackage(Package aPackage) {
    this.aPackage = aPackage;
  }

  public long getCrid() {
    return crid;
  }

  public void setCrid(long crid) {
    this.crid = crid;
  }


  public long getGcid() {
    return gcid;
  }

  public void setGcid(long gcid) {
    this.gcid = gcid;
  }


  public String getCrsummary() {
    return crsummary;
  }

  public void setCrsummary(String crsummary) {
    this.crsummary = crsummary;
  }


  public String getCrsuggest() {
    return crsuggest;
  }

  public void setCrsuggest(String crsuggest) {
    this.crsuggest = crsuggest;
  }


  public String getCrlifeguide() {
    return crlifeguide;
  }

  public void setCrlifeguide(String crlifeguide) {
    this.crlifeguide = crlifeguide;
  }


  public long getWorkid() {
    return workid;
  }

  public void setWorkid(long workid) {
    this.workid = workid;
  }

  public String getCrdate() {
    return crdate;
  }

  public void setCrdate(String crdate) {
    this.crdate = crdate;
  }

  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }

}
