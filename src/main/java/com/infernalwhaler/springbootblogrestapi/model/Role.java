package com.infernalwhaler.springbootblogrestapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Role Object
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */


@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
