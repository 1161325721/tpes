package com.cy.tpes.entity.hjx;


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
  private String signDate;
  private GroupClient groupClient;

  public GroupClient getGroupClient() {
    return groupClient;
  }

  public void setGroupClient(GroupClient groupClient) {
    this.groupClient = groupClient;
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


  public String getSignDate() {
    return signDate;
  }

  public void setSignDate(String signDate) {
    this.signDate = signDate;
  }
}
