package com.Library.library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Library.library_management.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

}