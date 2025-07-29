package com.infernalwhaler.springbootblogrestapi.payload;


import lombok.Data;

/**
 * SignUp Dto Class
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@Data
public class SignUpDto {

    private String name;

    private String username;

    private String email;

    private String password;
}
