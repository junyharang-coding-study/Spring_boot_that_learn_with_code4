package org.study.junyharang.service;

import org.study.junyharang.dto.MovieDTO;
import org.study.junyharang.dto.MovieImageDTO;
import org.study.junyharang.dto.PageRequestDTO;
import org.study.junyharang.dto.PageResponseDTO;
import org.study.junyharang.entity.Movie;
import org.study.junyharang.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO movieDTO);
    
    // 목록 처리
    PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO);

    // 조회 페이지
    MovieDTO getMovie(Long mno);

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) { // DTO Type을 Map Type으로 반환

        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie", movie);

        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

        //영화 이미지 DTO 처리
        if (imageDTOList != null && imageDTOList.size() > 0) { // 영화 이미지 DTO가 비어 있지 않다면?

            List<MovieImage> movieImageList = imageDTOList.stream().map(movieImageDTO -> {

                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImageName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();

                return movieImage;

            }).collect(Collectors.toList());

            entityMap.put("imgList", movieImageList);
        } // if문 끝

        return entityMap;
    } // dtoToEntity() 끝
    
    default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> movieImages, Double avg, Long reviewCnt) {
        MovieDTO movieDTO = MovieDTO.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                .regDate(movie.getRegDate())
                .modDate(movie.getModDate())
                .build();

        List<MovieImageDTO> movieImageDTOList = movieImages.stream().map(movieImage -> {
            return MovieImageDTO.builder()
                    .imageName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .uuid(movieImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        //책에서는 아래와 같이 Setter를 썼으나, Setter사용을 자제하기 위해 주니하랑은 아래와 같이 Builder 사용
//        movieDTO.setImageDTOList(movieImageDTOList);
//        movieDTO.setAvg(avg);
//        movieDTO.setReviewCnt(reviewCnt.intValue());
//
//        return movieDTO;
        
        MovieDTO movieDTOBuilder = movieDTO.builder().imageDTOList(movieImageDTOList)
                .avg(avg)
                .reviewCnt(reviewCnt.intValue())
                .build();
        
        return movieDTOBuilder;
    } // entitiesToDTO() 끝
} // interface 끝
