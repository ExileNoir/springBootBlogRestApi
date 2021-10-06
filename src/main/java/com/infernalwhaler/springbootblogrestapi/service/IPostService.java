package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.dto.PostDto;

import java.util.List;

/**
 * IPost Service
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 5/10/2021
 */

public interface IPostService {

    /**
     * Create Post
     *
     * @param postDto of Object PostDto
     * @return PostDto
     */
    PostDto createPost(final PostDto postDto);

    /**
     * Find all Posts
     *
     * @return List of PostDtos
     */
    List<PostDto> findAllPosts();

    /**
     * Find Post by ID
     *
     * @param id of Object Long
     * @return PostDto
     */
    PostDto findById(final Long id);

    /**
     * Updates a Post
     *
     * @param id      of Object Long
     * @param postDto of Object PostDto
     * @return PostDto
     */
    PostDto updatePost(final Long id, final PostDto postDto);

    /**
     * Deletes a Post
     *
     * @param id of Object Long
     */
    void deletePostById(final Long id);
}
