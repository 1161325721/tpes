package com.cy.tpes.entity.hwxbean;


public class Log {

  private long lid;
  private long wid;
  private String ltype;
  private java.sql.Timestamp ltime;
  private String lstate;
  private String lmessage;
  private String temp1;


  public long getLid() {
    return lid;
  }

  public void setLid(long lid) {
    this.lid = lid;
  }


  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }


  public String getLtype() {
    return ltype;
  }

  public void setLtype(String ltype) {
    this.ltype = ltype;
  }


  public java.sql.Timestamp getLtime() {
    return ltime;
  }

  public void setLtime(java.sql.Timestamp ltime) {
    this.ltime = ltime;
  }


  public String getLstate() {
    return lstate;
  }

  public void setLstate(String lstate) {
    this.lstate = lstate;
  }


  public String getLmessage() {
    return lmessage;
  }

  public void setLmessage(String lmessage) {
    this.lmessage = lmessage;
  }


  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }


  @Override
  public String toString()
  {
    return "Log{" + "lid=" + lid + ", wid=" + wid + ", ltype='" + ltype + '\'' + ", ltime=" + ltime + ", lstate='" + lstate + '\'' + ", lmessage='" + lmessage + '\'' + ", temp1='" + temp1 + '\'' + '}';
  }
}
