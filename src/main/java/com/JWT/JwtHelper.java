package com.JWT;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {
	private static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);
	private String secret = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";

	public String generateToken(Authentication authentication) {
		String name = authentication.getName();
		String token = Jwts.builder()
		.setSubject(name)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+ 1000*60*30))
		.signWith(keys())
		.compact();
		return token;
	}
	
	private Key keys() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}
	
	public String getUsername(String token) {
		Claims claims = Jwts.parserBuilder()
		.setSigningKey(keys())
		.build()
		.parseClaimsJws(token)
		.getBody();
		String username = claims.getSubject();
		return username;
	}
	
	public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(keys())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
