package org.study.junyharang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Data @Builder public class MovieDTO {

    private Long mno;

    private String title;

    @Builder.Default private List<MovieImageDTO> imageDTOList = new ArrayList<>();

}// class 끝
