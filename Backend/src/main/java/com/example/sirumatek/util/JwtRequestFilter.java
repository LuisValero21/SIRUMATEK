package com.example.sirumatek.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;

    public void doFilterInternal()
            throws ServletException, IOException {
        doFilterInternal((HttpServletRequest) null, (HttpServletResponse) null, (FilterChain) null);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        this.chain = chain;
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Aquí puedes cargar el usuario y setear la autenticación en el contexto
        }

        chain.doFilter(request, response);
    }

}