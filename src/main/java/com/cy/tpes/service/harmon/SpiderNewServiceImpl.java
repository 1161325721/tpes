package com.cy.tpes.service.harmon;


import com.cy.tpes.entity.harmon.CardAllConditionMsg;
import com.cy.tpes.entity.harmon.CardTableMsg;
import com.cy.tpes.entity.harmon.SpiderNew;
import com.cy.tpes.mapper.harmon.SpiderNewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 新闻爬虫(SpiderNew)表服务实现类
 *
 * @author makejava
 * @since 2020-03-09 23:27:03
 */
@Service()
public class SpiderNewServiceImpl implements SpiderNewService {
 @Autowired
 private SpiderNewMapper spiderNewMapper;
    @Override
    public void truncateTable() {
        spiderNewMapper.truncateTable();

    }

    @Override
    public void insertNew(List<SpiderNew> spiderNews) {
        spiderNewMapper.insertNew(spiderNews);

    }

    @Override
    public String updateNew(SpiderNew spiderNews) {
        int i = spiderNewMapper.updateNew(spiderNews);
        String msg = i>0?"success":"fail";
        return msg;
    }

    @Override
    public CardTableMsg findAllNew(CardAllConditionMsg conditionMsg) {
        int count = spiderNewMapper.countAllNew(conditionMsg);
        List spiderNews = spiderNewMapper.findAllNew(conditionMsg);
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(spiderNews);
        cardTableMsg.setCount(count);
        return cardTableMsg;
    }


}