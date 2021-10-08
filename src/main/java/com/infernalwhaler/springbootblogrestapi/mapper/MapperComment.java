package com.infernalwhaler.springbootblogrestapi.mapper;

import com.infernalwhaler.springbootblogrestapi.dto.CommentDto;
import com.infernalwhaler.springbootblogrestapi.model.Comment;
import org.springframework.stereotype.Component;

/**
 * Mapper for Comments
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

@Component
public class MapperComment {

    /**
     * Mapper to Comment
     *
     * @param commentDto of Object CommentDto
     * @return Comment Object
     */
    public Comment mapToComment(final CommentDto commentDto) {
        return new Comment(commentDto.getId(), commentDto.getName(), commentDto.getEmail(), commentDto.getBody());
    }

    /**
     * Mapper to CommentDto
     *
     * @param comment of Object Comment
     * @return CommentDto Object
     */
    public CommentDto mapToCommentDto(final Comment comment) {
        return new CommentDto(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody());
    }
}
