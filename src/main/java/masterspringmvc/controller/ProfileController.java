package masterspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 功能描述: TODO
 * 个人信息控制器
 * @author: 康小安
 * @date: 18-7-5 下午7:42
 */
@Controller
@RequestMapping("/twitter/profile")
public class ProfileController {

    @RequestMapping("")
    public String displayProfile(ProfileForm profileForm) {
        return "profile/profilePage";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveProfile(ProfileForm profileForm) {
        System.out.println("save ok" + profileForm);
        return "redirect:.";
    }
}
