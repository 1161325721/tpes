package com.cy.tpes.mapper.harmon;

import com.cy.tpes.entity.harmon.CardAllConditionMsg;
import com.cy.tpes.entity.harmon.SpiderNew;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpiderNewMapper {

    public void truncateTable();

    public void insertNew(List<SpiderNew> spiderNews);

    public int  updateNew(SpiderNew spiderNews);

    public List<SpiderNew> findAllNew(CardAllConditionMsg conditionMsg);
    public int countAllNew(CardAllConditionMsg conditionMsg);
}
