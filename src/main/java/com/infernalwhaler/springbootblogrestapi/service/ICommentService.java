package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.dto.CommentDto;

import java.util.List;

/**
 * ICommentService
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

public interface ICommentService {

    CommentDto createComment(final Long postId, final CommentDto commentDto);

    List<CommentDto> findCommentsByPostId(final Long postId);

    CommentDto findCommentById(final Long postId, final Long id);

    CommentDto updateComment(final Long postId, final Long id, final CommentDto commentRequest);

    void deleteComment(final Long postId, final Long id);
}
