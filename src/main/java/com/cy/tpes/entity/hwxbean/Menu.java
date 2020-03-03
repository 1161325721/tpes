package com.cy.tpes.entity.hwxbean;


public class Menu {

  private long rid;
  private String rname;
  private long mid;
  private String mname;
  private long mparentid;
  private String mpath;
  private String temp1;
  private String firstname;
  private String secondname;

  public String getFirstname()
  {
    return firstname;
  }

  public void setFirstname(String firstname)
  {
    this.firstname = firstname;
  }

  public String getSecondname()
  {
    return secondname;
  }

  public void setSecondname(String secondname)
  {
    this.secondname = secondname;
  }

  public long getRid()
  {
    return rid;
  }

  public void setRid(long rid)
  {
    this.rid = rid;
  }

  public String getRname()
  {
    return rname;
  }

  public void setRname(String rname)
  {
    this.rname = rname;
  }

  public long getMid() {
    return mid;
  }

  public void setMid(long mid) {
    this.mid = mid;
  }


  public String getMname() {
    return mname;
  }

  public void setMname(String mname) {
    this.mname = mname;
  }


  public long getMparentid() {
    return mparentid;
  }

  public void setMparentid(long mparentid) {
    this.mparentid = mparentid;
  }


  public String getMpath() {
    return mpath;
  }

  public void setMpath(String mpath) {
    this.mpath = mpath;
  }


  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }

}
