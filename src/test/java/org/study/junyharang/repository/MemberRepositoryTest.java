package org.study.junyharang.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.study.junyharang.entity.Member;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest public class MemberRepositoryTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private ReviewRepository reviewRepository;

    @Test public void 회원_등록() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("junyharang" + i + "@junyharang.com")
                    .pw("1111")
                    .nickName("주니하랑" + i)
                    .build();

            memberRepository.save(member);
        });

    } // 회원_등록() 끝

    @Commit @Transactional @Test public void 회원_탈퇴() {

        Long mid = 101L;  // Member의 고유번호

        Member member = Member.builder().mid(mid).build();

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);

    } // 회원_탈퇴() 끝
} // class 끝