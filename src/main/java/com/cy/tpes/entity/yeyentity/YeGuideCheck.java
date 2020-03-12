package com.cy.tpes.entity.yeyentity;


import java.sql.Date;

public class YeGuideCheck
{

  private long gcid;
  private String pcardnumber;
  private long goid;
  private long packid;
  private java.sql.Date gcdate;
  private String temp1;
  private String gcstate;


  public long getGcid() {
    return gcid;
  }

  public void setGcid(long gcid) {
    this.gcid = gcid;
  }


  public String getPcardnumber() {
    return pcardnumber;
  }

  public void setPcardnumber(String pcardnumber) {
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

  public Date getGcdate()
  {
    return gcdate;
  }

  public void setGcdate(Date gcdate)
  {
    this.gcdate = gcdate;
  }

  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }

  public String getGcstate()
  {
    return gcstate;
  }

  public void setGcstate(String gcstate)
  {
    this.gcstate = gcstate;
  }
}
