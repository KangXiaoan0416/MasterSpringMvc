package masterspringmvc.internet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ProxyClient {

    public static void main(String[] args) throws ClientProtocolException, IOException {


        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //代理对象
        HttpHost proxy = new HttpHost("127.0.0.1", 8118, "http");
        //配置对象
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

        //创建请求方法的实例， 并指定请求url
        HttpGet httpget = new HttpGet("https://api.twitter.com/1.1/search/tweets.json");
        //使用配置
        httpget.setConfig(config);

        CloseableHttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity, "utf-8");
        System.out.println("----------------------------------------");
        System.out.println(content);
        System.out.println("-----------------------------------------");
        httpClient.close();

    }
}
