package com.dani.book.springboot.config.auth;

import com.dani.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        * authorizeRequests : URL 별 권한관리를 설정하는 옵션의 시작점
        * antMatchers : 권한 관리 대상을 지정하는 옵션. URL, HTTP 메소드별로 관리 가능.
        * */
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/" ,"/css/**", "/images/**",
                            "/js/**","h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() // OAuth2 로그인기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() // UAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                            .userService(customOAuth2UserService);
                            // 로그인 성공 시 후속 조시를 진행항 UserService 인터페이스의 구현체 등록.
                            // 리소스서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시가능.
    }

}
