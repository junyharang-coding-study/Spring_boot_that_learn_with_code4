package org.study.junyharang.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString @Getter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity public class Movie extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String title;

} // class 끝
