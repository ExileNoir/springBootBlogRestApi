package com.infernalwhaler.springbootblogrestapi.dto;

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
public class CommentDto {

    private Long id;
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Body must be minimum 10 characters")
    private String body;
}
