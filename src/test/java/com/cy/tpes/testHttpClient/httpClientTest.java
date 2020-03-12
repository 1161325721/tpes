package com.cy.tpes.testHttpClient;

import com.cy.tpes.util.harmon.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class httpClientTest {
    @Autowired
    private HttpUtil httpUtil;
    @Test
    public void test01(){
        httpUtil = new HttpUtil();
//         String url = "https://www.baidu.com/s?rtt=1&bsst=1&cl=2&tn=news&rsv_dl=ns_pc&word=%E4%BD%93%E6%A3%80&x_bfe_rqs=03E80&x_bfe_tjscore=0.610691&tngroupname=organic_news&newVideo=12&pn=";
         String url = "https://news.sogou.com/news?mode=1&query=%CC%E5%BC%EC&sut=2548&lkt=0%2C0%2C0&sst0=1583810847707&page=";
        for (int i = 1; i <4 ; i++) {
            String searchUrl  = url +i;
            String resultHtml = httpUtil.doGetHtml(searchUrl);
//            parseDom(resultHtml);

        }
    }



}
