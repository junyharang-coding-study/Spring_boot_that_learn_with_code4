package org.study.junyharang.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.study.junyharang.entity.Movie;
import org.study.junyharang.entity.MovieImage;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired MovieRepository movieRepository;
    @Autowired MovieImageRepository movieImageRepository;

    @Commit @Transactional @Test
    public void 영화정보_등록() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("영화제목" + i)
                    .build();

        System.out.println("---------------------------------");

        movieRepository.save(movie);

        // 영화 사진을 최대 5개까지 임의 처리하기 위해 선언
        int count = (int) (Math.random() * 5) + 1;

        for(int j = 0; j < count; j++) {
            MovieImage movieImage = MovieImage.builder()
                    .uuid(UUID.randomUUID().toString())
                    .movie(movie)
                    .imgName("테스트 중 입니다!" + j + ".jpg")
                    .build();

            movieImageRepository.save(movieImage);
        } // for 문 끝

            System.out.println("===================================");
        });

    } // 영화정보_등록() 끝

    @Test public void 목록_페이지() {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        } // for문 끝

    } // 목록_페이지() 끝

    @Test public void 영화_관련_정보_가져오기() {

        List<Object[]> result = movieRepository.getMovieWithAll(94L);

        System.out.println(result);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        } // for문 끝

    } // 영화_관련_정보_가져오기() 끝
} // class 끝