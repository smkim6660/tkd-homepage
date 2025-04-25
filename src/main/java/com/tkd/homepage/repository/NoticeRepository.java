package com.tkd.homepage.repository;

import com.tkd.homepage.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    
    // 최신 공지사항 5개 가져오기
    List<Notice> findTop5ByOrderByCreatedAtDesc();
}
