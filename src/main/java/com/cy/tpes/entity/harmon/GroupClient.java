package com.cy.tpes.entity.harmon;

import java.util.Date;
import java.io.Serializable;

/**
 * 团检客户表(GroupClient)实体类
 *
 * @author makejava
 * @since 2020-02-26 14:20:22
 */
public class GroupClient implements Serializable {
    private static final long serialVersionUID = -88828577926353554L;
    /**
    * 团检客户ID
    */
    private Integer gcid;
    /**
    * 单位名字
    */
    private String gcname;
    /**
    * 帐号
    */
    private Integer gcaccount;
    /**
    * 密码
    */
    private String gcpass;
    /**
    * 余额
    */
    private Double gcbalance;
    /**
    * 联系电话
    */
    private String gcphone;
    /**
    * 注册时间
    */
    private Date gcregisterdate;
    /**
    * 状态ID
    */
    private Integer gcstate;
    /**
    * 单位邮箱
    */
    private String gcemail;


    public Integer getGcid() {
        return gcid;
    }

    public void setGcid(Integer gcid) {
        this.gcid = gcid;
    }

    public String getGcname() {
        return gcname;
    }

    public void setGcname(String gcname) {
        this.gcname = gcname;
    }

    public Integer getGcaccount() {
        return gcaccount;
    }

    public void setGcaccount(Integer gcaccount) {
        this.gcaccount = gcaccount;
    }

    public String getGcpass() {
        return gcpass;
    }

    public void setGcpass(String gcpass) {
        this.gcpass = gcpass;
    }

    public Double getGcbalance() {
        return gcbalance;
    }

    public void setGcbalance(Double gcbalance) {
        this.gcbalance = gcbalance;
    }

    public String getGcphone() {
        return gcphone;
    }

    public void setGcphone(String gcphone) {
        this.gcphone = gcphone;
    }

    public Date getGcregisterdate() {
        return gcregisterdate;
    }

    public void setGcregisterdate(Date gcregisterdate) {
        this.gcregisterdate = gcregisterdate;
    }

    public Integer getGcstate() {
        return gcstate;
    }

    public void setGcstate(Integer gcstate) {
        this.gcstate = gcstate;
    }

    public String getGcemail() {
        return gcemail;
    }

    public void setGcemail(String gcemail) {
        this.gcemail = gcemail;
    }

}