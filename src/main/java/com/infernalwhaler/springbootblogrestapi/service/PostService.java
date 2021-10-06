package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.controller.PostController;
import com.infernalwhaler.springbootblogrestapi.dto.PostDto;
import com.infernalwhaler.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.infernalwhaler.springbootblogrestapi.mapper.MapperPost;
import com.infernalwhaler.springbootblogrestapi.model.Post;
import com.infernalwhaler.springbootblogrestapi.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Post Service
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 5/10/2021
 */

@Service
public class PostService implements IPostService {

    private IPostRepository postRepository;
    private MapperPost mapperPost;

    @Autowired
    public PostService(IPostRepository repository, MapperPost mapperPost) {
        this.postRepository = repository;
        this.mapperPost = mapperPost;
    }

    /**
     * Create Blog Post
     *
     * @param postDto of Object PostDto
     * @return PostDto
     * @see IPostService#createPost(PostDto)
     * @see PostController#createPost(PostDto)
     */
    @Override
    public PostDto createPost(final PostDto postDto) {
        final Post post = mapperPost.mapToPost(postDto);
        final Post newPost = postRepository.save(post);
        return mapperPost.mapToPostDto(newPost);
    }

    /**
     * Get all Blog Posts
     *
     * @return List of PostDtos
     * @see IPostService#findAllPosts()
     * @see PostController#findAllPosts()
     */
    @Override
    public List<PostDto> findAllPosts() {
        final List<Post> postDtos = postRepository.findAll();
        if (postDtos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return postDtos
                .stream()
                .map(mapperPost::mapToPostDto)
                .collect(Collectors.toList());
    }

    /**
     * Find Post by ID
     *
     * @param id of Object Long
     * @return PostDto
     * @see IPostService#findById(Long)
     * @see PostController#findById(Long)
     */
    @Override
    public PostDto findById(final Long id) {
        return postRepository.findById(id)
                .map(mapperPost::mapToPostDto)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    /**
     * Updates a Post
     *
     * @param id      of Object Long
     * @param postDto of Object PostDto
     * @return PostDto
     * @see IPostService#updatePost(Long, PostDto)
     * @see PostController#updatePost(Long, PostDto)
     */
    @Override
    public PostDto updatePost(final Long id, final PostDto postDto) {
        final Post postToUpdate = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        postToUpdate.setTitle(postDto.getTitle());
        postToUpdate.setDescription(postDto.getDescription());
        postToUpdate.setContent(postDto.getContent());

        final Post postUpdated = postRepository.save(postToUpdate);

        return mapperPost.mapToPostDto(postUpdated);
    }

    /**
     * Deletes a Post
     *
     * @param id of Object Long
     * @see IPostService#deletePostById(Long)
     * @see PostController#deletePost(Long)
     */
    @Override
    public void deletePostById(final Long id) {
        final Post postToDelete = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        postRepository.delete(postToDelete);
    }
}
