package com.cy.tpes.entity.yeyentity;


import java.sql.Timestamp;

public class YeGroupClient
{

  private long gcid;
  private String gcname;
  private long gcaccount;
  private String gcpass;
  private double gcbalance;
  private long gccode;
  private String gcphone;
  private java.sql.Timestamp gcregisterdate;
  private long gcstate;
  private String temp1;

  public long getGcid()
  {
    return gcid;
  }

  public void setGcid(long gcid)
  {
    this.gcid = gcid;
  }

  public String getGcname()
  {
    return gcname;
  }

  public void setGcname(String gcname)
  {
    this.gcname = gcname;
  }

  public long getGcaccount()
  {
    return gcaccount;
  }

  public void setGcaccount(long gcaccount)
  {
    this.gcaccount = gcaccount;
  }

  public String getGcpass()
  {
    return gcpass;
  }

  public void setGcpass(String gcpass)
  {
    this.gcpass = gcpass;
  }

  public double getGcbalance()
  {
    return gcbalance;
  }

  public void setGcbalance(double gcbalance)
  {
    this.gcbalance = gcbalance;
  }

  public long getGccode()
  {
    return gccode;
  }

  public void setGccode(long gccode)
  {
    this.gccode = gccode;
  }

  public String getGcphone()
  {
    return gcphone;
  }

  public void setGcphone(String gcphone)
  {
    this.gcphone = gcphone;
  }

  public Timestamp getGcregisterdate()
  {
    return gcregisterdate;
  }

  public void setGcregisterdate(Timestamp gcregisterdate)
  {
    this.gcregisterdate = gcregisterdate;
  }

  public long getGcstate()
  {
    return gcstate;
  }

  public void setGcstate(long gcstate)
  {
    this.gcstate = gcstate;
  }

  public String getTemp1()
  {
    return temp1;
  }

  public void setTemp1(String temp1)
  {
    this.temp1 = temp1;
  }
}
