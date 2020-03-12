package com.cy.tpes.entity.harmon;

public class CardWorkload {
    private int totalCard;
    private int changeNos;
    private int issueNos;
    private  int issueWid;
    private int changeWid;
    private String wname;

    public CardWorkload() {
    }

    public CardWorkload(int totalCard, int changeNos, int issueNos, int issueWid, int changeWid, String wname) {
        this.totalCard = totalCard;
        this.changeNos = changeNos;
        this.issueNos = issueNos;
        this.issueWid = issueWid;
        this.changeWid = changeWid;
        this.wname = wname;
    }

    public int getTotalCard() {
        return totalCard;
    }

    public void setTotalCard(int totalCard) {
        this.totalCard = totalCard;
    }

    public int getChangeNos() {
        return changeNos;
    }

    public void setChangeNos(int changeNos) {
        this.changeNos = changeNos;
    }

    public int getIssueNos() {
        return issueNos;
    }

    public void setIssueNos(int issueNos) {
        this.issueNos = issueNos;
    }

    public int getIssueWid() {
        return issueWid;
    }

    public void setIssueWid(int issueWid) {
        this.issueWid = issueWid;
    }

    public int getChangeWid() {
        return changeWid;
    }

    public void setChangeWid(int changeWid) {
        this.changeWid = changeWid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }
}
