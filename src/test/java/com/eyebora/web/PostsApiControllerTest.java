package com.eyebora.web;

import com.eyebora.domain.post.Posts;
import com.eyebora.domain.post.PostsRepository;
import com.eyebora.web.dto.PostsSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PostsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    @DisplayName("Posts 등록된다.")
    @WithMockUser(roles = "USER")
    public void test_postCreate() throws Exception{
        // given
        String expectedTitle = "title";
        String expectedContent = "content";

        PostsSaveRequestDto requestDto
                = PostsSaveRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .author("author")
                .build();

        String url = "/api/v1/posts";

        //when
        mockMvc.perform(
                        post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(requestDto) )
                )
                .andExpect(status().isOk());

        List<Posts> postsList = postsRepository.findAll();

        //then
        assertThat( postsList.get(0).getTitle() ).isEqualTo(expectedTitle);
        assertThat( postsList.get(0).getContent() ).isEqualTo(expectedContent);
    }


}
