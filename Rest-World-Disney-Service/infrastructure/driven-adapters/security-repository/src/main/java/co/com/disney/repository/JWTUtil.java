package co.com.disney.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTUtil {

	private static String key = "disney_word";


	public String extractUsername (String token) {
		return extractClaims(token, Claims::getSubject); 

	}


	public Date extractExpiration (String token) {
		return extractClaims (token, Claims::getExpiration);

	}



	public <T> T extractClaims (String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}




	public Claims getAllClaims (String token) { 
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}




	public boolean isTokenExpirate (String token) {
		return extractExpiration(token).before(new Date()); 
	}



	public String generateToken (UserDetails userDetails ) { 

		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());

	}


	public String createToken (Map<String, Object> claims, String subject ) { 

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())) 
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, key).compact(); 

	}



	public boolean validateToken (String token, UserDetails userDetails) { 
		return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpirate(token); 
	}





}
