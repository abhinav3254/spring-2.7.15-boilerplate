package com.abhinav3254.jwt;

import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    private Claims claims;
    private String userId;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Skip filter for specific paths
        String servletPath = request.getServletPath();
        if (servletPath.equals("/auth/login") || servletPath.equals("/auth/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Remove "Bearer " prefix
            try {
                userId = jwtUtils.extractUsername(token); // Extract userId from the token
                claims = jwtUtils.extractAllClaims(token); // Extract all claims
            } catch (Exception e) {
                // Handle invalid token scenarios gracefully
                filterChain.doFilter(request, response);
                return;
            }
        }

        // Authenticate user if the token is valid and not yet authenticated
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(userId);

            if (jwtUtils.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set authentication in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
