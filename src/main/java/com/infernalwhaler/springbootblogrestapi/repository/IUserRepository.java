package com.infernalwhaler.springbootblogrestapi.repository;

import com.infernalwhaler.springbootblogrestapi.payload.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * IUser Repository
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

public interface IUserRepository extends JpaRepository<User, Long> {

    /**
     * Find User by Email
     *
     * @param email of Object String
     * @return Optional of User object
     */
    Optional<User> findByEmail(final String email);

    /**
     * Find User by userName or Email
     *
     * @param username of Object String
     * @param email    of Object String
     * @return Optional of User object
     */
    Optional<User> findByUsernameOrEmail(final String username, final String email);

    /**
     * Find User by userName
     *
     * @param username of Object String
     * @return Optional of User object
     */
    Optional<User> findByUsername(final String username);

    /**
     * See if User exists by userName
     *
     * @param username of object String
     * @return Boolean object
     */
    Boolean existsByUsername(final String username);

    /**
     * See if User exists by Email
     *
     * @param email of Object String
     * @return Boolean object
     */
    Boolean existsByEmail(final String email);
}
