package com.nicky;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private static Logger logger = Logger.getLogger(JwtService.class.getName());



    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Verificar el header
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            logger.warning("Header null or doesn't start with Bearer");
            return;
        }

        // 2. Verificar el token
        String token = header.substring(7);
        DecodedJWT tokenVerifier = jwtService.verifyToken(token);

        if(jwtService.isExpired(tokenVerifier)){
            filterChain.doFilter(request, response);
            logger.warning("token expired");
            return;
        }

        // 3. Verificar el username y el SecurityContextHolder
        String subject = jwtService.extractSubject(tokenVerifier);

        if(subject == null || subject.isBlank() || SecurityContextHolder.getContext().getAuthentication() != null){
            filterChain.doFilter(request, response);
            logger.warning("subject blank");
            return;
        }

        // 3. Definir el SecurityContextHolder
        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request,response);
    }
}
