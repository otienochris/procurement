package com.otienochris.procurement_management_system.configs.security.filters;


import com.otienochris.procurement_management_system.services.UserService;
import com.otienochris.procurement_management_system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// intercept request only once
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtils;

    @Qualifier("userService")
    @Autowired
    private UserDetailsService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        System.out.println(authorizationHeader);

        String username = null, jwtToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwtToken = authorizationHeader.substring(7);
            username = jwtUtils.extractUsername(jwtToken);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userService.loadUserByUsername(username);

            if (jwtUtils.validateToken(jwtToken, userDetails)) {
//                the following are often done by default. However, I'm doing this under one condition: if toke is valid
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response); // continue with the other filters
    }
}
