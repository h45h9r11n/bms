package com.project.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {
    @GetMapping("/req/login")
    public String login() {
        return "login";
    }

    @GetMapping("/req/signup")
    public String register() {
        return "signup";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
