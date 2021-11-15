package org.study.junyharang.entity;

import lombok.*;

import javax.persistence.*;

@ToString @Getter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "m_member")
@Entity public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    private String email;
    private String pw;
    private String nickName;
} // class 끝
