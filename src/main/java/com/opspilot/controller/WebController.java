package com.opspilot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Returns login.html
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Returns dashboard.html
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard"; // Redirect root to dashboard
    }
}