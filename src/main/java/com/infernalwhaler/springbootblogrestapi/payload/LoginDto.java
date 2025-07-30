package com.infernalwhaler.springbootblogrestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Login Dto Class
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String usernameOrEmail;

    private String password;
}
