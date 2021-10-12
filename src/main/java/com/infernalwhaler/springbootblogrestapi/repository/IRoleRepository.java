package com.infernalwhaler.springbootblogrestapi.repository;

import com.infernalwhaler.springbootblogrestapi.payload.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * IRole Repository
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

public interface IRoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find a Role by its name
     *
     * @param name of Object String
     * @return Optional of object Role
     */
    Optional<Role> findByName(final String name);
}
