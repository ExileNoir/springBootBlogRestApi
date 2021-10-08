package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.controller.PostController;
import com.infernalwhaler.springbootblogrestapi.dto.PostDto;
import com.infernalwhaler.springbootblogrestapi.dto.PostResponse;
import com.infernalwhaler.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.infernalwhaler.springbootblogrestapi.mapper.MapperPost;
import com.infernalwhaler.springbootblogrestapi.model.Post;
import com.infernalwhaler.springbootblogrestapi.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Post Service
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 5/10/2021
 */

@Service
public class PostServiceImpl implements IPostService {

    private final IPostRepository postRepository;
    private final MapperPost mapperPost;

    @Autowired
    public PostServiceImpl(IPostRepository repository, MapperPost mapperPost) {
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
        if (!Objects.isNull(postRepository.findByTitle(postDto.getTitle()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post with Title: '" + postDto.getTitle() + "' Already Exists");
        }
        final Post post = mapperPost.mapToPost(postDto);
        final Post newPost = postRepository.save(post);
        return mapperPost.mapToPostDto(newPost);
    }

    /**
     * Get all Blog Posts
     *
     * @param pageNo   of Object Integer
     * @param pageSize of Object Integer
     * @param sortBy   of Object String
     * @param sortDir  of Object String
     * @return PostResponse Object
     * @see IPostService#findAllPosts(Integer, Integer, String, String)
     * @see PostController#findAllPosts(Integer, Integer, String, String)
     */
    @Override
    public PostResponse findAllPosts(final Integer pageNo, final Integer pageSize, final String sortBy, final String sortDir) {
        final Sort sort = sortDir.equalsIgnoreCase(Sort.DEFAULT_DIRECTION.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        final Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        final Page<Post> postPages = postRepository.findAll(pageable);
        if (postPages.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No post pages found");
        }

        final List<Post> posts = postPages.getContent();
        if (posts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts found");
        }

        final List<PostDto> postDtos = posts
                .stream()
                .map(mapperPost::mapToPostDto)
                .collect(Collectors.toList());

        return new PostResponse(postDtos, postPages.getNumber(), postPages.getSize(), postPages.getTotalElements(), postPages.getTotalPages(), postPages.isLast());
    }

    /**
     * Find Post by ID
     *
     * @param id of Object Long
     * @return PostDto
     * @see IPostService#findById(Long)
     * @see PostController#findPostById(Long)
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
