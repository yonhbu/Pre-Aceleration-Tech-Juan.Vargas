package co.com.geographic.icons.auth.service;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	private static String key = "geographicon";
	
	
	public String generateToken (UserDetails userDetails ) { 
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date()) 
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, key).compact(); 
				
	}
	
	

	public Claims getAllClaims (String token) { 
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
	
	
	public String extractUsername (String token) {
		return getAllClaims(token).getSubject(); 
		
	}
	
	
	public Date extractExpiration (String token) {
		return extractClaims (token, Claims::getExpiration);
		
	}
	
	public <T> T extractClaims (String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	
	
	public boolean isTokenExpirate (String token) {
		return getAllClaims(token).getExpiration().before(new Date()); 
	}
	
	
	
	public boolean validateToken (String token, UserDetails userDetails) { 
		return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpirate(token); 
	}
	

	
	

}
