package org.study.junyharang.service;

import org.study.junyharang.dto.MovieDTO;
import org.study.junyharang.dto.MovieImageDTO;
import org.study.junyharang.entity.Movie;
import org.study.junyharang.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO movieDTO);

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) { // DTO Type을 Map Type으로 반환

        HashMap<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie", movie);

        List<MovieImageDTO> movieImageList = movieDTO.getImageDTOList();

        //영화 이미지 DTO 처리
        if (movieImageList != null && movieImageList.size() > 0) { // 영화 이미지 DTO가 비어 있지 않다면?

            movieImageList.stream().map(movieImageDTO -> {

                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImageName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();

                return movieImage;

            }).collect(Collectors.toList());

            entityMap.put("imageList", movieImageList);
        } // if문 끝

        return entityMap;
    } // dtoToEntity() 끝

} // interface 끝
