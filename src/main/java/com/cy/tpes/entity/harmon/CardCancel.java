package com.cy.tpes.entity.harmon;

import java.io.Serializable;
import java.sql.Date;

/**
 * (CardCancel)实体类
 *
 * @author makejava
 * @since 2020-02-27 10:58:10
 */
public class CardCancel implements Serializable {
    private static final long serialVersionUID = 776982322029322866L;
    
    private Integer ccancelId;
    /**
    * 卡号
    */
    private String cardNo;
    /**
    * 注销人ID
    */
    private Integer cancelWid;
    /**
    * 销卡原因
    */
    private String cancelCause;
    /**
    * 注销时间
    */
    private Date cancelDate;
    /**
    * 审核人
    */
    private Integer auditWid;
    /**
    * 审核时间
    */
    private Date auditDate;
    /**
    * 审核状态
    */
    private Integer applyState;
    private String cancelWname;
    private String auditWname;




    public Integer getCcancelId() {
        return ccancelId;
    }

    public void setCcancelId(Integer ccancelId) {
        this.ccancelId = ccancelId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getCancelWid() {
        return cancelWid;
    }

    public void setCancelWid(Integer cancelWid) {
        this.cancelWid = cancelWid;
    }

    public String getCancelCause() {
        return cancelCause;
    }

    public void setCancelCause(String cancelCause) {
        this.cancelCause = cancelCause;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
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

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    public String getCancelWname() {
        return cancelWname;
    }

    public void setCancelWname(String cancelWname) {
        this.cancelWname = cancelWname;
    }

    public String getAuditWname() {
        return auditWname;
    }

    public void setAuditWname(String auditWname) {
        this.auditWname = auditWname;
    }
}