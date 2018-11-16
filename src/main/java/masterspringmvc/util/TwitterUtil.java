package masterspringmvc.util;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.Properties;

/**
 * tweet工具类
 * 功能描述: TODO
 * @author: 康小安 
 * @createDate: 18-11-16 下午4:46
 */
public class TwitterUtil {
    private static String consumerKey = "JZw0zANiWj797ztWWC5TKLjyS";
    private static String consumerSecret = "V7Ah3H5689exSsa8jFjPZcbkMsXiyiuVnH9Ibo8FCRSjTz4Xag";
    private static String accessToken = "1014431059767906304-TWxlBZaYKgHGQdyju2vJ6SPna5EVmw";
    private static String accessTokenSecret = "y6bPeDlab4XdCpacbRoK7I5ehOfiF3PCuDtbsRXDsgwkO";

    /**
     * 初始化twitter请求对象 国内需要设置代理
     * (设置代理需要在初始化twitter对象之前设置,所以思考,初始化过程中发生过请求,建立连接)
     * @author 康小安
     * @createDate: 2018年11月16日 下午4:48
     * @return org.springframework.social.twitter.api.Twitter
     */
    public static Twitter initTwitter() {
        //设置代理,需要在创建twitter对象之前设置,之前放的位置不对,造成做了很多无用功
        TwitterUtil.setProxy();
        Twitter twitter = new TwitterTemplate(consumerKey,consumerSecret,accessToken,accessTokenSecret);
        return twitter;
    }

    /**
     * 设置代理
     * @author 康小安
     * @createDate: 2018年11月16日 下午4:44
     * @return void
     */
    public static void setProxy() {
        Properties props = System.getProperties();
        props.put("http.proxyHost", "127.0.0.1");
        props.put("http.proxyPort", "8118");
        props.put("https.proxyHost", "127.0.0.1");
        props.put("https.proxyPort", "8118");

        //　代理8118是linux系统(linux使用的proxy插件代理)　8080是window端口
        // System.setProperty("http.proxyHost", "127.0.0.1");
        // System.setProperty("http.proxyPort", "8118");
        // System.setProperty("https.proxyHost", "127.0.0.1");
        // System.setProperty("https.proxyPort", "8118");
    }
}
