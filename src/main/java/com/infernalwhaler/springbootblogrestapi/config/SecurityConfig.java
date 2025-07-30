package com.infernalwhaler.springbootblogrestapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Sdeseure
 * @project springboot-blog-rest-api
 * @date 29/07/2025
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private  UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
//                        authorize.anyRequest().authenticated()
                                authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }





//    @Bean
//    UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
//        final User.UserBuilder users = User.builder();
//
//        final UserDetails nora = users
//                .username("nora")
//                .password(passwordEncoder.encode("nora"))
//                .roles("USER")
//                .build();
//
//        final UserDetails constance = users
//                .username("constance")
//                .password(passwordEncoder.encode("constance"))
//                .roles("ADMIN")
//                .build();
//
//        final UserDetails steven = users
//                .username("steven")
//                .password(passwordEncoder.encode("steven"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(nora, constance, steven);
//    }
}
