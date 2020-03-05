package com.cy.tpes.entity.yeyentity;


public class YeGroupOrder
{

  private long goid;
  private long gcid;
  private double goprepay;
  private double gorefundpay;
  private double goreceivedpay;
  private long goinvoicingstate;
  private long goinvoicingnumber;
  private java.sql.Timestamp goordertime;
  private long wid;
  private java.sql.Date goappointdate;
  private long golasttime;
  private String gostate;
  private String temp1;

  //订单表查询时增加字段
  private String gcname;
  private long gcaccount;
  private String wname;
  private String sgoordertime;
  private String waccount;


  public String getWaccount()
  {
    return waccount;
  }

  public void setWaccount(String waccount)
  {
    this.waccount = waccount;
  }

  public String getSgoordertime()
  {
    return sgoordertime;
  }

  public void setSgoordertime(String sgoordertime)
  {
    this.sgoordertime = sgoordertime;
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

  public String getWname()
  {
    return wname;
  }

  public void setWname(String wname)
  {
    this.wname = wname;
  }

  public long getGoid() {
    return goid;
  }

  public void setGoid(long goid) {
    this.goid = goid;
  }


  public long getGcid() {
    return gcid;
  }

  public void setGcid(long gcid) {
    this.gcid = gcid;
  }


  public double getGoprepay() {
    return goprepay;
  }

  public void setGoprepay(double goprepay) {
    this.goprepay = goprepay;
  }


  public double getGorefundpay() {
    return gorefundpay;
  }

  public void setGorefundpay(double gorefundpay) {
    this.gorefundpay = gorefundpay;
  }


  public double getGoreceivedpay() {
    return goreceivedpay;
  }

  public void setGoreceivedpay(double goreceivedpay) {
    this.goreceivedpay = goreceivedpay;
  }


  public long getGoinvoicingstate() {
    return goinvoicingstate;
  }

  public void setGoinvoicingstate(long goinvoicingstate) {
    this.goinvoicingstate = goinvoicingstate;
  }


  public long getGoinvoicingnumber() {
    return goinvoicingnumber;
  }

  public void setGoinvoicingnumber(long goinvoicingnumber) {
    this.goinvoicingnumber = goinvoicingnumber;
  }


  public java.sql.Timestamp getGoordertime() {
    return goordertime;
  }

  public void setGoordertime(java.sql.Timestamp goordertime) {
    this.goordertime = goordertime;
  }


  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }


  public java.sql.Date getGoappointdate() {
    return goappointdate;
  }

  public void setGoappointdate(java.sql.Date goappointdate) {
    this.goappointdate = goappointdate;
  }


  public long getGolasttime() {
    return golasttime;
  }

  public void setGolasttime(long golasttime) {
    this.golasttime = golasttime;
  }


  public String getGostate() {
    return gostate;
  }

  public void setGostate(String gostate) {
    this.gostate = gostate;
  }


  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }

}
