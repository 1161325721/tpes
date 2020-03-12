package com.cy.tpes.entity.harmon;

import java.io.Serializable;

/**
 * 新闻爬虫(SpiderNew)实体类
 *
 * @author makejava
 * @since 2020-03-09 23:27:01
 */
public class SpiderNew implements Serializable {
    private static final long serialVersionUID = 549690239895591760L;
    /**
    * 新闻ID
    */
    private Integer newId;
    /**
    * 新闻标题
    */
    private String newTitel;
    /**
    * 超链接
    */
    private String newUrl;

    private Integer  newStatus;


    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }

    public String getNewTitel() {
        return newTitel;
    }

    public void setNewTitel(String newTitel) {
        this.newTitel = newTitel;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public SpiderNew(String newTitel, String newUrl, Integer newStatus) {
        this.newTitel = newTitel;
        this.newUrl = newUrl;
        this.newStatus = newStatus;
    }

    public SpiderNew() {
    }
}