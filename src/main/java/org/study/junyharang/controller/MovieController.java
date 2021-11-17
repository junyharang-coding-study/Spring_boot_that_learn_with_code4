package org.study.junyharang.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2 @RequestMapping("/movie")
@Controller public class MovieController {

    @GetMapping("/register") public void register() {

    } // register() 끝

} // class 끝