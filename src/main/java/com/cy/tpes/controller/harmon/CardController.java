package com.cy.tpes.controller.harmon;


import com.cy.tpes.entity.harmon.*;
import com.cy.tpes.entity.hwxbean.Patient;
import com.cy.tpes.entity.hwxbean.Worker;
import com.cy.tpes.service.harmon.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller

public class CardController {
    @Autowired
    private CardService cardService;

    //页面显示
    @RequestMapping(path = "/page/{pageName}")
    public String toPage(@PathVariable("pageName") String pageName) {
        return ("/" + pageName);
    }


    //卡入库
    @RequestMapping("/cardStorageInster")
    public @ResponseBody
    String cardStorageInster(CardStorage cardStorage, HttpServletRequest request) {
        Worker worker = (Worker) request.getSession().getAttribute("worker");
        cardStorage.setStorageWid((int) worker.getWid());
        String msg = cardService.cardStorageInster(cardStorage);
        return msg;
    }

    //    卡申请
    @RequestMapping("/cardApplyInsert")
    public @ResponseBody
    String cardApplyInsert(CardApply cardApply, HttpServletRequest request) {
        Worker worker = (Worker) request.getSession().getAttribute("worker");
        cardApply.setApplyWid((int) worker.getWid());
        String msg = cardService.cardApplyInsert(cardApply);
        return msg;
    }

    //    卡入库查询
    @RequestMapping("/queryCardStorage")
    public @ResponseBody
    CardTableMsg queryCardStorage(CardAllConditionMsg conditionMsg) {
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        CardTableMsg cardTableMsg = cardService.queryCardStorage(conditionMsg);
        return cardTableMsg;
    }

    //    卡申请查询
    @RequestMapping("/queryCardApply")
    public @ResponseBody
    CardTableMsg queryCardApply(CardAllConditionMsg conditionMsg, HttpServletRequest request) {
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        Worker worker = (Worker) request.getSession().getAttribute("worker");
        conditionMsg.setApplyWid((int) worker.getWid());
        CardTableMsg cardTableMsg = cardService.queryCardApply(conditionMsg);
        return cardTableMsg;
    }

    //修改卡申请单
    @RequestMapping("/cardApplyUpdateByProposer")
    public @ResponseBody
    String cardApplyUpdateByProposer(CardApply cardApply) {
        String msg = cardService.cardApplyUpdateByProposer(cardApply);
        return msg;
    }

    //    审核页面卡申请查询
    @RequestMapping("/queryCardApplyToAudit")
    public @ResponseBody
    CardTableMsg queryCardApplyToAudit(CardAllConditionMsg conditionMsg) {
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        CardTableMsg cardTableMsg = cardService.queryCardApply(conditionMsg);
        return cardTableMsg;
    }

    //    查询所有前台工作人员用于审核下拉框
    @RequestMapping("/queryAllCardProposer")
    public @ResponseBody
    ArrayList<Worker> queryAllCardProposer(Worker Worker) {
        ArrayList<Worker> workers = cardService.queryAllCardProposer(Worker);
        return workers;
    }

    //卡审核
    @RequestMapping("/manageCardApply")
    public @ResponseBody
    String manageCardApply(CardAllConditionMsg conditionMsg, HttpServletRequest request) {
        conditionMsg.setAuditWid((int) ((Worker) request.getSession().getAttribute("worker")).getWid());
        String msg = cardService.manageCardApply(conditionMsg);
        return msg;
    }

    //审核后详情页
    @RequestMapping("/showCardInfoToAudit")
    public @ResponseBody
    ArrayList<Card> showCardInfoToAudit(Card card) {
        ArrayList<Card> cards = cardService.showCardInfoToAudit(card);
        return cards;
    }

    //查询病人信息
    @RequestMapping("/queryPatientInfo")
    public @ResponseBody
    CardResponseMsg queryPatientInfo(Patient Patient) {
        CardResponseMsg cardResponseMsg = cardService.queryPatientInfo(Patient);
        return cardResponseMsg;
    }

    //查询卡片信息
    @RequestMapping("/queryCardInfo")
    public @ResponseBody
    CardResponseMsg queryCardInfo(Card card) {
        CardResponseMsg cardResponseMsg = cardService.queryCardInfo(card);
        return cardResponseMsg;
    }

