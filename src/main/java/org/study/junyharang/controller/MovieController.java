package org.study.junyharang.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.study.junyharang.dto.MovieDTO;
import org.study.junyharang.dto.PageRequestDTO;
import org.study.junyharang.service.MovieService;

@Log4j2 @RequestMapping("/movie") @RequiredArgsConstructor
@Controller public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register") public void register() {

    } // get register() 끝

    @PostMapping("/register") public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {

        log.info("영화 DTO 내용 : " + movieDTO);

        Long mno = movieService.register(movieDTO);

        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/movie/list";

    } // post register() 끝

    @GetMapping("/list") public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("pageRequestDTO 내용 : " + pageRequestDTO);

        model.addAttribute("result", movieService.getList(pageRequestDTO));
    } // list() 끝

    @GetMapping({"/read", "modify"}) public void read(
            long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("영화 고유 번호(mno) : " + mno);
        MovieDTO movieDTO = movieService.getMovie(mno);

        model.addAttribute("dto", movieDTO);
    } // read() 끝

} // class 끝
