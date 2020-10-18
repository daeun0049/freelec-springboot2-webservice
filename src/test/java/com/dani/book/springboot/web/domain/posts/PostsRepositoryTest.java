package com.dani.book.springboot.web.domain.posts;


import com.dani.book.springboot.domain.posts.Posts;
import com.dani.book.springboot.domain.posts.PostsRepository;
import javafx.geometry.Pos;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트게시글";
        String content = "테스트 본문";

        // 데이블 posts에 insert/update 쿼리를 실행
        // id 값이 있다면 update, 없다면 insert 쿼리가 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());

        // 테이블 posts에 있는 모든 데이터를 조회해 오는 메소드
        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2020, 10, 18, 0,0,0);
        postsRepository.save(Posts.builder()
                    .title("title")
                    .content("content")
                    .author("author")
                    .build());

        // when
        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);

        System.out.println(" >>>>>>>> createData=" + posts.getCreateData()+", modifiedDate="+posts.getModifiedDate());
        assertThat(posts.getCreateData()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
