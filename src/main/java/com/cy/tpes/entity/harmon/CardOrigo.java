package com.cy.tpes.entity.harmon;

import java.io.Serializable;

/**
 * 籍贯表(CardOrigo)实体类
 *
 * @author makejava
 * @since 2020-02-26 14:21:20
 */
public class CardOrigo implements Serializable {
    private static final long serialVersionUID = -38276629286029048L;
    
    private Integer corigoId;
    
    private String origoNo;
    
    private String origoName;


    public Integer getCorigoId() {
        return corigoId;
    }

    public void setCorigoId(Integer corigoId) {
        this.corigoId = corigoId;
    }

    public String getOrigoNo() {
        return origoNo;
    }

    public void setOrigoNo(String origoNo) {
        this.origoNo = origoNo;
    }

    public String getOrigoName() {
        return origoName;
    }

    public void setOrigoName(String origoName) {
        this.origoName = origoName;
    }

}