package com.jobportal.jwt;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String requestHeader = request.getHeader("Authorization");
                String username = null;
                String token = null;
                if(requestHeader != null && requestHeader.startsWith("Bearer ")){
                    token = requestHeader.substring(7);
                   try {
                    username = jwtHelper.getUsernameFromToken(token);
                   } catch (IllegalArgumentException e) {
                     e.printStackTrace();
                   }catch(ExpiredJwtException e){
                    e.printStackTrace();
                   }catch(MalformedJwtException e){
                    e.printStackTrace();
                   }catch(Exception e){
                    e.printStackTrace();
                   }
                }
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    Boolean validateToken = jwtHelper.validateToken(token, userDetails.getUsername());
                    if(validateToken){
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }   
                filterChain.doFilter(request, response);
    }
}