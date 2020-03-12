package com.cy.tpes.service.harmon;

import com.cy.tpes.entity.harmon.*;
import com.cy.tpes.entity.hwxbean.Patient;
import com.cy.tpes.entity.hwxbean.Worker;

import java.util.ArrayList;

public interface CardService {
    public String cardStorageInster(CardStorage cardStorage);

    public CardTableMsg queryCardStorage(CardAllConditionMsg conditionMsg);

    public String cardApplyInsert(CardApply cardApply);

    public String cardApplyUpdateByProposer(CardApply cardApply);

    public CardTableMsg queryCardApply(CardAllConditionMsg conditionMsg);

    public ArrayList<Worker> queryAllCardProposer(Worker worker);

    public String manageCardApply(CardAllConditionMsg conditionMsg);

    public ArrayList<Card> showCardInfoToAudit(Card card);

    public CardResponseMsg queryPatientInfo(Patient patient);

    public CardResponseMsg queryCardInfo(Card card);

    public String cardIssue(Card card);

    public String cardChange(Card card);

    public CardTableMsg queryCardCancel(CardAllConditionMsg conditionMsg);

    public CardResponseMsg cardCancelInsert(CardCancel cardCancel);

    public String cardCancelBackout(CardCancel cardCancel);

    public String manageCardCancelPass(CardCancel cardCancel);

    public String manageCardCancelBack(CardCancel cardCancel);


    public CardTableMsg queryCardAllInfo(CardAllConditionMsg conditionMsg);

    public CardTableMsg queryWorkload(CardAllConditionMsg conditionMsg);


}
