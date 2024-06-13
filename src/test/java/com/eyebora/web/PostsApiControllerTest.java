package com.eyebora.web;

import com.eyebora.domain.post.Posts;
import com.eyebora.domain.post.PostsRepository;
import com.eyebora.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("Posts 등록된다.")
    public void test_postCreate() {
        // given
        String expectedTitle = "title";
        String expectedContent = "content";

        PostsSaveRequestDto requestDto
                = PostsSaveRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .author("author")
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity
                = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat( responseEntity.getStatusCode() ).isEqualTo(HttpStatus.OK);
        assertThat( responseEntity.getBody() ).isGreaterThan( 0L );

        List<Posts> postsList = postsRepository.findAll();

        assertThat( postsList.get(0).getTitle() ).isEqualTo(expectedTitle);
        assertThat( postsList.get(0).getContent() ).isEqualTo(expectedContent);
    }
}
