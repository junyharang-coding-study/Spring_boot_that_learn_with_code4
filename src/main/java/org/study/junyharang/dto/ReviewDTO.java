package org.study.junyharang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder public class ReviewDTO {

    // review num
    private Long reviewNum;

    // Movie mno
    private Long mno;

    // Member id
    private Long mid;

    // Member nickname
    private String nickName;

    // Member email address
    private String email;

    private int grade;

    private String text;

    private LocalDateTime regDate, modDate;

} // class 끝
