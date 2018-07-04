package masterspringmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能描述: TODO
 * 访问Twitter控制器
 * @author: 康小安
 * @date: 18-7-4 下午3:49
 */
@Controller
public class TweetController {
    //这里twitterjar包有问题,无法自动注入所以改成手动配置,手动配置还是不行报500错误,暂时先放着

    String consumerKey = "JZw0zANiWj797ztWWC5TKLjyS";
    String consumerSecret = "V7Ah3H5689exSsa8jFjPZcbkMsXiyiuVnH9Ibo8FCRSjTz4Xag";
    String accessToken = "1014431059767906304-TWxlBZaYKgHGQdyju2vJ6SPna5EVmw";
    String accessTokenSecret = "y6bPeDlab4XdCpacbRoK7I5ehOfiF3PCuDtbsRXDsgwkO";
    private Twitter twitter;

    @RequestMapping("/twitter")
    public String hello(@RequestParam(defaultValue = "XiaoanMasterSpringMVC4") String search, Model model) {
        twitter = new TwitterTemplate(consumerKey,consumerSecret,accessToken,accessTokenSecret);
        SearchResults searchResults = twitter.searchOperations().search(search);
        String text = searchResults.getTweets().get(0).getText();
        model.addAttribute("message", text);

        return "twitterPage";
    }
}
