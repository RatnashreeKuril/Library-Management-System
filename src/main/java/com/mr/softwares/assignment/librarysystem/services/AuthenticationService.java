package com.mr.softwares.assignment.librarysystem.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mr.softwares.assignment.librarysystem.beans.UserBean;
import com.mr.softwares.assignment.librarysystem.dl.UserRepository;
import com.mr.softwares.assignment.librarysystem.dl.pojo.JwtAuthenticationResponse;
import com.mr.softwares.assignment.librarysystem.dl.pojo.LoginRequest;
import com.mr.softwares.assignment.librarysystem.dl.pojo.User;
import com.mr.softwares.assignment.librarysystem.exception.ServiceException;

import io.jsonwebtoken.io.SerialException;
import jakarta.validation.Valid;

@Controller
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @ResponseBody
    @PostMapping("/auth/login")
    public JwtAuthenticationResponse login(@Valid @RequestParam("username") String username) throws ServiceException
     {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,username));
        User user=userRepository.findByUsername(username);
        ServiceException serviceException=new ServiceException();
        if(user==null) 
        {
            serviceException.setGenericException("Invalid Username : "+username);
            throw serviceException;
        }
        String jwt=jwtService.generateToken(user);
        String refreshToken=jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }
    @PostMapping("/auth/register")
    @ResponseBody
    public UserBean register(@Valid @RequestBody UserBean userBean) throws ServiceException
    {
        return userService.add(userBean);
    }


}
