package com.cy.tpes.entity.harmon;

import java.util.Date;

/**
 * @author Created by yawn on 2018-01-09 14:31
 */
public class VacTask {

    private String id;
    private String name;
    private Vacation vac;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vacation getVac() {
        return vac;
    }

    public void setVac(Vacation vac) {
        this.vac = vac;
    }

    @Override
    public String toString() {
        return "VacTask{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", vac=" + vac +
                ", createTime=" + createTime +
                '}';
    }
}
