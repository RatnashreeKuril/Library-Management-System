package com.mr.softwares.assignment.librarysystem.services;


import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.mr.softwares.assignment.librarysystem.dl.pojo.User;

public interface JWTService {
String extractUserName(String token);

String generateToken(UserDetails userDetails);
boolean isTokenValid(String token,UserDetails userDetails);

String generateRefreshToken(Map<String,Object> hashMap, UserDetails userDetails);
}
