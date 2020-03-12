package com.cy.tpes.entity.yeyentity;

import java.sql.Timestamp;

public class YeFundTransaction
{

  private long ftid;
  private String fttype;
  private double ftamount;
  private java.sql.Timestamp ftdate;
  private long gcaccount;

  //把时间戳转换为字符串，表格展示
  private String stringDate;

  //表格查询额外增加
  private String gcname;


  public String getStringDate()
  {
    return stringDate;
  }

  public void setStringDate(String stringDate)
  {
    this.stringDate = stringDate;
  }

  public String getGcname()
  {
    return gcname;
  }

  public void setGcname(String gcname)
  {
    this.gcname = gcname;
  }

  public long getFtid()
  {
    return ftid;
  }

  public void setFtid(long ftid)
  {
    this.ftid = ftid;
  }

  public String getFttype()
  {
    return fttype;
  }

  public void setFttype(String fttype)
  {
    this.fttype = fttype;
  }

  public double getFtamount()
  {
    return ftamount;
  }

  public void setFtamount(double ftamount)
  {
    this.ftamount = ftamount;
  }

  public Timestamp getFtdate()
  {
    return ftdate;
  }

  public void setFtdate(Timestamp ftdate)
  {
    this.ftdate = ftdate;
  }

  public long getGcaccount()
  {
    return gcaccount;
  }

  public void setGcaccount(long gcaccount)
  {
    this.gcaccount = gcaccount;
  }
}
