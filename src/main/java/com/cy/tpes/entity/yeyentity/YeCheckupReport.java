package com.cy.tpes.entity.yeyentity;

public class YeCheckupReport
{

  private long crid;
  private long gcid;
  private String crsummary;
  private String crsuggest;
  private String crlifeguide;
  private long workid;
  private java.sql.Timestamp crdate;
  private String temp1;

  //以下为表格查询需要新增
  private String pname;
  private String pcardnumber;
  private  String packname;
  private long goid;


  public String getPname()
  {
    return pname;
  }

  public void setPname(String pname)
  {
    this.pname = pname;
  }

  public String getPcardnumber()
  {
    return pcardnumber;
  }

  public void setPcardnumber(String pcardnumber)
  {
    this.pcardnumber = pcardnumber;
  }

  public String getPackname()
  {
    return packname;
  }

  public void setPackname(String packname)
  {
    this.packname = packname;
  }

  public long getGoid()
  {
    return goid;
  }

  public void setGoid(long goid)
  {
    this.goid = goid;
  }

  public long getCrid() {
    return crid;
  }

  public void setCrid(long crid) {
    this.crid = crid;
  }


  public long getGcid() {
    return gcid;
  }

  public void setGcid(long gcid) {
    this.gcid = gcid;
  }


  public String getCrsummary() {
    return crsummary;
  }

  public void setCrsummary(String crsummary) {
    this.crsummary = crsummary;
  }


  public String getCrsuggest() {
    return crsuggest;
  }

  public void setCrsuggest(String crsuggest) {
    this.crsuggest = crsuggest;
  }


  public String getCrlifeguide() {
    return crlifeguide;
  }

  public void setCrlifeguide(String crlifeguide) {
    this.crlifeguide = crlifeguide;
  }


  public long getWorkid() {
    return workid;
  }

  public void setWorkid(long workid) {
    this.workid = workid;
  }


  public java.sql.Timestamp getCrdate() {
    return crdate;
  }

  public void setCrdate(java.sql.Timestamp crdate) {
    this.crdate = crdate;
  }


  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }

}
