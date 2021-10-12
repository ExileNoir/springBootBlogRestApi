package com.infernalwhaler.springbootblogrestapi.payload;

import lombok.Data;

/**
 * Login Dto Class
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@Data
public class LoginDto {

    private String usernameOrEmail;
    private String password;
}
