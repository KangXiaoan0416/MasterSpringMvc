package masterspringmvc.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 功能描述: TODO 检索控制器
 * @author: 康小安
 * @createDate: 18-11-16 下午3:57
 */
@Controller
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/search/{searchType}")
    public ModelAndView search(@PathVariable String searchType, @MatrixVariable List<String> keywords) {
        List<Tweet> tweets = searchService.search(searchType, keywords);
        //这里和书上的返回页面不一样,是重新写了个列表页接收数据
        ModelAndView modelAndView = new ModelAndView("twitterPage");
        modelAndView.addObject("tweets", tweets);
        modelAndView.addObject("search", String.join(",",keywords));
        return modelAndView;
    }
}










