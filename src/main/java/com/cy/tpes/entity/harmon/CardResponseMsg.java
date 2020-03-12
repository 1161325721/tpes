package com.cy.tpes.entity.harmon;

public class CardResponseMsg {
    private String msg ;
    private Object obj;

    public CardResponseMsg() {
    }

    public CardResponseMsg(String msg, Object obj) {
        this.msg = msg;
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "CardResponseMsg{" +
                "msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }
}
