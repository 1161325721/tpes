package com.cy.tpes.entity.harmon;

import java.io.Serializable;
import java.sql.Date;

/**
 * (CardChange)实体类
 *
 * @author makejava
 * @since 2020-02-26 22:37:51
 */
public class CardChange implements Serializable {
    private static final long serialVersionUID = -79285610135094437L;
    /**
    * 换卡记录id
    */
    private Integer cchageId;
    /**
    * 病人id
    */
    private String pcardnumber;
    /**
    * 旧卡号
    */
    private String cardOld;
    /**
    * 新卡号
    */
    private String cardNew;
    /**
    * 换卡人id
    */
    private Integer chageWid;
    /**
    * 换卡时间
    */
    private Date changeDate;
    /**
    * 耗时
    */


    public Integer getCchageId() {
        return cchageId;
    }

    public void setCchageId(Integer cchageId) {
        this.cchageId = cchageId;
    }

    public String getCardOld() {
        return cardOld;
    }

    public void setCardOld(String cardOld) {
        this.cardOld = cardOld;
    }

    public String getCardNew() {
        return cardNew;
    }

    public void setCardNew(String cardNew) {
        this.cardNew = cardNew;
    }

    public Integer getChageWid() {
        return chageWid;
    }

    public void setChageWid(Integer chageWid) {
        this.chageWid = chageWid;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getPcardnumber() {
        return pcardnumber;
    }

    public void setPcardnumber(String pcardnumber) {
        this.pcardnumber = pcardnumber;
    }
}