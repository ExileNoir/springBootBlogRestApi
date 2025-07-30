package com.infernalwhaler.springbootblogrestapi.service;

import com.infernalwhaler.springbootblogrestapi.payload.LoginDto;
import com.infernalwhaler.springbootblogrestapi.payload.SignUpDto;

/**
 * @author Sdeseure
 * @project springboot-blog-rest-api
 * @date 30/07/2025
 */

public interface IAuthService {

    String login(final LoginDto loginDto);

    String signUp(final SignUpDto signUpDto);
}
