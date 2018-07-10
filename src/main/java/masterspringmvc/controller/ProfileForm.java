package masterspringmvc.controller;

import masterspringmvc.date.PastLocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: TODO
 * 个人信息数据传输对象
 * @author: 康小安
 * @date: 18-7-5 下午7:44
 */
public class ProfileForm {
    /**用户名*/
    @Size(min = 2)
    private String twitterHandle;
    /**邮件地址*/
    @Email
    @NotEmpty
    private String email;
    /**出生地址*/
    @NotNull
    @PastLocalDate
    private LocalDate birthDate;
    /**口味列表*/
    @NotEmpty
    private List<String> tastes = new ArrayList<>();

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getTastes() {
        return tastes;
    }

    public void setTastes(List<String> tastes) {
        this.tastes = tastes;
    }
}
