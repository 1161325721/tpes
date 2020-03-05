package com.cy.tpes.entity.yeyentity;


import java.sql.Date;

public class YePatient
{

  private long pid;
  private String pname;
  private String pphone;
  private long gcid;
  private long pgender;
  private String pcardnumber;
  private String pidentitynumber;
  private long pstate;
  private String packname;
  private java.sql.Date sign_date;
  //另外加的，显示数据表格是需要
  private String gcname;
  private long gcaccount;

  public Date getSign_date()
  {
    return sign_date;
  }

  public void setSign_date(Date sign_date)
  {
    this.sign_date = sign_date;
  }

  public long getGcaccount()
  {
    return gcaccount;
  }

  public void setGcaccount(long gcaccount)
  {
    this.gcaccount = gcaccount;
  }

  public String getGcname()
  {
    return gcname;
  }

  public void setGcname(String gcname)
  {
    this.gcname = gcname;
  }

  public String getPackname()
  {
    return packname;
  }

  public void setPackname(String packname)
  {
    this.packname = packname;
  }

  public YePatient(String pname, String pphone, long gcid, long pgender, String pcardnumber, String pidentitynumber, long pstate,  String packname)
  {
    this.packname = packname;
    this.pname = pname;
    this.pphone = pphone;
    this.gcid = gcid;
    this.pgender = pgender;
    this.pcardnumber = pcardnumber;
    this.pidentitynumber = pidentitynumber;
    this.pstate = pstate;
  }

  public YePatient()
  {
  }

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


  public String getPphone() {
    return pphone;
  }

  public void setPphone(String pphone) {
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


  public String getPcardnumber() {
    return pcardnumber;
  }

  public void setPcardnumber(String pcardnumber) {
    this.pcardnumber = pcardnumber;
  }


  public String getPidentitynumber() {
    return pidentitynumber;
  }

  public void setPidentitynumber(String pidentitynumber) {
    this.pidentitynumber = pidentitynumber;
  }


  public long getPstate() {
    return pstate;
  }

  public void setPstate(long pstate) {
    this.pstate = pstate;
  }

}
