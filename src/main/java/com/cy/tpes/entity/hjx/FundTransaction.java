package com.cy.tpes.entity.hjx;

public class FundTransaction {

  private long ftid;
  private String fttype;
  private double ftamount;
  private String ftdate;
  private long gcaccount;

  public String getFtdate() {
    return ftdate;
  }

  public void setFtdate(String ftdate) {
    this.ftdate = ftdate;
  }

  public long getFtid() {
    return ftid;
  }

  public void setFtid(long ftid) {
    this.ftid = ftid;
  }


  public String getFttype() {
    return fttype;
  }

  public void setFttype(String fttype) {
    this.fttype = fttype;
  }


  public double getFtamount() {
    return ftamount;
  }

  public void setFtamount(double ftamount) {
    this.ftamount = ftamount;
  }




  public long getGcaccount() {
    return gcaccount;
  }

  public void setGcaccount(long gcaccount) {
    this.gcaccount = gcaccount;
  }

}
