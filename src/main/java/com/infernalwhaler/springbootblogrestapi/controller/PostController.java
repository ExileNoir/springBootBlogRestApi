package com.infernalwhaler.springbootblogrestapi.controller;

import com.infernalwhaler.springbootblogrestapi.dto.PostDto;
import com.infernalwhaler.springbootblogrestapi.service.IPostService;
import com.infernalwhaler.springbootblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Post Rest Controller
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 5/10/2021
 */

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    /**
     * Create Blog Post Rest Api
     *
     * @param postDto
     * @return ResponseEntity of PostDto
     * @see IPostService#createPost(PostDto)
     * @see PostService#createPost(PostDto)
     */
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody final PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    /**
     * Get all Blog Posts Rest Api
     *
     * @return ResponseEntity of List PostDtos
     * @see IPostService#findAllPosts()
     * @see PostService#findAllPosts()
     */
    @GetMapping
    public ResponseEntity<List<PostDto>> findAllPosts() {
        return new ResponseEntity<>(postService.findAllPosts(), HttpStatus.FOUND);
    }

    /**
     * Find Post by ID Rest Api
     *
     * @param id of Object Long
     * @return ResponseEntity PostDto
     * @see IPostService#findById(Long)
     * @see PostService#findById(Long) (long)
     */

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findPostById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.FOUND);
    }

    /**
     * Update Post Rest Api
     *
     * @param id      of Object Long
     * @param postDto of Object PostDto
     * @return ResponseEntity PostDto
     * @see IPostService#updatePost(Long, PostDto)
     * @see PostService#updatePost(Long, PostDto)
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") final Long id, @RequestBody final PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(id, postDto), HttpStatus.OK);
    }

    /**
     * Delete Post Rest Api
     *
     * @param id of Object Long
     * @return ResponseEntity String
     * @see IPostService#deletePostById(Long)
     * @see PostService#deletePostById(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") final Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted Successfully with id: '" + id + "'", HttpStatus.ACCEPTED);
    }
}
