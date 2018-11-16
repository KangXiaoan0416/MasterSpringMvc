package masterspringmvc.controller;

import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Properties;


/**
 * 功能描述: TODO
 * 访问Twitter控制器
 * @author: 康小安
 * @date: 18-7-4 下午3:49
 */
@Controller
@RequestMapping("/twitter")
public class TweetController {
    //这里twitterjar包有问题,无法自动注入所以改成手动配置,手动配置还是不行报500错误,暂时先放着

    String consumerKey = "JZw0zANiWj797ztWWC5TKLjyS";
    String consumerSecret = "V7Ah3H5689exSsa8jFjPZcbkMsXiyiuVnH9Ibo8FCRSjTz4Xag";
    String accessToken = "1014431059767906304-TWxlBZaYKgHGQdyju2vJ6SPna5EVmw";
    String accessTokenSecret = "y6bPeDlab4XdCpacbRoK7I5ehOfiF3PCuDtbsRXDsgwkO";

    private Twitter twitter;

    @RequestMapping("")
    public String home() {
        return "searchPage";
    }

    @RequestMapping(value="/postSearch", method = RequestMethod.POST)
    public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String search = request.getParameter("search");
        if(search.toLowerCase().contains("struts")) {
            //这里最开始写错了,应该是flashAttribute,我写成了attribute
            redirectAttributes.addFlashAttribute("error", "Try using spring instead");
            //哈哈,.代表当前目录,试出来了
            return "redirect:.";
        }
        redirectAttributes.addAttribute("search", search);
        return "redirect:result";
    }

    @RequestMapping("/result")
    public String hello(@RequestParam(defaultValue = "xiaoan") String search, Model model) {
        //设置代理,需要在创建twitter对象之前设置,之前放的位置不对,造成做了很多无用功
        setProxy();
        twitter = new TwitterTemplate(consumerKey,consumerSecret,accessToken,accessTokenSecret);
        SearchResults searchResults = twitter.searchOperations().search(search);

        //显示twitter信息列表
        List<Tweet> tweets = searchResults.getTweets();

        model.addAttribute("tweets", tweets);
        model.addAttribute("search", search);

        return "twitterPage";
    }

    /**设置代理*/
    private void setProxy() {
        Properties props = System.getProperties();
        props.put("http.proxyHost", "127.0.0.1");
        props.put("http.proxyPort", "1080");
        props.put("https.proxyHost", "127.0.0.1");
        props.put("https.proxyPort", "1080");

        // System.setProperty("http.proxyHost", "127.0.0.1");
        // System.setProperty("http.proxyPort", "8118");
        // System.setProperty("https.proxyHost", "127.0.0.1");
        // System.setProperty("https.proxyPort", "8118");
    }
}
