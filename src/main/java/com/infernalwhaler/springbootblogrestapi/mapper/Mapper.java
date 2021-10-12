package com.infernalwhaler.springbootblogrestapi.mapper;

import com.infernalwhaler.springbootblogrestapi.payload.CommentDto;
import com.infernalwhaler.springbootblogrestapi.payload.PostDto;
import com.infernalwhaler.springbootblogrestapi.model.Comment;
import com.infernalwhaler.springbootblogrestapi.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

@Component
public class Mapper {

    private final ModelMapper mapper = new ModelMapper();

    /**
     * Mapper to Post
     *
     * @param postDto of Object PostDto
     * @return Post Object
     */
    public Post mapToPost(final PostDto postDto) {
        return mapper.map(postDto, Post.class);
    }

    /**
     * Mapper to PostDto
     *
     * @param post of Object PostDto
     * @return PostDto Object
     */
    public PostDto mapToPostDto(final Post post) {
        return mapper.map(post, PostDto.class);
    }

    /**
     * Mapper to Comment
     *
     * @param commentDto of Object CommentDto
     * @return Comment Object
     */
    public Comment mapToComment(final CommentDto commentDto) {
        return mapper.map(commentDto, Comment.class);
    }

    /**
     * Mapper to CommentDto
     *
     * @param comment of Object Comment
     * @return CommentDto Object
     */
    public CommentDto mapToCommentDto(final Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }
}
