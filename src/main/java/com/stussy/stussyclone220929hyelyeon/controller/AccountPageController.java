package com.stussy.stussyclone220929hyelyeon.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/account")
@Configuration
public class AccountPageController {

    @GetMapping("/login")
    public String login() {

        return "account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }
}
