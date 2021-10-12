package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.payload.PostDto;
import com.infernalwhaler.springbootblogrestapi.payload.PostResponse;

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
     * @param pageNo   of Object Integer
     * @param pageSize of Object Integer
     * @param sortBy   of Object String
     * @param sortDir  of Object String
     * @return PostResponse Object
     */
    PostResponse findAllPosts(final Integer pageNo, final Integer pageSize, final String sortBy, final String sortDir);

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
