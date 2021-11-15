package org.study.junyharang.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.study.junyharang.entity.Member;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest public class MemberRepositoryTest {

    @Autowired private MemberRepository memberRepository;

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
} // class 끝