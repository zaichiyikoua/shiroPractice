package com.zaichiyikou.starter.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping(value = { "/", "/index" })
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello shiro");
        return "index.html";
    }

    @RequestMapping(value = "/user/add")
    public String add(Model model) {
        model.addAttribute("msg", "add");
        return "user/add.html";
    }

    @RequestMapping(value = "/user/update")
    public String update(Model model) {
        model.addAttribute("msg", "update");
        return "user/update.html";
    }

    @RequestMapping(value = "/tologin")
    public String toLogin(Model model) {
        model.addAttribute("msg", "login");
        return "login.html";
    }

    @RequestMapping(value = "/login")
    public String login(String username, String password, Model model) {

        // 1.获取当前用户 这个utils是shiro包中自带的
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户的登录信息
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 异常处理
        try {
            // 执行登录的方法
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {// 用户名不存在
            // TODO: handle exception
            e.printStackTrace();
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {// 密码错误
            // TODO: handle exception
            e.printStackTrace();
            model.addAttribute("msg", "密码错误");
            return "login";
        }
        
    }
}
