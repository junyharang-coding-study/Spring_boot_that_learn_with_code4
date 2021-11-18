package org.study.junyharang.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.junyharang.dto.MovieDTO;
import org.study.junyharang.entity.Movie;
import org.study.junyharang.entity.MovieImage;
import org.study.junyharang.repository.MovieImageRepository;
import org.study.junyharang.repository.MovieRepository;

import java.util.*;

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
} // class 끝
