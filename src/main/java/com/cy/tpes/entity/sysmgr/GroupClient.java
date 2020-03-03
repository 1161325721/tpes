package com.cy.tpes.entity.sysmgr;


public class GroupClient {

  private long gcid;
  private String gcname;
  private long gcaccount;
  private String gcpass;
  private long gcbalance;
  private long gccode;
  private long gcphone;
  private String gcregisterdate;
  private String gcstate;
  private String temp1;

  public GroupClient()
  {
  }

  public GroupClient(long gcid, String gcname, long gcaccount, String gcpass, long gcbalance, long gccode, long gcphone, String gcregisterdate, String gcstate, String temp1)
  {
    this.gcid = gcid;
    this.gcname = gcname;
    this.gcaccount = gcaccount;
    this.gcpass = gcpass;
    this.gcbalance = gcbalance;
    this.gccode = gccode;
    this.gcphone = gcphone;
    this.gcregisterdate = gcregisterdate;
    this.gcstate = gcstate;
    this.temp1 = temp1;
  }

  @Override
  public String toString()
  {
    return "GroupClient{" + "gcid=" + gcid + ", gcname='" + gcname + '\'' + ", gcaccount=" + gcaccount + ", gcpass='" + gcpass + '\'' + ", gcbalance=" + gcbalance + ", gccode=" + gccode + ", gcphone=" + gcphone + ", gcregisterdate=" + gcregisterdate + ", gcstate='" + gcstate + '\'' + ", temp1='" + temp1 + '\'' + '}';
  }

  public long getGcid() {
    return gcid;
  }

  public void setGcid(long gcid) {
    this.gcid = gcid;
  }


  public String getGcname() {
    return gcname;
  }

  public void setGcname(String gcname) {
    this.gcname = gcname;
  }


  public long getGcaccount() {
    return gcaccount;
  }

  public void setGcaccount(long gcaccount) {
    this.gcaccount = gcaccount;
  }


  public String getGcpass() {
    return gcpass;
  }

  public void setGcpass(String gcpass) {
    this.gcpass = gcpass;
  }


  public long getGcbalance() {
    return gcbalance;
  }

  public void setGcbalance(long gcbalance) {
    this.gcbalance = gcbalance;
  }


  public long getGccode() {
    return gccode;
  }

  public void setGccode(long gccode) {
    this.gccode = gccode;
  }


  public long getGcphone() {
    return gcphone;
  }

  public void setGcphone(long gcphone) {
    this.gcphone = gcphone;
  }

  public String getGcregisterdate()
  {
    return gcregisterdate;
  }

  public void setGcregisterdate(String gcregisterdate)
  {
    this.gcregisterdate = gcregisterdate;
  }

  public String getGcstate() {
    return gcstate;
  }

  public void setGcstate(String gcstate) {
    this.gcstate = gcstate;
  }


  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }

}
