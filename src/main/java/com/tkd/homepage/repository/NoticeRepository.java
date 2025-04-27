package com.tkd.homepage.repository;

import com.tkd.homepage.entity.Notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    
    // 최신 공지사항 5개 가져오기
    List<Notice> findTop5ByOrderByCreatedAtDesc();

    // 제목에 키워드가 포함된 공지사항 찾기
    Page<Notice> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    // 제목+내용 키워드가 포함된 공지사항 찾기
    Page<Notice> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String titleKeyword, String contentKeyword, Pageable pageable);
}
