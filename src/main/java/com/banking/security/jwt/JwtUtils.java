package com.banking.security.jwt;


import com.banking.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${secure.app.jwtSecret}")
    private String jwtSecret;

    @Value("${secure.app.jwtExpirationMs}")
    private String jwtExpirationMs;

    @Value("${secure.app.jwtCookieName}")
    private String jwtCookie;

    // Get JWT from Cookies by Cookie name
    public String getJwtFromCookies(HttpServletRequest httpServletRequest){
        Cookie cookie = WebUtils.getCookie(httpServletRequest, jwtCookie);
        if(cookie != null){
            return cookie.getValue();
        }
        return null;
    }

    // Generate a Cookie containing JWT from username, data, expiration, secret
    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrinciple){
        String jwt = generateTokenFromUsername((userPrinciple.getUsername()));
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24*60*60).httpOnly(true).build();
        return cookie;
    }

    // Return Cookie with null value (used to clean Cookie)
    public ResponseCookie getCleanJwtCookie(){
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("api").build();
        // ResponseCookie.from:
        // Params:
        //      name – the cookie name
        //      value – the cookie value
        // Returns:
        //      a builder to create the cookie with
        return cookie;
    }

    // Get username from JWT
    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    // Validate a JWT with a secret
    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid JWT signature: {}", e.getMessage());
        }catch (MalformedJwtException e){
            logger.error("Invalid JWT token: {}", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("JWT token expired: {}", e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("JWT token is unsupported: {}", e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String generateTokenFromUsername(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.ES512, jwtSecret)
                .compact();
    }
}
