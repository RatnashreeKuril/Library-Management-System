package com.mr.softwares.assignment.librarysystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mr.softwares.assignment.librarysystem.dl.pojo.User;
import com.mr.softwares.assignment.librarysystem.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration 
{
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;
    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,UserService userService)
    {
        this.jwtAuthenticationFilter=jwtAuthenticationFilter;
        this.userService=userService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(request->request.requestMatchers("/auth/**")
        .permitAll()
        .requestMatchers("/swagger-ui/index.html").permitAll()
        .requestMatchers("/v3/api-docs").permitAll()
        .requestMatchers("/book/add").hasAnyAuthority("STAFF")
        .requestMatchers("/book/update").hasAnyAuthority("STAFF")
        .requestMatchers("/book/delete").hasAnyAuthority("STAFF")
        .requestMatchers(HttpMethod.GET, "/book/*").hasAnyAuthority("STAFF","USER")
        .requestMatchers("/author/add").hasAnyAuthority("STAFF")
        .requestMatchers("/author/update").hasAnyAuthority("STAFF")
        .requestMatchers("/author/delete").hasAnyAuthority("STAFF")
        .requestMatchers(HttpMethod.GET, "/author/*").hasAnyAuthority("STAFF","USER")
        .requestMatchers("/borrowingRecord/add").hasAnyAuthority("STAFF")
        .requestMatchers(HttpMethod.GET, "/borrowingRecord/*").hasAnyAuthority("STAFF","USER")
        .requestMatchers("/borrowingRecord/borrowBook").hasAnyAuthority("STAFF","USER")
        .requestMatchers("/borrowingRecord/returnBook").hasAnyAuthority("STAFF","USER")
        .requestMatchers("/libraryBranch/**").hasAnyAuthority("STAFF")
        .requestMatchers(HttpMethod.GET, "/fine/*").hasAnyAuthority("STAFF","USER")
        .requestMatchers( "/fine/pay").hasAnyAuthority("STAFF","USER")
        .requestMatchers( "/fine/delete").hasAnyAuthority("STAFF")
        .requestMatchers( "/analytics/**").permitAll()
        .anyRequest().authenticated()).sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }
}
