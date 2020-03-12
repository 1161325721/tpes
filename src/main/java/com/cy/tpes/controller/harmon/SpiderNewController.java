package com.cy.tpes.controller.harmon;

import com.cy.tpes.entity.harmon.CardAllConditionMsg;
import com.cy.tpes.entity.harmon.CardTableMsg;
import com.cy.tpes.entity.harmon.SpiderNew;
import com.cy.tpes.service.harmon.SpiderNewService;
import com.cy.tpes.util.harmon.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 新闻爬虫(SpiderNew)表控制层
 *
 * @author makejava
 * @since 2020-03-09 23:27:03
 */
@Controller
public class SpiderNewController {
    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private SpiderNewService spiderNewService;

    //搜索新闻
    @RequestMapping("/searchNew")
    public @ResponseBody
    String searchNew() {
        autoGetNew();
        return "success";
    }

    //    新闻信息查询
    @RequestMapping("/queryNew")
    public @ResponseBody
    CardTableMsg queryNew(CardAllConditionMsg conditionMsg) {
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        CardTableMsg cardTableMsg = spiderNewService.findAllNew(conditionMsg);
        return cardTableMsg;
    }
    //    新闻信息查询
    @RequestMapping("/queryNewToShow")
    public @ResponseBody
    CardTableMsg queryNewToShow(CardAllConditionMsg conditionMsg) {
        conditionMsg.setNewStatus(1001);
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        CardTableMsg cardTableMsg = spiderNewService.findAllNew(conditionMsg);
        return cardTableMsg;
    }
    //停止新闻
    @RequestMapping("/newStop")
    public @ResponseBody
    String newStop( SpiderNew spiderNew) {
        String msg = spiderNewService.updateNew(spiderNew);
        return msg;
    }

    //    自动抓取新闻
    @Scheduled(fixedDelay = 1000*60*60*24)
    public void autoGetNew() {
        spiderNewService.truncateTable();
        getNew();
        System.out.println("自动操作执行了");
    }
    //获取新闻页面
    private void getNew() {
        String url = "https://news.sogou.com/news?mode=1&query=%CC%E5%BC%EC&sut=2548&lkt=0%2C0%2C0&sst0=1583810847707&page=";
        for (int i = 1; i < 11; i++) {
            String searchUrl = url + i;
            String resultHtml = httpUtil.doGetHtml(searchUrl);
            parseDom(resultHtml);
        }

    }
    //解析新闻页面并插入数据库
    private void parseDom(String html) {
        Document dom = Jsoup.parse(html);
        String id = "a#uigs_";
        List<SpiderNew> spiderNews = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String selectId = id + i;
            Elements elements = dom.select(selectId);
            for (Element element : elements) {
                String newUrl = element.attr("href");
                String newTitle = element.text();
                SpiderNew spiderNew = new SpiderNew(newTitle, newUrl, 1001);
                spiderNews.add(spiderNew);
            }
        }
        spiderNewService.insertNew(spiderNews);
    }


}