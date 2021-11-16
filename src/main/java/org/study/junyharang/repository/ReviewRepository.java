package org.study.junyharang.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.study.junyharang.entity.Member;
import org.study.junyharang.entity.Movie;
import org.study.junyharang.entity.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    // member의 Loding 방식만 EAGER로 처리하고, 나머지는 LAZY 방식으로 처리
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    // 회원을 이용해서 Review 삭제 Method
    @Modifying @Query("delete from Review mr where mr.member =:member")
    void deleteByMember(Member member);

} // interface 끝
