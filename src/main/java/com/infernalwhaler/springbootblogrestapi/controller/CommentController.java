package com.infernalwhaler.springbootblogrestapi.controller;

import com.infernalwhaler.springbootblogrestapi.payload.CommentDto;
import com.infernalwhaler.springbootblogrestapi.service.CommentServiceImpl;
import com.infernalwhaler.springbootblogrestapi.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Comment Rest Controller
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

@RestController
@RequestMapping("/api/")
@Api(value = "CRUD REST APIs For Comment Resource")
public class CommentController {

    private final ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Create Comment Rest Api
     *
     * @param postId     of Object Long
     * @param commentDto request body
     * @return ResponseEntity of CommentDto
     * @see ICommentService#createComment(Long, CommentDto)
     * @see CommentServiceImpl#createComment(Long, CommentDto)
     */
    @PostMapping("/posts/{postId}/comments")
    @ApiOperation(value = "Create Comment REST API")
    public ResponseEntity<CommentDto> createCommentDto(@PathVariable("postId") final Long postId,
                                                       @Valid @RequestBody final CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    /**
     * Find all Comments linked to a Post Rest Api
     *
     * @param postId of Object Long, as verification of existing Post
     * @return ResponseEntity of CommentDtos
     * @see ICommentService#findCommentsByPostId(Long)
     * @see CommentServiceImpl#findCommentsByPostId(Long)
     */
    @GetMapping("/posts/{postId}/comments")
    @ApiOperation(value = "Find All Comments By Post ID REST API")
    public ResponseEntity<List<CommentDto>> findCommentsByPostId(@PathVariable("postId") final Long postId) {
        return new ResponseEntity<>(commentService.findCommentsByPostId(postId), HttpStatus.FOUND);
    }

    /**
     * Find Comment by ID Rest Api
     *
     * @param postId of Object Long, as verification of existing Post
     * @param id     of Object long to retrieve the Comment
     * @return ResponseEntity of CommentDto
     * @see ICommentService#findCommentById(Long, Long)
     * @see CommentServiceImpl#findCommentById(Long, Long)
     */
    @GetMapping("/posts/{postId}/comments/{id}")
    @ApiOperation(value = "Find Single Comments By ID REST API")
    public ResponseEntity<CommentDto> findCommentById(@PathVariable("postId") final Long postId,
                                                      @PathVariable("id") final Long id) {
        return new ResponseEntity<>(commentService.findCommentById(postId, id), HttpStatus.FOUND);
    }

    /**
     * Update Comment Rest Api
     *
     * @param postId     of Object Long, as verification of existing Post
     * @param id         of Object long to retrieve the Comment
     * @param commentDto new content to update
     * @return ResponseEntity of updated CommentDto
     * @see ICommentService#updateComment(Long, Long, CommentDto)
     * @see CommentServiceImpl#updateComment(Long, Long, CommentDto)
     */
    @PutMapping("/posts/{postId}/comments/{id}")
    @ApiOperation(value = "Update Single Comments By ID REST API")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") final Long postId,
                                                    @PathVariable("id") final Long id,
                                                    @Valid @RequestBody final CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(postId, id, commentDto), HttpStatus.ACCEPTED);
    }

    /**
     * Delete Comment Rest Api
     *
     * @param postId of Object Long, as verification of existing Post
     * @param id     of Object long to retrieve the Comment
     * @return ResponseEntity  of Object String message
     * @see ICommentService#deleteComment(Long, Long)
     * @see CommentServiceImpl#deleteComment(Long, Long)
     */
    @DeleteMapping("/posts/{postId}/comments/{id}")
    @ApiOperation(value = "Delete Single Comments By ID REST API")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") final Long postId,
                                                @PathVariable("id") final Long id) {
        commentService.deleteComment(postId, id);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
