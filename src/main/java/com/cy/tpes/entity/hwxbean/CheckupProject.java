package com.cy.tpes.entity.hwxbean;


import java.sql.Timestamp;

public class CheckupProject {

  private Long cpid;
  private Long crid;
  private Long proid;
  private String cpsummary;
  private Long wid;
  private java.sql.Timestamp cpdate;
  private Long cpstate;
  private String temp1;
  private Long sum_state;
  private String sum_type;
  private String pic;//图片地址

  public Long getCpid()
  {
    return cpid;
  }

  public void setCpid(Long cpid)
  {
    this.cpid = cpid;
  }

  public Long getCrid()
  {
    return crid;
  }

  public void setCrid(Long crid)
  {
    this.crid = crid;
  }

  public Long getProid()
  {
    return proid;
  }

  public void setProid(Long proid)
  {
    this.proid = proid;
  }

  public String getCpsummary()
  {
    return cpsummary;
  }

  public void setCpsummary(String cpsummary)
  {
    this.cpsummary = cpsummary;
  }

  public Long getWid()
  {
    return wid;
  }

  public void setWid(Long wid)
  {
    this.wid = wid;
  }

  public Timestamp getCpdate()
  {
    return cpdate;
  }

  public void setCpdate(Timestamp cpdate)
  {
    this.cpdate = cpdate;
  }

  public Long getCpstate()
  {
    return cpstate;
  }

  public void setCpstate(Long cpstate)
  {
    this.cpstate = cpstate;
  }

  public String getTemp1()
  {
    return temp1;
  }

  public void setTemp1(String temp1)
  {
    this.temp1 = temp1;
  }

  public Long getSum_state()
  {
    return sum_state;
  }

  public void setSum_state(Long sum_state)
  {
    this.sum_state = sum_state;
  }

  public String getSum_type()
  {
    return sum_type;
  }

  public void setSum_type(String sum_type)
  {
    this.sum_type = sum_type;
  }

  public String getPic()
  {
    return pic;
  }

  public void setPic(String pic)
  {
    this.pic = pic;
  }
}
