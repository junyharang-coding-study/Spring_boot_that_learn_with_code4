package org.study.junyharang.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.study.junyharang.dto.ReviewDTO;
import org.study.junyharang.entity.Movie;
import org.study.junyharang.entity.Review;
import org.study.junyharang.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor @Log4j2
@Service public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getListOfMovie(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        return result.stream().map(movieReview -> entityToDTO(movieReview)).collect(Collectors.toList());
    } // getListOfMovie() 끝

    @Override
    public Long reviewRegister(ReviewDTO movieReviewDTO) {
        Review movieReview = dtoToEntity(movieReviewDTO);

        reviewRepository.save(movieReview);

        return movieReview.getReviewNum();
    } // reviewRegister() 끝

    @Override
    public void reviewModify(ReviewDTO movieReviewDTO) {
        Optional<Review> result = reviewRepository.findById(movieReviewDTO.getReviewNum());

        if (result.isPresent()) {
            Review movieReview = result.get();

            movieReview.changeGrade(movieReviewDTO.getGrade());
            movieReview.changeText(movieReviewDTO.getText());

            reviewRepository.save(movieReview);
        } // if문 끝
    } // reviewModify() 끝

    @Override
    public void reviewRemove(Long reviewNum) {
        reviewRepository.deleteById(reviewNum);
    } // reviewRemove() 끝
} // class 끝
