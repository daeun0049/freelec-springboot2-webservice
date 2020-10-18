package com.dani.book.springboot.web;

import com.dani.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
* @RunWith : 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
*            스프링 부트 테스트와 JUnit 사이에 연결자 역할
* @WebMvcTest : Web에 집중할 수 있는 어노테이션 (@Controller, @ControllerAdvice 사용가능)
* @Autowired : 스프링이 관리하는 Bean을 주입 밥는다
*
* */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // 웹 API를 테스트 할때 사용
    // 스프링 MVC테스트의 시작점
    // HTTP GET, POST 등에 대한 API 테스트 가능
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // 체이닝이 지원되어 여러 검증기능을 이어서 선언할 수 있음
        mvc.perform(get("/hello"))
                // mvc.perform의 결과를 검증
                // HTTP Header의 Status를 검증
                // 200, 404, 500등의 상태 검증
                .andExpect(status().isOk())
                // mvc.perform의 결과를 검증
                // 응답 본문의 내용 검증
                // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.amount", is(amount)));
    }

}
