package com.infernalwhaler.springbootblogrestapi.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * SignUp Dto Class
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@Data
@Api(value = "Sign Up model information")
public class SignUpDto {

    @ApiModelProperty(value = "Sign Up Name")
    private String name;

    @ApiModelProperty(value = "Sign Up Username")
    private String username;

    @ApiModelProperty(value = "Sign Up Email")
    private String email;

    @ApiModelProperty(value = "Sign Up Password")
    private String password;
}
