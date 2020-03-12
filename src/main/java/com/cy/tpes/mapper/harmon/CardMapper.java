package com.cy.tpes.mapper.harmon;

import com.cy.tpes.entity.harmon.*;
import com.cy.tpes.entity.hwxbean.Patient;
import com.cy.tpes.entity.hwxbean.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CardMapper {
    //    卡入库相关
    public int cardStorageInsterRrcord(CardStorage cardStorage);

    public int cardStorageInsterCard(List<Card> cardList);

    public int countCardStorage(CardAllConditionMsg conditionMsg);

    public ArrayList<CardApply> queryCardStorage(CardAllConditionMsg conditionMsg);

    //    卡申请相关
    public int cardApplyInsertRrcord(CardApply cardApply);

    public int cardApplyUpdateRrcord(CardApply cardApply);

    //    三表,内联,左外
    public int countCardApply(CardAllConditionMsg conditionMsg);

    public ArrayList<CardApply> queryCardApply(CardAllConditionMsg conditionMsg);

    public ArrayList<Worker> queryAllCardProposer(Worker worker);

    public ArrayList<Card> queryAllCard(Card card);

    //    按申请单分组查询
    public ArrayList<Card> queryCardByApplyId(Card card);

    public int cardUpdateToAudit(@Param("cardlist") List<Card> cardList, @Param("card") Card card);

    public Patient queryPatientInfo(Patient patient);

    public Card queryCardInfo(Card card);

    public Card queryCardInfoByPcardnumber(Card card);

    public int cardIssue(Card card);

    public int insertCardChange(CardChange cardChange);

    public int cardOldUpdateAferChage(Card card);

    public int cardOldUpdateAferChageByCardNo(Card card);

    public GroupClient queryGroupClientById(GroupClient groupClient);

    public CardOrigo queryCardOrigoByOrigoNo(CardOrigo cardOrigo);

    public int countCardCancel(CardAllConditionMsg conditionMsg);

    public ArrayList<CardCancel> queryCardCancel(CardAllConditionMsg conditionMsg);

    public int cardCancelInsertRrcord(CardCancel cardCancel);

    public int cardCancelRemove(CardCancel cardCancel);

    public int updateCardCancelByID(CardCancel cardCancel);


    public int countCardAllInfo(CardAllConditionMsg conditionMsg);

    public ArrayList<Card> queryCardAllInfo(CardAllConditionMsg conditionMsg);

    public int countWorkload(CardAllConditionMsg conditionMsg);

    public ArrayList<CardWorkload> queryWorkload(CardAllConditionMsg conditionMsg);

}
