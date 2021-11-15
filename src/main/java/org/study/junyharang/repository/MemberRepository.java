package org.study.junyharang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.junyharang.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
} // interface 끝
