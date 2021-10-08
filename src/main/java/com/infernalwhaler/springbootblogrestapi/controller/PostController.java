package com.infernalwhaler.springbootblogrestapi.controller;

import com.infernalwhaler.springbootblogrestapi.dto.PostDto;
import com.infernalwhaler.springbootblogrestapi.dto.PostResponse;
import com.infernalwhaler.springbootblogrestapi.service.IPostService;
import com.infernalwhaler.springbootblogrestapi.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @see PostServiceImpl#createPost(PostDto)
     */
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody final PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    /**
     * Get all Blog Posts Rest Api
     *
     * @param pageNo   of Object Integer
     * @param pageSize of Object Integer
     * @param sortBy   of Object String
     * @param sortDir  of Object String
     * @return ResponseEntity of PostResponse
     * @see IPostService#findAllPosts(Integer, Integer, String, String)
     * @see PostServiceImpl#findAllPosts(Integer, Integer, String, String)
     */
    @GetMapping
    public ResponseEntity<PostResponse> findAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) final Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) final Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) final String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) final String sortDir) {
        return new ResponseEntity<>(postService.findAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.FOUND);
    }

    /**
     * Find Post by ID Rest Api
     *
     * @param id of Object Long
     * @return ResponseEntity PostDto
     * @see IPostService#findById(Long)
     * @see PostServiceImpl#findById(Long) (long)
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
     * @see PostServiceImpl#updatePost(Long, PostDto)
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") final Long id,
                                              @RequestBody final PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(id, postDto), HttpStatus.OK);
    }

    /**
     * Delete Post Rest Api
     *
     * @param id of Object Long
     * @return ResponseEntity String
     * @see IPostService#deletePostById(Long)
     * @see PostServiceImpl#deletePostById(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") final Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted Successfully with id: '" + id + "'", HttpStatus.ACCEPTED);
    }
}
