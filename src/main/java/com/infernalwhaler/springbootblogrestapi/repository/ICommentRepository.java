package com.infernalwhaler.springbootblogrestapi.repository;

import com.infernalwhaler.springbootblogrestapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * IComment Repository
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

public interface ICommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(final Long postId);
}
