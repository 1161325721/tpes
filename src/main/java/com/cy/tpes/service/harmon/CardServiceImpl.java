package com.cy.tpes.service.harmon;

import com.cy.tpes.entity.harmon.*;
import com.cy.tpes.entity.hwxbean.Patient;
import com.cy.tpes.entity.hwxbean.Worker;
import com.cy.tpes.mapper.harmon.CardMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;


    @Override
    public String cardStorageInster(CardStorage cardStorage) {
        int cardSfxstart = cardStorage.getCardSfxstart();
        int cardSfxend = cardStorage.getCardSfxend();
        int cardNos = cardSfxend - cardSfxstart + 1;
        String cardPrefix = cardStorage.getCardPrefix();
        Date date = new Date(System.currentTimeMillis());
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < cardNos; i++) {
            Card card = new Card();
            card.setCardPrefix(cardPrefix);
            card.setCardSuffix(cardSfxstart + i);
            card.setCardNo(cardPrefix + (cardSfxstart + i));
            card.setBalance(0);
            card.setCardState(1001);
            cardList.add(card);
        }
        int cardResult = cardMapper.cardStorageInsterCard(cardList);
        int rcordResult = 0;
        if (cardResult > 0) {
            cardStorage.setStorageDate(date);
            cardStorage.setCardQuat(cardNos);
            rcordResult = cardMapper.cardStorageInsterRrcord(cardStorage);
        }
        String msg = rcordResult > 0 ? "success" : "failed";
        return msg;
    }

    @Override
    public String cardApplyInsert(CardApply cardApply) {
        Date date = new Date(System.currentTimeMillis());
        cardApply.setApplyDate(date);
        cardApply.setApplyState(2001);
        int rcordResult = cardMapper.cardApplyInsertRrcord(cardApply);
        CardResponseMsg cardResponseMsg = new CardResponseMsg();
        String msg = "";
        if (rcordResult > 0) {
            msg = "success";
            cardResponseMsg.setObj(cardApply.getCapplyId());
        } else {
            msg = "failed";
        }
        cardResponseMsg.setMsg(msg);
        return new Gson().toJson(cardResponseMsg);
    }

    @Override
    public String cardApplyUpdateByProposer(CardApply cardApply) {
        int rcordResult = cardMapper.cardApplyUpdateRrcord(cardApply);
        String msg = rcordResult > 0 ? "success" : "failed";
        return msg;
    }

    @Override
    public CardTableMsg queryCardStorage(CardAllConditionMsg conditionMsg) {
        int count = cardMapper.countCardStorage(conditionMsg);
        List cardStorageList = cardMapper.queryCardStorage(conditionMsg);
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(cardStorageList);
        cardTableMsg.setCount(count);
        return cardTableMsg;
    }

    @Override
    public CardTableMsg queryCardApply(CardAllConditionMsg conditionMsg) {
        int count = cardMapper.countCardApply(conditionMsg);
        List cardApplyList = cardMapper.queryCardApply(conditionMsg);
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(cardApplyList);
        cardTableMsg.setCount(count);
        return cardTableMsg;
    }

    @Override
    public ArrayList<Worker> queryAllCardProposer(Worker worker) {
        ArrayList<Worker> workers = cardMapper.queryAllCardProposer(worker);
        return workers;
    }

    @Override
    public String manageCardApply(CardAllConditionMsg conditionMsg) {
        conditionMsg.setLimit(1);
        ArrayList<CardApply> cardApplies = cardMapper.queryCardApply(conditionMsg);
        String msg = "";
        if (cardApplies.size() > 0 && cardApplies.get(0) != null) {
            CardApply cardApply = cardApplies.get(0);
            Card card = new Card();
            card.setCardPrefix(cardApply.getCardPrefix());
            card.setCardState(1001);
            card.setOffset(0);
            card.setLimit(cardApply.getApplayQuat());
            ArrayList<Card> cards = cardMapper.queryAllCard(card);

            if (cards.size() == cardApply.getApplayQuat()) {
                card.setCardState(1002);
                card.setApplyWid(cardApply.getApplyWid());
                card.setGetDate(new Date(System.currentTimeMillis()));
                card.setCapplyId(cardApply.getCapplyId());
                int result = cardMapper.cardUpdateToAudit(cards, card);
                cardApply.setAuditWid(conditionMsg.getAuditWid());
                cardApply.setApplyState(2002);
                cardApply.setAuditDate(new Date(System.currentTimeMillis()));
                int index = cardMapper.cardApplyUpdateRrcord(cardApply);
                msg = index > 0 ? "success" : "failed";
            } else {
                msg = "cannot";
            }

        } else {
            msg = "cannot";
        }

        return msg;
    }

    @Override
    public ArrayList<Card> showCardInfoToAudit(Card card) {
        ArrayList<Card> cards = cardMapper.queryCardByApplyId(card);
        return cards;
    }

    @Override
    public CardResponseMsg queryPatientInfo(Patient patient) {
        Patient patient1 = cardMapper.queryPatientInfo(patient);
        CardResponseMsg cardResponseMsg = new CardResponseMsg();
        String msg = "";
        if (patient1 != null && patient1.getPid() > 0) {
            GroupClient groupClient = new GroupClient();
            groupClient.setGcid((int) patient1.getGcid());
            GroupClient groupClient1 = cardMapper.queryGroupClientById(groupClient);
            String pidentitynumber = patient1.getPidentitynumber() + "";
            String origoNo = pidentitynumber.substring(0, 2) + "0000";
            CardOrigo cardOrigo = new CardOrigo();
            cardOrigo.setOrigoNo(origoNo);
            CardOrigo cardOrigo1 = cardMapper.queryCardOrigoByOrigoNo(cardOrigo);
            patient1.setGcname(groupClient1.getGcname());
            patient1.setOrigoName(cardOrigo1.getOrigoName());
            msg = "success";
            cardResponseMsg.setObj(patient1);
        } else {
            msg = "failed";
        }
        cardResponseMsg.setMsg(msg);
        return cardResponseMsg;
    }

    @Override
    public CardResponseMsg queryCardInfo(Card card) {
        card.setCardState(1002);
        Card card1 = cardMapper.queryCardInfo(card);
        CardResponseMsg cardResponseMsg = new CardResponseMsg();
        String msg = "";
        if (card1 != null && card1.getCardId() > 0) {
            msg = "success";
            cardResponseMsg.setObj(card1);
        } else {
            msg = "failed";
        }
        cardResponseMsg.setMsg(msg);
        return cardResponseMsg;
    }

    @Override
    public String cardIssue(Card card) {
        card.setIssueDate(new Date(System.currentTimeMillis()));
        card.setCardState(1003);
        int rcordResult = cardMapper.cardIssue(card);
        String msg = rcordResult > 0 ? "success" : "failed";
        return msg;
    }

    @Override
    public String cardChange(Card card) {
        String msg = "";
        card.setCardState(1003);
        Card card1 = cardMapper.queryCardInfoByPcardnumber(card);
        if (card1 != null) {
            card.setIssueDate(new Date(System.currentTimeMillis()));
            card.setCardState(1003);
            int rcordResult = cardMapper.cardIssue(card);
            if (rcordResult > 0) {
                card1.setCardState(1004);
                cardMapper.cardOldUpdateAferChage(card1);
                CardChange cardChange = new CardChange();
                cardChange.setPcardnumber(card.getPcardnumber());
                cardChange.setCardOld(card1.getCardNo());
                cardChange.setCardNew(card.getCardNo());
                cardChange.setChageWid(card.getIssueWid());
                cardChange.setChangeDate(new Date(System.currentTimeMillis()));
                int index = cardMapper.insertCardChange(cardChange);
                msg = index > 0 ? "success" : "failed";
            } else {
                msg = "failed";
            }

        } else {
            msg = "failed";
        }
        return msg;
    }

    @Override
    public CardTableMsg queryCardCancel(CardAllConditionMsg conditionMsg) {
        int count = cardMapper.countCardCancel(conditionMsg);
        List cardCancelList = cardMapper.queryCardCancel(conditionMsg);
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(cardCancelList);
        cardTableMsg.setCount(count);
        return cardTableMsg;

    }

    @Override
    public CardResponseMsg cardCancelInsert(CardCancel cardCancel) {
        Date date = new Date(System.currentTimeMillis());
        cardCancel.setCancelDate(date);
        cardCancel.setApplyState(2001);
        Card card = new Card();
        card.setCardNo(cardCancel.getCardNo());
        card.setCardState(1002);
        CardResponseMsg cardResponseMsg = new CardResponseMsg();
        String msg = "";
//        按卡号及状态查询卡
        Card card1 = cardMapper.queryCardInfo(card);
        if (card1 != null && card1.getCardId() > 0) {
            int rcordResult = cardMapper.cardCancelInsertRrcord(cardCancel);
            if (rcordResult > 0) {
                msg = "success";
                cardResponseMsg.setObj(cardCancel.getCcancelId());
                card1.setCardState(1000);
                cardMapper.cardOldUpdateAferChage(card1);
            } else {
                msg = "failed";
            }
        } else {
            msg = "cannot";
        }
        cardResponseMsg.setMsg(msg);
        return cardResponseMsg;
    }

    @Override
    public String cardCancelBackout(CardCancel cardCancel) {
        int rcordResult = cardMapper.cardCancelRemove(cardCancel);
        Card card = new Card();
        card.setCardNo(cardCancel.getCardNo());
        card.setCardState(1002);
        cardMapper.cardOldUpdateAferChageByCardNo(card);
        String msg = "";
        if (rcordResult > 0) {
            msg = "success";
        } else {
            msg = "failed";
        }
        return msg;
    }

    @Override
    public String manageCardCancelPass(CardCancel cardCancel) {
        cardCancel.setAuditDate(new Date(System.currentTimeMillis()));
        cardCancel.setApplyState(2002);
        int rcordResult = cardMapper.updateCardCancelByID(cardCancel);
        Card card = new Card();
        card.setCardNo(cardCancel.getCardNo());
        card.setCardState(1005);
        cardMapper.cardOldUpdateAferChageByCardNo(card);
        String msg = "";
        if (rcordResult > 0) {
            msg = "success";
        } else {
            msg = "failed";
        }
        return msg;
    }

    @Override
    public String manageCardCancelBack(CardCancel cardCancel) {
        cardCancel.setAuditDate(new Date(System.currentTimeMillis()));
        cardCancel.setApplyState(2003);
        int rcordResult = cardMapper.updateCardCancelByID(cardCancel);
        Card card = new Card();
        card.setCardNo(cardCancel.getCardNo());
        card.setCardState(1002);
        cardMapper.cardOldUpdateAferChageByCardNo(card);
        String msg = "";
        if (rcordResult > 0) {
            msg = "success";
        } else {
            msg = "failed";
        }
        return msg;
    }

    @Override
    public CardTableMsg queryCardAllInfo(CardAllConditionMsg conditionMsg) {
        int count = cardMapper.countCardAllInfo(conditionMsg);
        List cardList = cardMapper.queryCardAllInfo(conditionMsg);
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(cardList);
        cardTableMsg.setCount(count);
        return cardTableMsg;
    }

    @Override
    public CardTableMsg queryWorkload(CardAllConditionMsg conditionMsg) {
        int count = cardMapper.countWorkload(conditionMsg);
        List workloadList = cardMapper.queryWorkload(conditionMsg);
        if (workloadList != null && workloadList.size() > 0) {
            for (int i = 0; i < workloadList.size(); i++) {
                CardWorkload workload = (CardWorkload) workloadList.get(i);
                workload.setIssueNos(workload.getTotalCard() - workload.getChangeNos());
            }
        }
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(workloadList);
        cardTableMsg.setCount(count);
        return cardTableMsg;
    }

}
