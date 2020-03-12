package com.cy.tpes.util.harmon;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class HttpUtil {
    private PoolingHttpClientConnectionManager clientConnectionManager;

    public HttpUtil() {
        this.clientConnectionManager = new PoolingHttpClientConnectionManager();
        this.clientConnectionManager.setMaxTotal(100);
        this.clientConnectionManager.setDefaultMaxPerRoute(10);
    }

    public String doGetHtml(String url){
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.clientConnectionManager).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode()==200) {
                if (httpResponse.getEntity() != null) {
                    String content = EntityUtils.toString(httpResponse.getEntity(), "utf8");
                    return content;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    private RequestConfig getConfig() {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10000)
                .build();
        return requestConfig;
    }
}
