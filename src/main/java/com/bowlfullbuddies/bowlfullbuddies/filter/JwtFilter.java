package com.bowlfullbuddies.bowlfullbuddies.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bowlfullbuddies.bowlfullbuddies.config.JwtUtil;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.repository.UserRepository;

import java.io.IOException;
import java.util.Collections;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        String auth = req.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);
            try {
                if (jwtUtil.validateToken(token)) {
                    Claims claims = jwtUtil.getClaims(token);
                    String username = claims.getSubject();
                    Long userId = claims.get("userId", Long.class);
                    logger.debug("JwtFilter: token valid subject={}, userId={}", username, userId);

                    Users u = null;
                    if (userId != null) {
                        u = userRepository.findById(userId).orElse(null);
                    }

                    if (u != null) {
                        UserDetails userDetails = org.springframework.security.core.userdetails.User
                            .withUsername(username)
                            .password("") // not used here
                            .authorities(Collections.emptyList())
                            .build();

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    } else {
                        logger.warn("JwtFilter: no user found with id {}", userId);
                    }
                } else {
                    logger.warn("JwtFilter: token validation returned false");
                }
            } catch (ExpiredJwtException eje) {
                logger.warn("JwtFilter: token expired - {}", eje.getMessage());
            } catch (JwtException je) {
                logger.warn("JwtFilter: invalid token - {}", je.getMessage());
            } catch (Exception ex) {
                logger.error("JwtFilter: unexpected error validating token", ex);
            }
        }
        chain.doFilter(req, res);
    }
}
