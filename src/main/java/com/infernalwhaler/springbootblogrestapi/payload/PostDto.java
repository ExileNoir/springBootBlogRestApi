package com.infernalwhaler.springbootblogrestapi.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Post Dto
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 5/10/2021
 */

@Data
@NoArgsConstructor
@ApiModel(value = "Post model information")
public class PostDto {

    @ApiModelProperty(value = "Blog post ID")
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    @ApiModelProperty(value = "Blog post Title")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    @ApiModelProperty(value = "Blog post Description")
    private String description;

    @NotEmpty
    @ApiModelProperty(value = "Blog post Content")
    private String content;

    @ApiModelProperty(value = "Blog post Comments")
    private Set<CommentDto> comments;


    public PostDto(Long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public PostDto(Long id, String title, String description, String content, Set<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.comments = comments;
    }
}
