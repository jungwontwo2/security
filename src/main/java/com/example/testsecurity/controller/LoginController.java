package com.example.testsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginP() {
        return "login";
    }
    @PostMapping("/loginProc")
    public String loginProc() {
        return"/admin";
    }
}
