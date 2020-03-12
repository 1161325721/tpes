package com.cy.tpes.entity.hjx;


import java.util.List;

public class GuideCheck {

  private long gcid;
  private String pcardnumber;
  private long goid;
  private long packid;
  private String gcdate;
  private String temp1;
  private String temp2;
  private CheckupReport checkupReport;
  private List<CheckupProject> checkupProjects;

  public CheckupReport getCheckupReport() {
    return checkupReport;
  }

  public void setCheckupReport(CheckupReport checkupReport) {
    this.checkupReport = checkupReport;
  }

  public List<CheckupProject> getCheckupProjects() {
    return checkupProjects;
  }

  public void setCheckupProjects(List<CheckupProject> checkupProjects) {
    this.checkupProjects = checkupProjects;
  }

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

  public String getGcdate() {
    return gcdate;
  }

  public void setGcdate(String gcdate) {
    this.gcdate = gcdate;
  }

  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }


  public String getTemp2() {
    return temp2;
  }

  public void setTemp2(String temp2) {
    this.temp2 = temp2;
  }

}
