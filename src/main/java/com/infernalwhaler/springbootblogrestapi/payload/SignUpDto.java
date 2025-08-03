package com.infernalwhaler.springbootblogrestapi.payload;


/**
 * SignUp Dto Class
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

public record SignUpDto(String name, String username, String email, String password) {
}
