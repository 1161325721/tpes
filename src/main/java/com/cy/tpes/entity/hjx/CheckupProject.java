package com.cy.tpes.entity.hjx;

import java.util.List;

public class CheckupProject {

  private long cpid;
  private long crid;
  private long proid;
  private String cpsummary;
  private long wid;
  private String cpdate;
  private long cpstate;
  private String temp1;
  private long sumState;
  private String sumType;
  private String pic;
  private Project project;
  private List<CheckupItem> checkupItems;
  private Worker worker;
  public Project getProject() {
    return project;
  }

  public List<CheckupItem> getCheckupItems() {
    return checkupItems;
  }

  public Worker getWorker() {
    return worker;
  }

  public void setWorker(Worker worker) {
    this.worker = worker;
  }

  public void setCheckupItems(List<CheckupItem> checkupItems) {
    this.checkupItems = checkupItems;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public long getCpid() {
    return cpid;
  }

  public void setCpid(long cpid) {
    this.cpid = cpid;
  }


  public long getCrid() {
    return crid;
  }

  public void setCrid(long crid) {
    this.crid = crid;
  }


  public long getProid() {
    return proid;
  }

  public void setProid(long proid) {
    this.proid = proid;
  }


  public String getCpsummary() {
    return cpsummary;
  }

  public void setCpsummary(String cpsummary) {
    this.cpsummary = cpsummary;
  }


  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }


  public String getCpdate() {
    return cpdate;
  }

  public void setCpdate(String cpdate) {
    this.cpdate = cpdate;
  }

  public long getCpstate() {
    return cpstate;
  }

  public void setCpstate(long cpstate) {
    this.cpstate = cpstate;
  }


  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }


  public long getSumState() {
    return sumState;
  }

  public void setSumState(long sumState) {
    this.sumState = sumState;
  }


  public String getSumType() {
    return sumType;
  }

  public void setSumType(String sumType) {
    this.sumType = sumType;
  }


  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public CheckupProject() {
  }
}
