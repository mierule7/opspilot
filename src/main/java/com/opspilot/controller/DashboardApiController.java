package com.opspilot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DashboardApiController {

    @GetMapping("/secure-test")
    public String secureTest() {
        return "This is a highly secure response from OpsPilot!";
    }
}