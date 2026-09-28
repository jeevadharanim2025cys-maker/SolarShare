package com.solarshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/households")
    public String households() {
        return "households";
    }

    @GetMapping("/generation")
    public String generation() {
        return "generation";
    }

    @GetMapping("/consumption")
    public String consumption() {
        return "consumption";
    }

    @GetMapping("/ledger")
    public String ledger() {
        return "ledger";
    }

    @GetMapping("/summary")
    public String summary() {
        return "summary";
    }
}