    //    发卡
    @RequestMapping("/cardIssue")
    public @ResponseBody
    String cardIssue(Card card, HttpServletRequest request) {
        card.setIssueWid((int) ((Worker) request.getSession().getAttribute("worker")).getWid());
        String msg = cardService.cardIssue(card);
        return msg;
    }

    //    换卡
    @RequestMapping("/cardChange")
    public @ResponseBody
    String cardChange(Card card, HttpServletRequest request) {
        card.setIssueWid((int) ((Worker) request.getSession().getAttribute("worker")).getWid());
        String msg = cardService.cardChange(card);
        return msg;
    }

    //    卡销卡申请查询
    @RequestMapping("/queryCardCancel")
    public @ResponseBody
    CardTableMsg queryCardCancel(CardAllConditionMsg conditionMsg, HttpServletRequest request) {
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        Worker worker = (Worker) request.getSession().getAttribute("worker");
        conditionMsg.setCancelWid((int) worker.getWid());
        CardTableMsg cardTableMsg = cardService.queryCardCancel(conditionMsg);
        return cardTableMsg;
    }

    //    卡销卡申请
    @RequestMapping("/cardCancelInsert")
    public @ResponseBody
    CardResponseMsg cardCancelInsert(CardCancel cardCancel, HttpServletRequest request) {
        Worker worker = (Worker) request.getSession().getAttribute("worker");
        cardCancel.setCancelWid((int) worker.getWid());
        CardResponseMsg cardResponseMsg = cardService.cardCancelInsert(cardCancel);
        return cardResponseMsg;
    }

    //    卡销卡撤销
    @RequestMapping("/cardCancelBackout")
    public @ResponseBody
    String cardCancelBackout(CardCancel cardCancel) {
        String msg = cardService.cardCancelBackout(cardCancel);
        return msg;
    }

    //    卡销卡申请查询
    @RequestMapping("/queryCardCancelToAudit")
    public @ResponseBody
    CardTableMsg queryCardCancelToAudit(CardAllConditionMsg conditionMsg) {
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        CardTableMsg cardTableMsg = cardService.queryCardCancel(conditionMsg);
        return cardTableMsg;
    }

    //    卡销卡申请审核
    @RequestMapping("/manageCardCancelPass")
    public @ResponseBody
    String manageCardCancelPass(CardCancel cardCancel, HttpServletRequest request) {
        Worker worker = (Worker) request.getSession().getAttribute("worker");
        cardCancel.setAuditWid((int) worker.getWid());
        String msg = cardService.manageCardCancelPass(cardCancel);
        return msg;
    }

    //    卡销卡申请审核
    @RequestMapping("/manageCardCancelBack")
    public @ResponseBody
    String manageCardCancelBack(CardCancel cardCancel, HttpServletRequest request) {
        Worker worker = (Worker) request.getSession().getAttribute("worker");
        cardCancel.setAuditWid((int) worker.getWid());
        String msg = cardService.manageCardCancelBack(cardCancel);
        return msg;
    }

    //    前台卡查询
    @RequestMapping("/queryCardAllInfo")
    public @ResponseBody
    CardTableMsg queryCardAllInfo(CardAllConditionMsg conditionMsg, HttpServletRequest request) {
        Worker worker = (Worker) request.getSession().getAttribute("worker");
        conditionMsg.setApplyWid((int) worker.getWid());
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        CardTableMsg cardTableMsg = cardService.queryCardAllInfo(conditionMsg);
        return cardTableMsg;
    }

    //    后台卡查询
    @RequestMapping("/queryCardAllInfoToAudit")
    public @ResponseBody
    CardTableMsg queryCardAllInfoToAudit(CardAllConditionMsg conditionMsg) {
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        CardTableMsg cardTableMsg = cardService.queryCardAllInfo(conditionMsg);
        return cardTableMsg;
    }

    //    后台工作量统计
    @RequestMapping("/queryWorkload")
    public @ResponseBody
    CardTableMsg queryWorkload(CardAllConditionMsg conditionMsg) {
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        CardTableMsg cardTableMsg = cardService.queryWorkload(conditionMsg);
        return cardTableMsg;
    }


}
