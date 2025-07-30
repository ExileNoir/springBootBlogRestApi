package com.infernalwhaler.springbootblogrestapi.repository;

import com.infernalwhaler.springbootblogrestapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * IComment Repository
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

public interface ICommentRepository extends JpaRepository<Comment, Long> {

    /**
     * find Comments By Post ID
     *
     * @param postId Of Object Long to retrieve Post with Comments
     * @return List of Comments
     */
    Optional<List<Comment>> findByPostId(final Long postId);
}
