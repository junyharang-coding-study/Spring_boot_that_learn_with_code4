package org.study.junyharang.entity;

import lombok.*;

import javax.persistence.*;

@ToString(exclude = {"movie", "member"})
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNum;

    @ManyToOne(fetch = FetchType.LAZY) private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY) private Member member;

    private int grade;

    private String text;

} // class 끝
