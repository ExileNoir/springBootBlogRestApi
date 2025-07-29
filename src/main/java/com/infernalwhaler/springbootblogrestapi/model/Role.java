package com.infernalwhaler.springbootblogrestapi.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

/**
 * Role Object
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
