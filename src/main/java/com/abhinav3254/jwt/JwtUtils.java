package com.abhinav3254.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    private final String SECRET = "1a4c00389985ec192146d8b3985fb6c4863defbf6a4c7ca95d75e1df494dbbc629c2dda94f8b091ddcc9853c9048574ed0986ac901572fcf01492ed500fdc5a4350db565ef487a13bc52d9dbbb3689982b8cd73c08da6eb3f065edda32b551d0a13bb49df9ce505f17e41de6a1d4d5456057658fc1530a76a93681dd99680e2079e1b2a71ed19f2c4979a6a6998852b28a1788bdf22e6d7840953466d0ee32019e480eb3cc3497fff2a1d68bc4931ce079bf6b71a33a3e65e238793684d9ca34a44da6deb35752f8ad320cec2fc88f146f59a2d3bb3041b57184aca68b77c390d491cc64d711a4867a5129661bbda3ee5832682b82050beaa04ee7d57a470005";

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }


    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }



    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String id = extractUsername(token);
        return (id.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }



    public String generateToken(String id, String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("email",email);
        return createToken(claims, id);
    }


    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }


    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

}
