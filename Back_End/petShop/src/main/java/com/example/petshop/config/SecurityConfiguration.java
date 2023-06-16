package com.example.petshop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import static com.example.petshop.base.Permission.*;
import static com.example.petshop.base.Role.*;
import static com.example.petshop.base.Role.CLIENTE;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/petshop/auth/**"
                        /*"/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"*/
                )
                .permitAll()


                .requestMatchers("/api/petshop/funcionario/**").hasAnyRole(ADMINISTRADOR.name(), FUNCIONARIO.name())
                .requestMatchers(GET, "/api/petshop/funcionario/**").hasAnyAuthority(ADMIN_READ.name(), FUN_READ.name())
                .requestMatchers(POST, "/api/petshop/funcionario/**").hasAnyAuthority(ADMIN_CREATE.name(), FUN_CREATE.name())
                .requestMatchers(PUT, "/api/petshop/funcionario/**").hasAnyAuthority(ADMIN_UPDATE.name(), FUN_UPDATE.name())
                .requestMatchers(DELETE, "/api/petshop/funcionario/**").hasAnyAuthority(ADMIN_DELETE.name(), FUN_DELETE.name())

                .requestMatchers("/api/petshop/usuario/**").hasAnyRole(ADMINISTRADOR.name(), FUNCIONARIO.name(), CLIENTE.name())
                .requestMatchers(GET, "/api/petshop/usuario/**").hasAnyAuthority(ADMIN_READ.name(), FUN_READ.name(), CLI_READ.name())
                .requestMatchers(POST, "/api/petshop/usuario/**").hasAnyAuthority(ADMIN_CREATE.name(), FUN_CREATE.name(), CLI_CREATE.name())
                .requestMatchers(PUT, "/api/petshop/usuario/**").hasAnyAuthority(ADMIN_UPDATE.name(), FUN_UPDATE.name(), CLI_UPDATE.name())
                .requestMatchers(DELETE, "/api/petshop/usuario/**").hasAnyAuthority(ADMIN_DELETE.name(), FUN_DELETE.name(), CLI_DELETE.name())

                .anyRequest()
                    .authenticated()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/petshop/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
                return http.build();
    }
}
