package com.cy.tpes.service.harmon;

import com.cy.tpes.entity.harmon.*;

import java.util.List;

public interface ActivitiService {
    public CardTableMsg queryWorkerGroup(CardAllConditionMsg conditionMsg);

    public String addUser(CardUser cardUser);

    public String startVac(String userName, Vacation vac);

    public CardTableMsg myVac(String userName);

    public CardTableMsg myVacRecord(String userName);


    public CardTableMsg myAudit(String userName);

    public CardTableMsg myAuditRecord(String userName);

    public String passAudit(String userName, VacTask vacTask);

    public String queryUserPower(String userName);

}
