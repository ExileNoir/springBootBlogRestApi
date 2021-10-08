package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.controller.CommentController;
import com.infernalwhaler.springbootblogrestapi.dto.CommentDto;
import com.infernalwhaler.springbootblogrestapi.exceptions.BlogApiException;
import com.infernalwhaler.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.infernalwhaler.springbootblogrestapi.mapper.Mapper;
import com.infernalwhaler.springbootblogrestapi.model.Comment;
import com.infernalwhaler.springbootblogrestapi.model.Post;
import com.infernalwhaler.springbootblogrestapi.repository.ICommentRepository;
import com.infernalwhaler.springbootblogrestapi.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Comment Service Implementation
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

@Service
public class CommentServiceImpl implements ICommentService {

    private final ICommentRepository commentRepository;
    private final IPostRepository postRepository;
    private final Mapper mapper;


    @Autowired
    public CommentServiceImpl(ICommentRepository commentRepository, IPostRepository postRepository, Mapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    /**
     * Create Comment
     *
     * @param postId     of Object Long
     * @param commentDto of Object CommentDto
     * @return CommentDto
     * @see ICommentService#createComment(Long, CommentDto)
     * @see CommentController#createCommentDto(Long, CommentDto)
     */
    @Override
    public CommentDto createComment(final Long postId, final CommentDto commentDto) {
        final Comment commentToCreate = mapper.mapToComment(commentDto);
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        commentToCreate.setPost(post);

        final Comment commentToSave = commentRepository.save(commentToCreate);
        return mapper.mapToCommentDto(commentToSave);
    }

    /**
     * Find Comments by Post ID
     *
     * @param postId Of Object Long to retrieve Post with Comments
     * @return List of CommentDtos
     * @see ICommentService#findCommentsByPostId(Long)
     * @see CommentController#findCommentsByPostId(Long)
     */
    @Override
    public List<CommentDto> findCommentsByPostId(final Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(mapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    /**
     * Find Comments by ID
     *
     * @param postId of Object Long, as verification of existing Post
     * @param id     of Object long to retrieve the Comment
     * @return CommentDto
     * @see ICommentService#findCommentById(Long, Long)
     * @see CommentController#findCommentById(Long, Long)
     */
    @Override
    public CommentDto findCommentById(final Long postId, final Long id) {
        final Comment commentToUpdate = findValidComment(postId, id);
        return mapper.mapToCommentDto(commentToUpdate);
    }

    /**
     * Update Comment
     *
     * @param postId         of Object Long, as verification of existing Post
     * @param id             of Object long to retrieve the Comment
     * @param commentRequest new content to update
     * @return CommentDto
     * @see ICommentService#updateComment(Long, Long, CommentDto)
     * @see CommentController#updateComment(Long, Long, CommentDto)
     */
    @Override
    public CommentDto updateComment(final Long postId, final Long id, final CommentDto commentRequest) {
        final Comment commentToUpdate = findValidComment(postId, id);

        commentToUpdate.setName(commentRequest.getName());
        commentToUpdate.setEmail(commentRequest.getEmail());
        commentToUpdate.setBody(commentRequest.getBody());

        final Comment updatedComment = commentRepository.save(commentToUpdate);

        return mapper.mapToCommentDto(updatedComment);
    }

    /**
     * Delete Comment
     *
     * @param postId of Object Long, as verification of existing Post
     * @param id     of Object long to retrieve the Comment
     * @see ICommentService#deleteComment(Long, Long)
     * @see CommentController#deleteComment(Long, Long)
     */
    @Override
    public void deleteComment(final Long postId, final Long id) {
        final Comment commentToDelete = findValidComment(postId, id);
        commentRepository.delete(commentToDelete);
    }

    /**
     * Find valid Comment linked by existing Post
     *
     * @param postId of Object Long, as verification of existing Post
     * @param id     of Object long to retrieve the Comment
     * @return Comment
     */
    private Comment findValidComment(final Long postId, final Long id) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        final Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return comment;
    }
}
