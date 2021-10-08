package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.dto.CommentDto;

import java.util.List;

/**
 * IComment Service
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

public interface ICommentService {

    /**
     * Create Comment
     *
     * @param postId     of Object Long
     * @param commentDto of Object CommentDto
     * @return CommentDto
     */
    CommentDto createComment(final Long postId, final CommentDto commentDto);

    /**
     * Find Comments by Post ID
     *
     * @param postId Of Object Long to retrieve Post with Comments
     * @return List of CommentDtos
     */
    List<CommentDto> findCommentsByPostId(final Long postId);

    /**
     * Find Comment by ID
     *
     * @param postId of Object Long, as verification of existing Post
     * @param id     of Object long to retrieve the Comment
     * @return CommentDto
     */
    CommentDto findCommentById(final Long postId, final Long id);

    /**
     * Update Comment
     *
     * @param postId         of Object Long, as verification of existing Post
     * @param id             of Object long to retrieve the Comment
     * @param commentRequest new content to update
     * @return CommentDto
     */
    CommentDto updateComment(final Long postId, final Long id, final CommentDto commentRequest);

    /**
     * Delete Comment
     *
     * @param postId of Object Long, as verification of existing Post
     * @param id     of Object long to retrieve the Comment
     */
    void deleteComment(final Long postId, final Long id);
}
