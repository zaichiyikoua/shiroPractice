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
    
    @RequestMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("msg", "add");
        return "add.html";
    }
    
    @RequestMapping(value = "/update")
    public String update(Model model) {
        model.addAttribute("msg", "update");
        return "update.html";
    }
}
