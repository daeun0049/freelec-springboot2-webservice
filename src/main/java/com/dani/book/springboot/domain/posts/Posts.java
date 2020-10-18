package com.dani.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    /*
       Posts : 실제 DB의 테이블과 매칭될 클래스 (Entity 클래스)

       @Entity
       테이블과 링크될 클래스임을 나타냄
       기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 매칭

       @Id
       해당 테이블의 PK 필드를 나타냄

       @Column
       테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됨
       사용이유 ? 기본값 외에 추가로 변경이 필요한 옵션이 있는 경우

       @NoArgsConstructor
       기본생성자 자동 추가 (public Postr(){} 와 같은 효과)

       @Getter
       클래스 내 모든 필드의 Getter 메소드를 자동생성

       @Builder
       해당 클래스의 빌더 패턴 클래스를 생성
       생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    */

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
