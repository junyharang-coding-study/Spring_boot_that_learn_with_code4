package org.study.junyharang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.junyharang.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
} // interface 끝
