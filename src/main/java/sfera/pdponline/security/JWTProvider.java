package sfera.pdponline.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sfera.pdponline.exceptions.JWTException;

import java.util.Date;

@Component

public class JWTProvider {

    @Value("${jwt.secretkey}")
    private String secretKey;
    @Value("${jwt.token.ttl}")
    private Long ttl;

    public String generateToken(String email) {
    long now = System.currentTimeMillis();
    return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date(now))
            .setExpiration(new Date(now + ttl))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();

    }

    public String getEmailFromToken(String token) {
        try{
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (ExpiredJwtException e){
            throw new JWTException("JWT token has expired: "+e.getMessage());
        }catch (SignatureException e){
            throw new JWTException("Invalid JWT signature: "+e.getMessage());
        }catch (Exception e){
            throw new JWTException(" JWT token exception: "+e.getMessage());
        }

    }

    public boolean valid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new JWTException("JWT token has expired: " + e.getMessage());
        } catch (SignatureException e) {
            throw new JWTException("Invalid JWT signature: " + e.getMessage());
        } catch (Exception e) {
            throw new JWTException(" JWT token exception: " + e.getMessage());
        }
    }










}
