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
     * Mapper to Post
     *
     * @param postDto of Object PostDto
     * @return Post Object
     */
    public Post mapToPost(final PostDto postDto) {
        return new Post(postDto.getTitle(), postDto.getDescription(), postDto.getContent());
    }

    /**
     * Mapper to PostDto
     *
     * @param post of Object PostDto
     * @return PostDto Object
     */
    public PostDto mapToPostDto(final Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getDescription(), post.getContent());
    }
}
