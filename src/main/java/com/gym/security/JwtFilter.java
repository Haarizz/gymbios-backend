package com.gym.security;

import com.gym.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        // ⭐ Allow login & register without JWT
        if (path.equals("/api/auth/login") || path.equals("/api/auth/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        String token = null;

        // ⭐ Extract token and remove "Bearer "
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        // ⭐ Token exists → validate
        if (token != null) {
            try {
                if (jwtUtil.validateToken(token)) {

                    Claims claims = jwtUtil.getClaims(token);
                    String email = claims.getSubject();
                    String role = claims.get("role", String.class);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    email,
                                    null,
                                    List.of(() -> role)
                            );

                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // ⭐ Set authentication in SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception ex) {
                // ❗ Token invalid → just proceed and let security block it
                System.out.println("Invalid JWT: " + ex.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
