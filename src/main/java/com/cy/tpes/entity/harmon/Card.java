package com.cy.tpes.entity.harmon;

import java.io.Serializable;
import java.sql.Date;

/**
 * 健康卡表(Card)实体类
 *
 * @author makejava
 * @since 2020-02-23 23:17:09
 */
public class Card implements Serializable {
    private static final long serialVersionUID = 767053654390792906L;
    /**
    * 卡id
    */
    private Integer cardId;
    /**
    * 卡号
    */
    private String cardNo;
    /**
    * 病人id
    */
    private String pcardnumber;
    /**
    * 发卡时间
    */
    private Date issueDate;
    /**
    * 发卡人id
    */
    private Integer issueWid;
    /**
    * 发卡时长
    */
    private Integer timeCost;
    /**
    * 卡前缀
    */
    private String cardPrefix;
    /**
    * 卡编号
    */
    private Integer cardSuffix;
    /**
    * 领卡人id
    */
    private Integer applyWid;
    /**
    * 申请时间
    */
    private Date getDate;
    /**
    * 卡余额
    */
    private Integer balance;
    
    private Integer cardState;
    private Integer offset;
    private Integer limit;
    private Integer capplyId;
    private String patientName;
    private String applyWname;
    private String issueWname;
    private String companyName;
    private String cancelCause;



    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPcardnumber() {
        return pcardnumber;
    }

    public void setPcardnumber(String pcardnumber) {
        this.pcardnumber = pcardnumber;
    }

    public Object getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Integer getIssueWid() {
        return issueWid;
    }

    public void setIssueWid(Integer issueWid) {
        this.issueWid = issueWid;
    }

    public Integer getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Integer timeCost) {
        this.timeCost = timeCost;
    }

    public String getCardPrefix() {
        return cardPrefix;
    }

    public void setCardPrefix(String cardPrefix) {
        this.cardPrefix = cardPrefix;
    }

    public Integer getCardSuffix() {
        return cardSuffix;
    }

    public void setCardSuffix(Integer cardSuffix) {
        this.cardSuffix = cardSuffix;
    }

    public Integer getApplyWid() {
        return applyWid;
    }

    public void setApplyWid(Integer applyWid) {
        this.applyWid = applyWid;
    }

    public Object getGetDate() {
        return getDate;
    }

    public void setGetDate(Date getDate) {
        this.getDate = getDate;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCapplyId() {
        return capplyId;
    }

    public void setCapplyId(Integer capplyId) {
        this.capplyId = capplyId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getApplyWname() {
        return applyWname;
    }

    public void setApplyWname(String applyWname) {
        this.applyWname = applyWname;
    }

    public String getIssueWname() {
        return issueWname;
    }

    public void setIssueWname(String issueWname) {
        this.issueWname = issueWname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCancelCause() {
        return cancelCause;
    }

    public void setCancelCause(String cancelCause) {
        this.cancelCause = cancelCause;
    }
}