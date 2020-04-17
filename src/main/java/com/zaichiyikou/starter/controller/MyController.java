package com.zaichiyikou.starter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping(value = {"/","/index"})
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
    
    @RequestMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("msg", "login");
        return "login.html";
    }
}
