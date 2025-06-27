package com.juanba.sunnybank.infrastructure.security.config.filter;

import com.juanba.sunnybank.infrastructure.persistance.user.repository.SpringDataUserRepository;
import com.juanba.sunnybank.infrastructure.util.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final SpringDataUserRepository springDataUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        var jwt = authorizationHeader.split(" ")[1];
        var subject = jwtService.getSubject(jwt);
        var user = springDataUserRepository.findByEmail(subject);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
