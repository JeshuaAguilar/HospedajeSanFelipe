package com.hospedajesanfelipe.config;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String MY_SECRET_WORD = "dwHFwTQc4UAAOdeuunjhvH/+7eXmHnwioncruUYV8DO7nPknoVCHMdhNdN9lPZ1pVlBOKr7xrfrIjfXpvQ2NTQ==";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(MY_SECRET_WORD));

    private long expirationMs = 10800000;

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }
    
    public boolean validateToken(String jwt) {
	    try {
	    	
	    	@SuppressWarnings({ "unused", "deprecation" })
			Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt);
	        return true;
	    } catch (Exception e) {
	    	return false;
	    }
   }

   @SuppressWarnings("deprecation")
public String extractUsername(String jwt) {
	   return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody().getSubject();
	   
   }
   
   public Collection<GrantedAuthority> getRoles(String jwt) {
	   Collection<GrantedAuthority> authorities = null;
	   try {
		@SuppressWarnings("deprecation")
		Object roles = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody().get("authorities");
		authorities = Arrays.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class).
				readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
		} catch (Exception e) {
			// TODO: handle exception
		}
	   
	   return authorities;
   }
}
