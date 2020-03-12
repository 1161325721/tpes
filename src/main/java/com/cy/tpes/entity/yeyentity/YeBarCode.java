package com.cy.tpes.entity.yeyentity;


public class YeBarCode
{

  private long bcid;
  private long pid;
  private long iid;
  private java.sql.Timestamp bcdate;
  private long bcnumber;
  private String temp1;


  public long getBcid() {
    return bcid;
  }

  public void setBcid(long bcid) {
    this.bcid = bcid;
  }


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public long getIid() {
    return iid;
  }

  public void setIid(long iid) {
    this.iid = iid;
  }


  public java.sql.Timestamp getBcdate() {
    return bcdate;
  }

  public void setBcdate(java.sql.Timestamp bcdate) {
    this.bcdate = bcdate;
  }


  public long getBcnumber() {
    return bcnumber;
  }

  public void setBcnumber(long bcnumber) {
    this.bcnumber = bcnumber;
  }


  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }

}
