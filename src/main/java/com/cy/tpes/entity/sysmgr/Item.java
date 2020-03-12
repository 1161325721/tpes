package com.cy.tpes.entity.sysmgr;


import java.util.List;

public class Item {

  private long iid;
  private String iname;
  private String iunit;
  private long imax;
  private long imin;

  public long getIid() {
    return iid;
  }

  public void setIid(long iid) {
    this.iid = iid;
  }


  public String getIname() {
    return iname;
  }

  public void setIname(String iname) {
    this.iname = iname;
  }


  public String getIunit() {
    return iunit;
  }

  public void setIunit(String iunit) {
    this.iunit = iunit;
  }


  public long getImax() {
    return imax;
  }

  public void setImax(long imax) {
    this.imax = imax;
  }


  public long getImin() {
    return imin;
  }

  public void setImin(long imin) {
    this.imin = imin;
  }


}
