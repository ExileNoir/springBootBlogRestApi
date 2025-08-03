package com.infernalwhaler.springbootblogrestapi.payload;

/**
 * Login Dto Class
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */


public record LoginDto(String usernameOrEmail, String password) {
}
