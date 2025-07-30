package com.infernalwhaler.springbootblogrestapi.controller;

import com.infernalwhaler.springbootblogrestapi.payload.LoginDto;
import com.infernalwhaler.springbootblogrestapi.payload.SignUpDto;
import com.infernalwhaler.springbootblogrestapi.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Sdeseure
 * @project springboot-blog-rest-api
 * @date 30/07/2025
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody final LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping(value = {"/signup","/register"})
    public ResponseEntity<String> signUp(@RequestBody final SignUpDto signUpDto) {
        return new ResponseEntity<>(authService.signUp(signUpDto), HttpStatus.CREATED);
    }
}
