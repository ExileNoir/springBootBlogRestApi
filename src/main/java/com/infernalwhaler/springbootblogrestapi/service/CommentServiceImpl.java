package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.dto.CommentDto;
import com.infernalwhaler.springbootblogrestapi.exceptions.BlogApiException;
import com.infernalwhaler.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.infernalwhaler.springbootblogrestapi.mapper.MapperComment;
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
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

@Service
public class CommentServiceImpl implements ICommentService {

    private final ICommentRepository commentRepository;
    private final IPostRepository postRepository;
    private final MapperComment mapper;


    @Autowired
    public CommentServiceImpl(ICommentRepository commentRepository, IPostRepository postRepository, MapperComment mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(final Long postId, final CommentDto commentDto) {
        final Comment commentToCreate = mapper.mapToComment(commentDto);
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        commentToCreate.setPost(post);

        final Comment commentToSave = commentRepository.save(commentToCreate);
        return mapper.mapToCommentDto(commentToSave);
    }

    @Override
    public List<CommentDto> findCommentsByPostId(final Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(mapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto findCommentById(final Long postId, final Long id) {
        final Comment commentToUpdate = findValidComment(postId, id);
        return mapper.mapToCommentDto(commentToUpdate);
    }

    @Override
    public CommentDto updateComment(final Long postId, final Long id, final CommentDto commentRequest) {
        final Comment commentToUpdate = findValidComment(postId, id);

        commentToUpdate.setName(commentRequest.getName());
        commentToUpdate.setEmail(commentRequest.getEmail());
        commentToUpdate.setBody(commentRequest.getBody());

        final Comment updatedComment = commentRepository.save(commentToUpdate);

        return mapper.mapToCommentDto(updatedComment);
    }

    @Override
    public void deleteComment(final Long postId, final Long id) {
        final Comment commentToDelete = findValidComment(postId, id);
        commentRepository.delete(commentToDelete);
    }

    private Comment findValidComment(final Long postId, final Long id) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        final Comment commentToUpdate = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));

        if (!commentToUpdate.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return commentToUpdate;
    }
}
