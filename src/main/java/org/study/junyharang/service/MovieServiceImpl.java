package org.study.junyharang.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.junyharang.dto.MovieDTO;
import org.study.junyharang.dto.PageRequestDTO;
import org.study.junyharang.dto.PageResponseDTO;
import org.study.junyharang.entity.Movie;
import org.study.junyharang.entity.MovieImage;
import org.study.junyharang.repository.MovieImageRepository;
import org.study.junyharang.repository.MovieRepository;

import java.util.*;
import java.util.function.Function;

@RequiredArgsConstructor @Log4j2
@Service public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Transactional @Override
    public Long register(MovieDTO movieDTO) {
        Map<String, Object> entityMap = dtoToEntity(movieDTO);

        Movie movie = (Movie) entityMap.get("movie");

        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");
        
        movieRepository.save(movie);
        
        movieImageList.forEach(movieImage -> {
            movieImageRepository.save(movieImage);
        });
        
        return movie.getMno();
    } // register() 끝

    @Override
    public PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);

        Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
                (Movie) arr[0], (List<MovieImage>) (Arrays.asList((MovieImage)arr[1])), (Double) arr[2], (Long) arr[3]));

        return new PageResponseDTO<>(result, fn);
    } // getList() 끝

    @Override
    public MovieDTO getMovie(Long mno) {
        List<Object[]> result = movieRepository.getMovieWithAll(mno);

        // Movie Entity는 가장 앞에 존재 - 모든 Row가 동일 값
        Movie movie = (Movie) result.get(0)[0];

        List<MovieImage> movieImageList = new ArrayList<>();

        result.forEach(arr -> {
            MovieImage movieImage = (MovieImage) arr[1];
            movieImageList.add(movieImage);
        });

        // 평군 평점 - 모든 Row가 동일한 값
        Double avg = (Double) result.get(0)[2];
        // 리뷰 개수 - 모든 Row가 동일한 값
        Long reviewCnt = (Long) result.get(0)[3];

        return entitiesToDTO(movie, movieImageList, avg, reviewCnt);
    } // getMovie() 끝
} // class 끝
