package com.manageblogpost.blog_post_manage.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manageblogpost.blog_post_manage.controller.utils.ApiResponse;
import com.manageblogpost.blog_post_manage.service.security.JwtAuthentication;
import com.manageblogpost.blog_post_manage.service.security.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtAuthentication jwtAuthentication;
    private final UserService userService;

    public JwtAuthFilter(JwtAuthentication jwtAuthentication, UserService userService) {
        this.jwtAuthentication = jwtAuthentication;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);
            String userEmail = jwtAuthentication.getUniqueIdentifier(jwt);

            if (userEmail != null && jwtAuthentication.IsTokenExpired(jwt)) {
                UserDetails userDetails = userService.loadUserByUsername(userEmail);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
        }
        catch (ExpiredJwtException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ServletOutputStream out = response.getOutputStream();
            new ObjectMapper().writeValue(out, new ApiResponse<>(null,"Invalid user session, try logging in again" ,"INVALID_TOKEN", HttpStatus.UNAUTHORIZED));
            out.flush();
        }
    }
}
