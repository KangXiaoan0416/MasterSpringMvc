package masterspringmvc.search;

import masterspringmvc.util.TwitterUtil;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述: TODO
 * 检索服务
 * @author: 康小安
 * @createDate: 18-11-16 下午3:58
 */
@Service
public class SearchService {

    /**自动注入有问题,参考TweetController*/
    private Twitter twitter = TwitterUtil.initTwitter();

    public List<Tweet> search(String searchType, List<String> keywords) {
        List<SearchParameters> searches = keywords.stream().map(taste -> createSearchParam(searchType, taste)).collect(Collectors.toList());
        List<Tweet> results = searches.stream().map(params -> twitter.searchOperations().search(params)).
                flatMap(searchResults -> searchResults.getTweets().stream()).collect(Collectors.toList());
        return results;

    }

    private SearchParameters.ResultType getResultType(String searchType) {
        for (SearchParameters.ResultType knownType : SearchParameters.ResultType.values()) {
            if(knownType.name().equalsIgnoreCase(searchType)) {
                return knownType;
            }
        }
        return SearchParameters.ResultType.RECENT;
    }
    
    /**
     * 获取搜索参数
     * @param searchType 检索类型
     * @param taste 检索字
     * @author 康小安
     * @createDate: 2018年11月16日 下午7:39
     * @return org.springframework.social.twitter.api.SearchParameters
     */
    private SearchParameters createSearchParam(String searchType, String taste) {
        SearchParameters.ResultType resultType = getResultType(searchType);
        SearchParameters searchParameters = new SearchParameters(taste);
        searchParameters.resultType(resultType);
        //返回结果列表大小
        searchParameters.count(10);
        return searchParameters;
    }

}


































