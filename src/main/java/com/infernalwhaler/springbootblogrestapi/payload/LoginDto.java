package com.infernalwhaler.springbootblogrestapi.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Login Dto Class
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@Data
@Api(value = "Login model information")
public class LoginDto {

    @ApiModelProperty(value = "Login Username or Email")
    private String usernameOrEmail;

    @ApiModelProperty(value = "Login Password")
    private String password;
}
