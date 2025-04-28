package com.tkd.homepage.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tkd.homepage.entity.Notice;
import com.tkd.homepage.repository.NoticeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeRepository noticeRepository;

    public NoticeController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    // 목록
    @GetMapping
    public String getList(@RequestParam(required = false) String keyword,
                          @RequestParam(required = false, defaultValue = "title") String mode, 
                          @RequestParam(defaultValue = "0") int page,
                          Model model) {
        Page<Notice> notices;
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "updatedAt"));

        if (keyword != null && !keyword.isEmpty()) {
            if ("all".equals(mode)) {
                notices = noticeRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword, pageable);
            } else {
                notices = noticeRepository.findByTitleContainingIgnoreCase(keyword, pageable);
            }
        } else {
            notices = noticeRepository.findAll(pageable);
        }

        model.addAttribute("notices", notices);
        model.addAttribute("keyword", keyword);
        model.addAttribute("mode", mode);

        return "notice/list";
    }

    // 새 글 작성 폼
    @GetMapping("/new")
    public String newNoticeForm(Model model) {
        model.addAttribute("notice", new Notice());
        return "notice/form";
    }

    // 작성 처리
    @PostMapping
    public String createNotice(@ModelAttribute Notice notice) {
        noticeRepository.save(notice);
        return "redirect:/notice";
    }

    // 상세 보기
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Notice notice = noticeRepository.findById(id).orElseThrow();
        model.addAttribute("notice", notice);
        return "notice/view";
    }

    // 수정
    @GetMapping("/{id}/edit")
    public String editNoticeForm(@PathVariable Long id, Model model) {
        Notice notice = noticeRepository.findById(id).orElseThrow();
        model.addAttribute("notice", notice);
        return "notice/edit-form";
    }

    // 수정 처리
    @PostMapping("/{id}/edit")
    public String updateNotice(@PathVariable Long id, @ModelAttribute Notice notice) {
        Notice existingNotice = noticeRepository.findById(id).orElseThrow();
        existingNotice.setTitle(notice.getTitle());
        existingNotice.setContent(notice.getContent());
        noticeRepository.save(existingNotice);
        return "redirect:/notice/" + id;
    }

    // 삭제 처리
    @PostMapping("/{id}/delete")
    public String deleteNotice(@PathVariable Long id) {
        noticeRepository.deleteById(id);
        return "redirect:/notice";
    }

}
