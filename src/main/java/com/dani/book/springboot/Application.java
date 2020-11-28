package com.dani.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
* Application : 메인클래스
* SpringBootApplication : 스프링 부트의 자동설정, 스프링 Bean 읽기 생성 자동설정. (프로젝트 최상단에 위치해야 함)
* */

//@EnableJpaAuditing // JPA Aditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enable", "false");
        System.setProperty("spring.devtools.livereload.enable", "true");

        //내장 WAS 실행
        SpringApplication.run(Application.class, args);
    }
}
