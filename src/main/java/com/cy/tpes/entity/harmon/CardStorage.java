package com.cy.tpes.entity.harmon;

import java.io.Serializable;
import java.sql.Date;

/**
 * 卡入库(CardStorage)实体类
 *
 * @author makejava
 * @since 2020-02-23 10:40:56
 */
public class CardStorage implements Serializable {
    private static final long serialVersionUID = 264731896710413110L;
    /**
    * ID
    */
    private Integer csId;
    /**
    * 卡前缀
    */
    private String cardPrefix;
    /**
    * 卡开始编号
    */
    private Integer cardSfxstart;
    /**
    * 卡截止编号
    */
    private Integer cardSfxend;
    /**
    * 入库数量
    */
    private Integer cardQuat;
    /**
    * 入库日期
    */
    private Date storageDate;
    /**
    * 入库人ID
    */
    private Integer storageWid;

    private String storageWname;


    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
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

    public Integer getCardQuat() {
        return cardQuat;
    }

    public void setCardQuat(Integer cardQuat) {
        this.cardQuat = cardQuat;
    }

    public Date getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }

    public Integer getStorageWid() {
        return storageWid;
    }

    public void setStorageWid(Integer storageWid) {
        this.storageWid = storageWid;
    }

    public String getStorageWname() {
        return storageWname;
    }

    public void setStorageWname(String storageWname) {
        this.storageWname = storageWname;
    }

    @Override
    public String toString() {
        return "CardStorage{" +
                "csId=" + csId +
                ", cardPrefix='" + cardPrefix + '\'' +
                ", cardSfxstart=" + cardSfxstart +
                ", cardSfxend=" + cardSfxend +
                ", cardQuat=" + cardQuat +
                ", storageDate=" + storageDate +
                ", storageWid=" + storageWid +
                ", storageWname='" + storageWname + '\'' +
                '}';
    }
}