package com.cy.tpes.mapper.harmon;

import com.cy.tpes.entity.harmon.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ActivitiMapper {

    public int countUserGroup(CardAllConditionMsg conditionMsg);

    public ArrayList<CardUser> queryUserGroup(CardAllConditionMsg conditionMsg);

    public CardUser queryUserPower(CardUser cardUser);

}
