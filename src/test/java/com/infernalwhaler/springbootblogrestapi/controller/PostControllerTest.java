package com.infernalwhaler.springbootblogrestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infernalwhaler.springbootblogrestapi.dto.PostDto;
import com.infernalwhaler.springbootblogrestapi.service.IPostService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 6/10/2021
 */

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPostService postService;

    private final String URL_TEMPLATE = "/api/posts";

    private PostDto buildPostDto() {
        return new PostDto(1L, "Title", "Description", "Content");
    }

    private PostDto buildPostDto01() {
        return new PostDto(2L, "Title01", "Description01", "Content01");
    }

    @SneakyThrows
    private String mapperToJson(final PostDto postDto) {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(postDto);
    }

    @SneakyThrows
    private PostDto updatePostDto(final PostDto postDto) {
        postDto.setContent("updated Content");
        postDto.setDescription("UPDATED");
        postDto.setTitle("UPDATED TITLE");
        return postDto;
    }

    @SneakyThrows
    @Test
    void ShouldCreatePost() {
        final PostDto postDto = buildPostDto();

        when(postService.createPost(any(PostDto.class)))
                .thenReturn(postDto);

        mockMvc.perform(post(URL_TEMPLATE)
                        .contentType(APPLICATION_JSON)
                        .content(mapperToJson(postDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Title"))
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    @SneakyThrows
    @Test
    void findAllPosts() {
        final List<PostDto> postDtos = List.of(buildPostDto(), buildPostDto01());

        when(postService.findAllPosts())
                .thenReturn(postDtos);

        mockMvc.perform(get(URL_TEMPLATE)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.length()", is(2)))
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    @SneakyThrows
    @Test
    void updatePost() {
        when(postService.updatePost(1L, buildPostDto()))
                .thenReturn(updatePostDto(buildPostDto()));

        mockMvc.perform(put(URL_TEMPLATE + "/{id}", 1L)
                        .contentType(APPLICATION_JSON)
                        .content(mapperToJson(buildPostDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("UPDATED TITLE"))
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    @SneakyThrows
    @Test
    void deletePost() {
        Mockito.doNothing()
                .when(postService)
                .deletePostById(buildPostDto().getId());

        mockMvc.perform(delete(URL_TEMPLATE + "/{id}", 1L)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Post Deleted Successfully with id: '" + buildPostDto().getId() + "'"))
                .andDo(print())
                .andReturn()
                .getResponse();
    }

}