package com.cy.tpes.entity.sysmgr;


import java.util.List;

public class GuideCheck {

  private long gcid;
  private long gcnumber;
  private long pcardnumber;
  private long goid;
  private long packid;
  private String gcdate;
  private Package packages;
  private Patient patient;

  public long getGcid() {
    return gcid;
  }

  public void setGcid(long gcid) {
    this.gcid = gcid;
  }


  public long getGcnumber() {
    return gcnumber;
  }

  public void setGcnumber(long gcnumber) {
    this.gcnumber = gcnumber;
  }


  public long getPcardnumber() {
    return pcardnumber;
  }

  public void setPcardnumber(long pcardnumber) {
    this.pcardnumber = pcardnumber;
  }


  public long getGoid() {
    return goid;
  }

  public void setGoid(long goid) {
    this.goid = goid;
  }


  public long getPackid() {
    return packid;
  }

  public void setPackid(long packid) {
    this.packid = packid;
  }

  public String getGcdate()
  {
    return gcdate;
  }

  public void setGcdate(String gcdate)
  {
    this.gcdate = gcdate;
  }

  public Package getPackages()
  {
    return packages;
  }

  public void setPackages(Package packages)
  {
    this.packages = packages;
  }

  public Patient getPatient()
  {
    return patient;
  }

  public void setPatient(Patient patient)
  {
    this.patient = patient;
  }
}
