package com.tian.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tian
 * @date 2020/8/21
 */
@Controller
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/noAuth")
    @ResponseBody
    public String noAuth() {
        return "未经授权不能登录";
    }


    @RequestMapping("/logout")
    public String toIndex1(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            model.addAttribute("msg","用户已退出登录");
            return "index";
        }
        model.addAttribute("msg","用户未登录");
        return "index";
    }

}
