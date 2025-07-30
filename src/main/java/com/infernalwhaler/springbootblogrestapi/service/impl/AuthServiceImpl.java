package com.infernalwhaler.springbootblogrestapi.service.impl;

import com.infernalwhaler.springbootblogrestapi.exceptions.BlogApiException;
import com.infernalwhaler.springbootblogrestapi.model.Role;
import com.infernalwhaler.springbootblogrestapi.model.User;
import com.infernalwhaler.springbootblogrestapi.payload.LoginDto;
import com.infernalwhaler.springbootblogrestapi.payload.SignUpDto;
import com.infernalwhaler.springbootblogrestapi.repository.IRoleRepository;
import com.infernalwhaler.springbootblogrestapi.repository.IUserRepository;
import com.infernalwhaler.springbootblogrestapi.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sdeseure
 * @project springboot-blog-rest-api
 * @date 30/07/2025
 */

@Service
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, IUserRepository userRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Login User
     *
     * @param loginDto LoginDto Pojo
     * @return normal String message of successful Login
     */
    @Override
    public String login(final LoginDto loginDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User Logged-in Successfully";
    }

    /**
     * SignUp User
     *
     * @param signUpDto SignUpDto Pojo
     * @return normal String message of successful signUp
     */
    @Override
    public String signUp(final SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())
                || userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username or Email already exists");
        }

        final Set<Role> roles = new HashSet<>();
        final Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new BlogApiException(HttpStatus.BAD_REQUEST, "Role Not Found"));
        roles.add(userRole);

        final User user = new User(
                signUpDto.getName(), signUpDto.getUsername(),
                signUpDto.getEmail(), passwordEncoder.encode(signUpDto.getPassword()));
        user.setRoles(roles);

        userRepository.save(user);

        return "User Created Successfully";
    }
}
