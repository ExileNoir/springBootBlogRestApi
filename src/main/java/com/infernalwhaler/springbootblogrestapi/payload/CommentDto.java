package com.infernalwhaler.springbootblogrestapi.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Comment Dto
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "Comment model information")
public class CommentDto {

    @ApiModelProperty(value = "Comment ID")
    private Long id;

    @NotEmpty(message = "Name should not be null or empty")
    @ApiModelProperty(value = "Comment Name")
    private String name;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    @ApiModelProperty(value = "Comment Email")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Body must be minimum 10 characters")
    @ApiModelProperty(value = "Comment Body")
    private String body;
}
