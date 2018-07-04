package masterspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能描述: TODO
 * HelloWord Controller
 * @author: 康小安
 * @date: 18-7-3 下午6:14
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(@RequestParam(defaultValue = "world") String name, Model model) {
        model.addAttribute("message", "Hello," + name);
        return "resultPage";
    }
}
