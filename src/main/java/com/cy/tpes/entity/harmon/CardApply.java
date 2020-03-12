package com.cy.tpes.entity.harmon;

import java.io.Serializable;
import java.sql.Date;

/**
 * 卡申领表(CardApply)实体类
 *
 * @author makejava
 * @since 2020-02-23 23:03:11
 */
public class CardApply implements Serializable {
    private static final long serialVersionUID = -53161179024539326L;
    
    private Integer capplyId;
    /**
    * 申请日期
    */
    private Date applyDate;
    /**
    * 申请数量
    */
    private Integer applayQuat;
    /**
    * 申请人id
    */
    private Integer applyWid;
    private String applyWname;
    /**
    * 审核人id
    */
    private Integer auditWid;
    private String auditWname;
    /**
    * 审核时间
    */
    private Date auditDate;
    /**
    * 前缀
    */
    private String cardPrefix;
    /**
    * 开始编号
    */
    private Integer cardSfxstart;
    /**
    * 截止编号
    */
    private Integer cardSfxend;
    /**
    * 申请状态
    */
    private Integer applyState;


    public Integer getCapplyId() {
        return capplyId;
    }

    public void setCapplyId(Integer capplyId) {
        this.capplyId = capplyId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getApplayQuat() {
        return applayQuat;
    }

    public void setApplayQuat(Integer applayQuat) {
        this.applayQuat = applayQuat;
    }

    public Integer getApplyWid() {
        return applyWid;
    }

    public void setApplyWid(Integer applyWid) {
        this.applyWid = applyWid;
    }

    public Integer getAuditWid() {
        return auditWid;
    }

    public void setAuditWid(Integer auditWid) {
        this.auditWid = auditWid;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getCardPrefix() {
        return cardPrefix;
    }

    public void setCardPrefix(String cardPrefix) {
        this.cardPrefix = cardPrefix;
    }

    public Integer getCardSfxstart() {
        return cardSfxstart;
    }

    public void setCardSfxstart(Integer cardSfxstart) {
        this.cardSfxstart = cardSfxstart;
    }

    public Integer getCardSfxend() {
        return cardSfxend;
    }

    public void setCardSfxend(Integer cardSfxend) {
        this.cardSfxend = cardSfxend;
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    public String getApplyWname() {
        return applyWname;
    }

    public void setApplyWname(String applyWname) {
        this.applyWname = applyWname;
    }

    public String getAuditWname() {
        return auditWname;
    }

    public void setAuditWname(String auditWname) {
        this.auditWname = auditWname;
    }

    @Override
    public String toString() {
        return "CardApply{" +
                "capplyId=" + capplyId +
                ", applyDate=" + applyDate +
                ", applayQuat=" + applayQuat +
                ", applyWid=" + applyWid +
                ", applyWname='" + applyWname + '\'' +
                ", auditWid=" + auditWid +
                ", auditWname='" + auditWname + '\'' +
                ", auditDate=" + auditDate +
                ", cardPrefix='" + cardPrefix + '\'' +
                ", cardSfxstart=" + cardSfxstart +
                ", cardSfxend=" + cardSfxend +
                ", applyState=" + applyState +
                '}';
    }
}