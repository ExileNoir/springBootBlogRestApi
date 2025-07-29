package com.infernalwhaler.springbootblogrestapi.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * JWT authentication response DTO
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@Getter
@Setter
public class JwtAuthResponseDto {

    private String accessToken;

    private String tokenType = "Bearer";

    public JwtAuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
