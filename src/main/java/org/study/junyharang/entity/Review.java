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

    public void changeGrade(int grade) { // 리뷰 평점 수정 기능
        this.grade = grade;
    } // changeGrade() 끝

    public void changeText(String text) { // 리뷰 수정 기능
        this.text = text;
    } // changeText() 끝

} // class 끝
