package com.infernalwhaler.springbootblogrestapi.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
@Api(value = "JWT Auth Response model information")
public class JwtAuthResponseDto {

    @ApiModelProperty(value = "JwtAuthResponse Token")
    private String accessToken;

    @ApiModelProperty(value = "JwtAuthResponse Token Type")
    private String tokenType = "Bearer";

    public JwtAuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
