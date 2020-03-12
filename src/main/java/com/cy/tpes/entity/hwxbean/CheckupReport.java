package com.cy.tpes.entity.hwxbean;


public class CheckupReport {

  private Long crid;
  private Long gcid;
  private String crsummary;
  private String crsuggest;
  private String crlifeguide;
  private Long workid;
  private java.sql.Timestamp crdate;
  private String temp1;
  private String wname;

  public String getWname()
  {
    return wname;
  }

  public void setWname(String wname)
  {
    this.wname = wname;
  }

  public Long getCrid()
  {
    return crid;
  }

  public void setCrid(Long crid)
  {
    this.crid = crid;
  }

  public void setGcid(Long gcid)
  {
    this.gcid = gcid;
  }

  public void setWorkid(Long workid)
  {
    this.workid = workid;
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


  public java.sql.Timestamp getCrdate() {
    return crdate;
  }

  public void setCrdate(java.sql.Timestamp crdate) {
    this.crdate = crdate;
  }


  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }

}
