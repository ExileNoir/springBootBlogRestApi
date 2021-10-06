package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.dto.PostDto;
import com.infernalwhaler.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.infernalwhaler.springbootblogrestapi.mapper.MapperPost;
import com.infernalwhaler.springbootblogrestapi.model.Post;
import com.infernalwhaler.springbootblogrestapi.repository.IPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 6/10/2021
 */

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostServiceTest {

    private IPostRepository repository;
    private MapperPost mapper;
    private PostService service;


    @BeforeEach
    public void init() {
        repository = Mockito.mock(IPostRepository.class);
        mapper = new MapperPost();
        service = new PostService(repository, mapper);
    }

    @Test
    void shouldCreatePost() {
        final Post post = new Post(1L, "Title", "Description", "Content");
        final PostDto postDto = new PostDto(1L, "Title", "Description", "Content");

        when(repository.save(any()))
                .thenReturn(post);

        final PostDto savedPost = service.createPost(postDto);

        assertNotNull(savedPost);
        verify(repository).save(any());
    }

    @Test
    void shouldNotCreatePost() {
        final PostDto postDto = new PostDto(1L, "Title", "Description", "Content");

        when(repository.findById(any()))
                .thenThrow(ResponseStatusException.class);

        assertThrows(ResponseStatusException.class, () -> service.createPost(postDto));
    }

    @Test
    void shouldFindAllPosts() {
        final Post post = new Post(1L, "Title", "Description", "Content");
        final Post post01 = new Post(1L, "Title01", "Description01", "Content01");
        final List<Post> posts = List.of(post01, post);

        when(repository.findAll()).thenReturn(posts);

        final List<PostDto> postDtos = service.findAllPosts();

        assertNotNull(postDtos);
        assertEquals(2, postDtos.size());
    }

    @Test
    void shouldNotFindAllPosts() {
        when(repository.findAll())
                .thenThrow(ResponseStatusException.class);

        assertThrows(ResponseStatusException.class, () -> service.findAllPosts());
    }

    @Test
    void shouldFindById() {
        final Post post = new Post(1L, "Title", "Description", "Content");
        final PostDto postDto = new PostDto(1L, "Title", "Description", "Content");

        when(repository.findById(1L))
                .thenReturn(Optional.of(post));

        final PostDto pDtoById = service.findById(1L);

        assertNotNull(pDtoById);
        assertEquals(postDto, pDtoById);
    }

    @Test
    void shouldNotFindById() {
        final Post post = new Post(1L, "Title", "Description", "Content");
        final PostDto postDto = new PostDto(1L, "Title", "Description", "Content");

        when(repository.findById(1L))
                .thenReturn(Optional.of(post));

        final PostDto pDtoById = service.findById(1L);

        assertNotNull(pDtoById);
        assertEquals(postDto, pDtoById);
    }

    @Test
    void shouldUpdatePost() {
        final Post post = new Post(1L, "Title", "Description", "Content");
        final Post postU = new Post(1L, "Title UPDATED", "Description UPDATED", "Content");
        final PostDto postDto = new PostDto(1L, "Title", "Description", "Content");
        final PostDto postDtoU = new PostDto(1L, "Title UPDATED", "Description UPDATED", "Content");

        when(repository.findById(1L))
                .thenReturn(Optional.of(post));
        when(repository.save(post))
                .thenReturn(postU);

        final PostDto postDtoToUpdate = service.updatePost(1L, postDto);

        assertNotNull(postDtoToUpdate);
        assertEquals(postDtoU, postDtoToUpdate);
    }

    @Test
    void shouldNotUpdatePost() {
        final PostDto postDto = new PostDto(1L, "Title", "Description", "Content");

        when(repository.findById(1L))
                .thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> service.updatePost(1L, postDto));
    }


    @Test
    void shouldDeletePostById() {
        final Post post = new Post(1L, "Title", "Description", "Content");

        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(post));

        service.deletePostById(1L);

        verify(repository).delete(post);
    }

    @Test
    void shouldNotDeleteById() {
        assertThrows(ResourceNotFoundException.class, () -> service.deletePostById(1L));
    }
}