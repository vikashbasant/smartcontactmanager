package com.smc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model m) {
        m.addAttribute ("title", "Home - Smart Contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model m) {
        m.addAttribute ("title", "About - Smart Contact Manager");
        return "about";
    }


    @RequestMapping("/signup")
    public String singUp(Model m) {
        m.addAttribute ("title", "Register - Smart Contact Manager");
        return "signUp";
    }


    @RequestMapping("/login")
    public String login(Model m) {
        m.addAttribute ("title", "Login - Smart Contact Manager");
        return "login";
    }
}
