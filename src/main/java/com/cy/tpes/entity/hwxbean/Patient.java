package com.cy.tpes.entity.hwxbean;


import java.sql.Timestamp;
import java.util.Date;

public class Patient {

  private long pid;
  private String pname;
  private String pphone;
  private long gcid;
  private long pgender;
  private String pcardnumber;
  private String pidentitynumber;
  private long pstate;
  private String packname;
  private Timestamp sign_date;//最后日期
  private String gcname;//公司名字
  private Date signDate;
  private String origoName;


  public String getGcname()
  {
    return gcname;
  }

  public void setGcname(String gcname)
  {
    this.gcname = gcname;
  }

  public Timestamp getSign_date()
  {
    return sign_date;
  }

  public void setSign_date(Timestamp sign_date)
  {
    this.sign_date = sign_date;
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


  public String getPackname() {
    return packname;
  }

  public void setPackname(String packname) {
    this.packname = packname;
  }

  public Date getSignDate() {
    return signDate;
  }

  public void setSignDate(Date signDate) {
    this.signDate = signDate;
  }

  public String getOrigoName() {
    return origoName;
  }

  public void setOrigoName(String origoName) {
    this.origoName = origoName;
  }
}
