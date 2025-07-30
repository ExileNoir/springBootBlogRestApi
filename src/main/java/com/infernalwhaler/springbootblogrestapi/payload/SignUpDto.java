package com.infernalwhaler.springbootblogrestapi.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SignUp Dto Class
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private String name;

    private String username;

    private String email;

    private String password;
}
