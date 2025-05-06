package com.tkd.homepage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tkd.homepage.entity.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findByUsername(String username);
    
}
