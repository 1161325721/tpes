package com.cy.tpes.entity.hjx;


public class CheckupItem {

    private long ciid;
    private long cpid;
    private long iid;
    private double cicheckvalue;
    private String cicompareresult;
    private long wid;
    private String cidate;
    private String temp1;
    private Item item;

    public long getCiid() {
        return ciid;
    }

    public void setCiid(long ciid) {
        this.ciid = ciid;
    }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public long getCpid() {
        return cpid;
    }

    public void setCpid(long cpid) {
        this.cpid = cpid;
    }


    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }


    public double getCicheckvalue() {
        return cicheckvalue;
    }

    public void setCicheckvalue(double cicheckvalue) {
        this.cicheckvalue = cicheckvalue;
    }


    public String getCicompareresult() {
        return cicompareresult;
    }

    public void setCicompareresult(String cicompareresult) {
        this.cicompareresult = cicompareresult;
    }


    public long getWid() {
        return wid;
    }

    public void setWid(long wid) {
        this.wid = wid;
    }


    public String getCidate() {
        return cidate;
    }

    public void setCidate(String cidate) {
        this.cidate = cidate;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

}
