package com.cy.tpes.service.harmon;


import com.cy.tpes.entity.harmon.CardAllConditionMsg;
import com.cy.tpes.entity.harmon.CardTableMsg;
import com.cy.tpes.entity.harmon.SpiderNew;

import java.util.List;

/**
 * 新闻爬虫(SpiderNew)表服务接口
 *
 * @author makejava
 * @since 2020-03-09 23:27:02
 */
public interface SpiderNewService {

    public void truncateTable();

    public void insertNew(List<SpiderNew> spiderNews);

    public String updateNew(SpiderNew spiderNews);

    public CardTableMsg findAllNew(CardAllConditionMsg conditionMsg);

}