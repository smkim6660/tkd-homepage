package com.tkd.homepage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tkd.homepage.entity.Notice;
import com.tkd.homepage.repository.NoticeRepository;


@Controller
public class HomeController {

    private final NoticeRepository noticeRepository;

    public HomeController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
    
    @GetMapping("/")
    public String home(Model model) {
        List<Notice> recentNotices = noticeRepository.findTop5ByOrderByCreatedAtDesc();
        model.addAttribute("notices", recentNotices);
        return "home";
    }
    
}
