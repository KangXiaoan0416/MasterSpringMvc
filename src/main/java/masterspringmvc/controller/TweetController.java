package masterspringmvc.controller;

import masterspringmvc.util.TwitterUtil;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 功能描述: TODO
 * 访问Twitter控制器
 * @author: 康小安
 * @date: 18-7-4 下午3:49
 */
@Controller
@RequestMapping("")
public class TweetController {
    /**这里twitterjar包有问题,无法自动注入所以改成手动配置*/
    private Twitter twitter = TwitterUtil.initTwitter();

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
        SearchResults searchResults = twitter.searchOperations().search(search);

        //显示twitter信息列表
        List<Tweet> tweets = searchResults.getTweets();

        model.addAttribute("tweets", tweets);
        model.addAttribute("search", search);

        return "twitterPage";
    }
}
