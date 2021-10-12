package com.infernalwhaler.springbootblogrestapi.controller;

import com.infernalwhaler.springbootblogrestapi.payload.PostDto;
import com.infernalwhaler.springbootblogrestapi.payload.PostResponse;
import com.infernalwhaler.springbootblogrestapi.service.IPostService;
import com.infernalwhaler.springbootblogrestapi.service.PostServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Post Rest Controller
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 5/10/2021
 */

@RestController
@RequestMapping("/api/posts")
@Api(value = "CRUD Rest APIs for Post Resources")
public class PostController {

    private final IPostService postService;

    @Autowired
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
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create Post REST API")
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
    @ApiOperation(value = "Find All Posts REST API")
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
    @ApiOperation(value = "Find Post By ID REST API")
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
    @ApiOperation(value = "Update Post By ID REST API")
    @PreAuthorize("hasRole('ADMIN')")
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
    @ApiOperation(value = "Delete Post By ID REST API")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePost(@PathVariable("id") final Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted Successfully with id: '" + id + "'", HttpStatus.ACCEPTED);
    }
}
