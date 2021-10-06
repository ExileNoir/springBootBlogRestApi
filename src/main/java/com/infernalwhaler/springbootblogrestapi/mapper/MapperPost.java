package com.infernalwhaler.springbootblogrestapi.mapper;

import com.infernalwhaler.springbootblogrestapi.dto.PostDto;
import com.infernalwhaler.springbootblogrestapi.model.Post;
import org.springframework.stereotype.Component;

/**
 * Mapper for Posts
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 5/10/2021
 */

@Component
public class MapperPost {

    /**
     * Mapper for Post
     *
     * @param postDto
     * @return Post
     */
    public Post mapToPost(final PostDto postDto) {
        final Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    /**
     * Mapper for PostDto
     *
     * @param post
     * @return PostDto
     */
    public PostDto mapToPostDto(final Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getDescription(), post.getContent());
    }
}
