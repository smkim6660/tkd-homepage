package com.tkd.homepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "대한태권도협회 홈페이지에 오신 걸 환영합니다!");
        return "home";
    }
    
}
