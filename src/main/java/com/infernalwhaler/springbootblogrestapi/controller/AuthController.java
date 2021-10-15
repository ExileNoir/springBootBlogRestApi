package com.infernalwhaler.springbootblogrestapi.controller;

import com.infernalwhaler.springbootblogrestapi.model.Role;
import com.infernalwhaler.springbootblogrestapi.model.User;
import com.infernalwhaler.springbootblogrestapi.payload.JwtAuthResponseDto;
import com.infernalwhaler.springbootblogrestapi.payload.LoginDto;
import com.infernalwhaler.springbootblogrestapi.payload.SignUpDto;
import com.infernalwhaler.springbootblogrestapi.repository.IRoleRepository;
import com.infernalwhaler.springbootblogrestapi.repository.IUserRepository;
import com.infernalwhaler.springbootblogrestapi.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

/**
 * Auth Rest Controller
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@RestController
@RequestMapping("/api/auth")
@Api(value = "Auth Controller exposes SignIn and SignUp REST APIs")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IUserRepository userRepository,
                          IRoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Signing in a User
     *
     * @param loginDto of Object LoginDto
     * @return ResponseEntity of object JwtAuthResponseDto
     */
    @PostMapping("/signin")
    @ApiOperation(value = "REST API to Login user to Blog app")
    public ResponseEntity<JwtAuthResponseDto> authenticateUser(@RequestBody final LoginDto loginDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        // get token from JwtTokenProvider
        final String token = tokenProvider.generateToken(authentication);

        return new ResponseEntity<>(new JwtAuthResponseDto(token), HttpStatus.OK);
    }

    /**
     * Singing up a User
     *
     * @param signUpDto of Object SignUpDto
     * @return ResponseEntity wildcard
     */
    @PostMapping("/signup")
    @ApiOperation(value = "REST API to Register user to Blog app")
    public ResponseEntity<?> registerUser(@RequestBody final SignUpDto signUpDto) {
        // add check if username exists in DB
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            logger.info("Username is already taken: '" + signUpDto.getUsername() + "'");
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        // add check for email exists in Db
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            logger.info("Email is already taken: '" + signUpDto.getEmail() + "'");
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }

        final User user = new User(
                signUpDto.getName(), signUpDto.getUsername(), signUpDto.getEmail(), passwordEncoder.encode(signUpDto.getPassword()));

        final Role roles = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Role not found"));
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered Successfully", OK);
    }
}
