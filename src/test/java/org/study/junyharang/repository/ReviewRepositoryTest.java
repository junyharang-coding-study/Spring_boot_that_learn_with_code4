package org.study.junyharang.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.study.junyharang.entity.Member;
import org.study.junyharang.entity.Movie;
import org.study.junyharang.entity.Review;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest public class ReviewRepositoryTest {

    @Autowired private ReviewRepository reviewRepository;

    @Test public void 영화리뷰_등록() {
        // 200개 리뷰 등록
        IntStream.rangeClosed(1, 200).forEach(i -> {

            // 영화 번호
            long mno = (long) (Math.random() * 100) + 1;

            // Reviewer 번호
                Long mid = ((long) (Math.random() * 200) + 1);

            // Member Table 설계 시 실수로 지웠다가 다시하는 바람에 DB에서 101부터 mid 시작
                if(mid <= 100) { // mid가 100보다 작다면?
                    // mid에 100을 더해라
                    mid += 100;
                } // if문 끝
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .member(member)
                    .movie(
                            Movie.builder().mno(mno).build())
                    .grade(
                            ((int) (Math.random() * 5) + 1))
                    .text("이 영화는 이래요!" + i)
                    .build();

            reviewRepository.save(review);
        });
    } // 영화리뷰_등록() 끝

} // class 끝