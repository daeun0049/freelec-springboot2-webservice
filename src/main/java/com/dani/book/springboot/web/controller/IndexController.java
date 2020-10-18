package com.dani.book.springboot.web.controller;

import com.dani.book.springboot.domain.posts.Posts;
import com.dani.book.springboot.service.posts.PostsService;
import com.dani.book.springboot.web.dto.PostsResponseDto;
import com.dani.book.springboot.web.dto.PostsSaveRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostsService postsService;

    @RequestMapping("/index")
    public String index() { return "index"; }

    @RequestMapping("/test")
    public String test() { return "test"; }

    @RequestMapping("/enroll")
    public @ResponseBody Long enroll(@RequestParam HashMap<String, String> param) {
        PostsSaveRequestsDto requestsDto = PostsSaveRequestsDto.builder()
                .title(param.get("title"))
                .content(param.get("content"))
                .author(param.get("author"))
                .build();

        Long userId = postsService.save(requestsDto);
        return userId;
    }

    @RequestMapping("/list")
    public @ResponseBody PostsResponseDto list(@RequestParam("id") Long userId) {
        PostsResponseDto dto = postsService.findById(userId);
        return dto;
    }

}
