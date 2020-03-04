package com.cy.tpes.entity.sysmgr;


public class Patient {

  private long pid;
  private String pname;
  private long pphone;
  private long gcid;
  private long pgender;
  private long pcardnumber;
  private String pidentitynumber;
  private String pstate;
  private String temp1;


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }


  public long getPphone() {
    return pphone;
  }

  public void setPphone(long pphone) {
    this.pphone = pphone;
  }


  public long getGcid() {
    return gcid;
  }

  public void setGcid(long gcid) {
    this.gcid = gcid;
  }


  public long getPgender() {
    return pgender;
  }

  public void setPgender(long pgender) {
    this.pgender = pgender;
  }


  public long getPcardnumber() {
    return pcardnumber;
  }

  public void setPcardnumber(long pcardnumber) {
    this.pcardnumber = pcardnumber;
  }


  public String getPidentitynumber() {
    return pidentitynumber;
  }

  public void setPidentitynumber(String pidentitynumber) {
    this.pidentitynumber = pidentitynumber;
  }


  public String getPstate() {
    return pstate;
  }

  public void setPstate(String pstate) {
    this.pstate = pstate;
  }


  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }

}
