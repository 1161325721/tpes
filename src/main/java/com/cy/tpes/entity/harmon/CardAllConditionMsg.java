package com.cy.tpes.entity.harmon;

import java.sql.Date;
import java.sql.Timestamp;

public class CardAllConditionMsg {
    private int page;
    private int limit;
    private String cardPrefix;
    private String cardSfxstart;
    private String cardSfxend;
    private String fdate;
    private String edate;
    private String applyState;
    private int applyWid;
    private int issueWid;
    private int auditWid;
    private int offset;
    private int id;
    private String cardNo;
    private int cancelWid;
    private String cardState;
    private String userName;

    private String password;

    private String groupId;
    private int newStatus;

    public CardAllConditionMsg() {
    }

    public CardAllConditionMsg(int page, int limit, String cardPrefix, String cardSfxstart, String cardSfxend, String fdate, String edate, String applyState, int applyWid, int issueWid, int auditWid, int offset, int id, String cardNo, int cancelWid, String cardState, String userName, String password, String groupId, int newStatus) {
        this.page = page;
        this.limit = limit;
        this.cardPrefix = cardPrefix;
        this.cardSfxstart = cardSfxstart;
        this.cardSfxend = cardSfxend;
        this.fdate = fdate;
        this.edate = edate;
        this.applyState = applyState;
        this.applyWid = applyWid;
        this.issueWid = issueWid;
        this.auditWid = auditWid;
        this.offset = offset;
        this.id = id;
        this.cardNo = cardNo;
        this.cancelWid = cancelWid;
        this.cardState = cardState;
        this.userName = userName;
        this.password = password;
        this.groupId = groupId;
        this.newStatus = newStatus;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getCardPrefix() {
        return cardPrefix;
    }

    public void setCardPrefix(String cardPrefix) {
        this.cardPrefix = cardPrefix;
    }

    public String getCardSfxstart() {
        return cardSfxstart;
    }

    public void setCardSfxstart(String cardSfxstart) {
        this.cardSfxstart = cardSfxstart;
    }

    public String getCardSfxend() {
        return cardSfxend;
    }

    public void setCardSfxend(String cardSfxend) {
        this.cardSfxend = cardSfxend;
    }

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public int getApplyWid() {
        return applyWid;
    }

    public void setApplyWid(int applyWid) {
        this.applyWid = applyWid;
    }

    public int getIssueWid() {
        return issueWid;
    }

    public void setIssueWid(int issueWid) {
        this.issueWid = issueWid;
    }

    public int getAuditWid() {
        return auditWid;
    }

    public void setAuditWid(int auditWid) {
        this.auditWid = auditWid;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getCancelWid() {
        return cancelWid;
    }

    public void setCancelWid(int cancelWid) {
        this.cancelWid = cancelWid;
    }

    public String getCardState() {
        return cardState;
    }

    public void setCardState(String cardState) {
        this.cardState = cardState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(int newStatus) {
        this.newStatus = newStatus;
    }

    @Override
    public String toString() {
        return "CardAllConditionMsg{" +
                "page=" + page +
                ", limit=" + limit +
                ", cardPrefix='" + cardPrefix + '\'' +
                ", cardSfxstart='" + cardSfxstart + '\'' +
                ", cardSfxend='" + cardSfxend + '\'' +
                ", fdate='" + fdate + '\'' +
                ", edate='" + edate + '\'' +
                ", applyState='" + applyState + '\'' +
                ", applyWid=" + applyWid +
                ", issueWid=" + issueWid +
                ", auditWid=" + auditWid +
                ", offset=" + offset +
                ", id=" + id +
                ", cardNo='" + cardNo + '\'' +
                ", cancelWid=" + cancelWid +
                ", cardState='" + cardState + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", groupId='" + groupId + '\'' +
                ", newStatus=" + newStatus +
                '}';
    }
}
