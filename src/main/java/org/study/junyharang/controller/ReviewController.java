package org.study.junyharang.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.study.junyharang.dto.ReviewDTO;
import org.study.junyharang.service.ReviewService;

import java.util.List;

@Log4j2 @RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {

        log.info("---------------Review 목록을 가져옵니다.-------------------");
        log.info("영화 번호 : " + mno);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);

    } // getList() 끝

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO movieReviewDTO) {

        log.info("---------------------영화 리뷰 등록이 시작됩니다!-----------------------");
        log.info("영화 리뷰 (reviewDTO) : " + movieReviewDTO);

        Long reviewNum = reviewService.reviewRegister(movieReviewDTO);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);

    } // addReview() 끝

    @PutMapping("/{mno}/{reviewNum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewNum, @RequestBody ReviewDTO movieReviewDTO) {

        log.info("----------------------- 리뷰 수정이 시작 되었습니다!--------------------------------");
        log.info("리뷰 정보 (reviewDTO) : " + movieReviewDTO);

        reviewService.reviewModify(movieReviewDTO);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);

    } // modifyReview() 끝

    @DeleteMapping("/{mno}/{reviewNum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewNum) {

        log.info("-----------------------리뷰 삭제가 시작 됩니다!---------------------------");
        log.info("삭제될 리뷰 번호(reviewNum) : " + reviewNum);

        reviewService.reviewRemove(reviewNum);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    } // removeReview() 끝

} // class 끝
