package com.infernalwhaler.springbootblogrestapi.controller;

import com.infernalwhaler.springbootblogrestapi.payload.PostDto;
import com.infernalwhaler.springbootblogrestapi.payload.PostResponse;
import com.infernalwhaler.springbootblogrestapi.service.IPostService;
import com.infernalwhaler.springbootblogrestapi.service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


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

    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }


    /**
     * Create Blog Post Rest Api
     *
     * @param postDto request body
     * @return ResponseEntity of PostDto
     * @see IPostService#createPost(PostDto)
     * @see PostServiceImpl#createPost(PostDto)
     */
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody final PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    /**
     * Find all Blog Posts Rest Api
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
     * @param id of Object Long to retrieve Post
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
     * @param id      of Object Long to retrieve Post
     * @param postDto of Object PostDto to update Post
     * @return ResponseEntity of updated PostDto
     * @see IPostService#updatePost(Long, PostDto)
     * @see PostServiceImpl#updatePost(Long, PostDto)
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") final Long id,
                                              @Valid @RequestBody final PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(id, postDto), HttpStatus.OK);
    }

    /**
     * Delete Post Rest Api
     *
     * @param id of Object Long to retrieve Post
     * @return ResponseEntity of Object String message
     * @see IPostService#deletePostById(Long)
     * @see PostServiceImpl#deletePostById(Long)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") final Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted Successfully with id: '" + id + "'", HttpStatus.ACCEPTED);
    }
}
