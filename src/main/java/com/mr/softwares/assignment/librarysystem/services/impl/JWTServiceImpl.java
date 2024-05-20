package com.mr.softwares.assignment.librarysystem.services.impl;
import org.hibernate.validator.internal.engine.valueextraction.ValueExtractorDescriptor.Key;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.*;

import com.mr.softwares.assignment.librarysystem.dl.pojo.User;
import com.mr.softwares.assignment.librarysystem.services.JWTService;

import java.util.*;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import java.util.function.Function;
@Service
public class JWTServiceImpl implements JWTService {

    public String generateToken(UserDetails userDetails)
    {
        return Jwts.builder().setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
        .signWith(SignatureAlgorithm.HS256,getSigninKey())
        .compact();
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private SecretKey getSigninKey()
    {
        byte[] key=Decoders.BASE64.decode("ab66af25d8c64a31bb3e422d02ef3be5736f57d7727d5df045bb82dc5144d87a");
        return Keys.hmacShaKeyFor(key);
    }

    public String extractUserName(String token)
    {
        return extractClaim(token,Claims::getSubject);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token,UserDetails userDetails)
    {
        final String username=extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token)
    {
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }
    @Override
    public String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*60))
        .signWith(SignatureAlgorithm.HS256,getSigninKey())
        .compact();
    }
    
}


