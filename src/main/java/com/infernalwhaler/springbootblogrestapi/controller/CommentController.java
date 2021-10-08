package com.infernalwhaler.springbootblogrestapi.controller;

import com.infernalwhaler.springbootblogrestapi.dto.CommentDto;
import com.infernalwhaler.springbootblogrestapi.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class CommentController {

    private final ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createCommentDto(@PathVariable("postId") final Long postId,
                                                       @RequestBody final CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> findCommentsByPostId(@PathVariable("postId") final Long postId) {
        return new ResponseEntity<>(commentService.findCommentsByPostId(postId), HttpStatus.FOUND);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> findCommentById(@PathVariable("postId") final Long postId,
                                                      @PathVariable("id") final Long id) {
        return new ResponseEntity<>(commentService.findCommentById(postId, id), HttpStatus.FOUND);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") final Long postId,
                                                    @PathVariable("id") final Long id,
                                                    @RequestBody final CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(postId, id, commentDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") final Long postId,
                                                @PathVariable("id") final Long id) {
        commentService.deleteComment(postId, id);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
