package org.study.junyharang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Data @Builder public class MovieDTO {

    private Long mno;

    private String title;

    @Builder.Default private List<MovieImageDTO> imageDTOList = new ArrayList<>();

    //영화의 평균 평점
    private double avg;

    //Review 수 [jpa의 count()]
    private int reviewCnt;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}// class 끝
