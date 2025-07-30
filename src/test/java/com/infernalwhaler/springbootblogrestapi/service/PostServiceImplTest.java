package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.payload.PostDto;
import com.infernalwhaler.springbootblogrestapi.payload.PostResponse;
import com.infernalwhaler.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.infernalwhaler.springbootblogrestapi.mapper.Mapper;
import com.infernalwhaler.springbootblogrestapi.model.Post;
import com.infernalwhaler.springbootblogrestapi.repository.IPostRepository;
import com.infernalwhaler.springbootblogrestapi.service.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
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
class PostServiceImplTest {

    private IPostRepository repository;
    private Mapper mapper;
    private PostServiceImpl service;


    @BeforeEach
    public void init() {
        repository = Mockito.mock(IPostRepository.class);
        mapper = new Mapper();
        service = new PostServiceImpl(repository, mapper);
    }

    @Test
    void shouldCreatePost() {
        final Post post = new Post(1L, "Title", "Description", "Content");
        final PostDto postDto = new PostDto(1L, "Title", "Description", "Content", null);

        when(repository.save(any()))
                .thenReturn(post);

        final PostDto savedPost = service.createPost(postDto);

        assertNotNull(savedPost);
        verify(repository).save(any());
    }

    @Test
    void shouldNotCreatePost() {
        final PostDto postDto = new PostDto(1L, "Title", "Description", "Content");

        when(repository.save(any()))
                .thenThrow(ResponseStatusException.class);

        assertThrows(ResponseStatusException.class, () -> service.createPost(postDto));
    }

    @Test
    void shouldFindAllPosts() {
        final Post post = new Post(1L, "AA", "Description", "Content");
        final Post post01 = new Post(2L, "Title01", "Description01", "Content01");
        final List<Post> posts = List.of(post, post01);
        final Pageable pageable = PageRequest.of(0, 5, Sort.by("content"));

        when(repository.findAll(pageable))
                .thenReturn(new PageImpl<>(posts));

        final PostResponse postResponse = service.findAllPosts(0, 5, "content", "asc");

        assertNotNull(postResponse);
        assertEquals("AA", postResponse.getContent().get(0).getTitle());
        assertEquals(2, postResponse.getContent().size());
    }

    @Test
    void shouldNotFindAllPosts() {
        final Pageable pageable = PageRequest.of(0, 5, Sort.by("title"));
        when(repository.findAll(pageable))
                .thenThrow(ResponseStatusException.class);

        assertThrows(ResponseStatusException.class, () -> service.findAllPosts(0, 5, "title", "asc"));
    }

    @Test
    void shouldFindById() {
        final Post post = new Post(1L, "Title", "Description", "Content");
        final PostDto postDto = new PostDto(1L, "Title", "Description", "Content", new HashSet<>());

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
                .thenThrow(ResponseStatusException.class);

        assertThrows(ResponseStatusException.class, () -> service.findById(1L));
    }

    @Test
    void shouldUpdatePost() {
        final Post post = new Post(1L, "Title", "Description", "Content");
        final Post postU = new Post(1L, "Title UPDATED", "Description UPDATED", "Content");
        final PostDto postDto = new PostDto(1L, "Title", "Description", "Content", new HashSet<>());
        final PostDto postDtoU = new PostDto(1L, "Title UPDATED", "Description UPDATED", "Content", new HashSet<>());

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