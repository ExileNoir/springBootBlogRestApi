package com.infernalwhaler.springbootblogrestapi.repository;

import com.infernalwhaler.springbootblogrestapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * IPost Repository
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 5/10/2021
 */

public interface IPostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(final String postTitle);
}
