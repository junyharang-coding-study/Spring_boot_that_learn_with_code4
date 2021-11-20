package org.study.junyharang.service;

import org.study.junyharang.dto.ReviewDTO;
import org.study.junyharang.entity.Member;
import org.study.junyharang.entity.Movie;
import org.study.junyharang.entity.Review;

import java.util.List;

public interface ReviewService {

    // 영화의 모든 영화 리뷰 가져오기 기능
    List<ReviewDTO> getListOfMovie(Long mno);

    // 영화 리뷰 추가 기능
    Long reviewRegister(ReviewDTO movieReviewDTO);

    // 특정 영화 리뷰 수정 기능
    void reviewModify(ReviewDTO movieReviewDTO);

    // 영화 리뷰 삭제 기능
    void reviewRemove(Long reviewNum);

    default Review dtoToEntity(ReviewDTO movieReviewDTO) {
        Review movieReview = Review.builder()
                .reviewNum(movieReviewDTO.getReviewNum())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .member(Member.builder().mid(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();

        return movieReview;

    } // dtoToEntity() 끝

    default ReviewDTO entityToDTO(Review movieReview) {

        ReviewDTO movieReviewDTO = ReviewDTO.builder()
                .reviewNum(movieReview.getReviewNum())
                .mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMember().getMid())
                .nickName(movieReview.getMember().getNickName())
                .email(movieReview.getMember().getEmail())
                .grade(movieReview.getGrade())
                .text(movieReview.getText())
                .regDate(movieReview.getRegDate())
                .modDate(movieReview.getModDate())
                .build();

        return movieReviewDTO;
    } // entityToDTO() 끝

} // interface 끝
