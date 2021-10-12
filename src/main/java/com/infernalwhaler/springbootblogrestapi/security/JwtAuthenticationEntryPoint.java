package com.infernalwhaler.springbootblogrestapi.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Json Web Token Authentication EntryPoint class
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * This method is called whenever an Exception is thrown
     * due to an unauthenticated User
     * tyring to access a resource that requires authentication
     *
     * @param request  of Object HttpServletRequest
     * @param response of Object HttpServletResponse
     * @param exc      of Object AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException exc) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exc.getMessage());
    }
}
