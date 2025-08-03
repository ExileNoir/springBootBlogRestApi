package com.infernalwhaler.springbootblogrestapi.payload;


import java.util.List;

/**
 * Post Response
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 7/10/2021
 */


public record PostResponse(List<PostDto> content, int pageNo, int pageSize, long totalElements, int totalPages,
                           boolean last) {
}
