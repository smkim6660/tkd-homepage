package com.tkd.homepage.repository;

import com.tkd.homepage.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    
    // 최신 공지사항 5개 가져오기
    List<Notice> findTop5ByOrderByCreatedAtDesc();

    // 제목에 키워드가 포함된 공지사항 찾기
    List<Notice> findByTitleContainingIgnoreCase(String keyword);

    // 제목+내용 키워드가 포함된 공지사항 찾기
    List<Notice> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String titleKeyword, String contentKeyword);
}
