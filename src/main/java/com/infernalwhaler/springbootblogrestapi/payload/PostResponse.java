package com.infernalwhaler.springbootblogrestapi.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Post Response
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 7/10/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "Post Response model information")
public class PostResponse {

    @ApiModelProperty(value = "Post Response  Content of Post Models")
    private List<PostDto> content;

    @ApiModelProperty(value = "Post Response  Page Number")
    private int pageNo;

    @ApiModelProperty(value = "Post Response  Page Size")
    private int pageSize;

    @ApiModelProperty(value = "Post Response  Total Elements")
    private long totalElements;

    @ApiModelProperty(value = "Post Response  Total Pages")
    private int totalPages;

    @ApiModelProperty(value = "Post Response  is Last Page")
    private boolean last;
}
