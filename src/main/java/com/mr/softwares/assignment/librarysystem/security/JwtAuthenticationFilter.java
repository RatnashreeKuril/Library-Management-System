package com.mr.softwares.assignment.librarysystem.security;

import java.io.IOException;

import org.apache.catalina.util.StringUtil;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mr.softwares.assignment.librarysystem.services.JWTService;
import com.mr.softwares.assignment.librarysystem.services.UserService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter
{

    private final JWTService jwtService;

    private final UserService userService;

    public JwtAuthenticationFilter(JWTService jwtService,UserService userService)
    {
        this.jwtService = jwtService;
        this.userService = userService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
            final String authHeader=request.getHeader("Authorization");
            final String jwt;
            final String username;
            if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer "))
            {
                filterChain.doFilter(request,response);
                return;
            }
            jwt=authHeader.substring(7);
            username=jwtService.extractUserName(jwt);

            if(StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UserDetails userDetails=userService.userDetailsService().loadUserByUsername(username);
                if(jwtService.isTokenValid(jwt, userDetails))
                {
                    SecurityContext securityContext=SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(token);    
                    SecurityContextHolder.setContext(securityContext);
                }

                
            }
            filterChain.doFilter(request, response);


    }


}
