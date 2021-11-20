package co.com.disney.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import co.com.disney.repository.OperationUserJPA;


@Component
public class JwtFilterRequest extends OncePerRequestFilter {

	private JWTUtil jwtUtil;
	
	@Autowired
	private OperationUserJPA operationUserJPA;

	private AuthenticationManager authenticationManager;
	
	
	@Autowired
    public JwtFilterRequest(@Lazy AuthenticationManager authenticationManager) {
        this.jwtUtil = new JWTUtil();
		this.authenticationManager = authenticationManager;
    }



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization"); 

		String jwt = null;
		String userName = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ") ) {

			jwt = authorizationHeader.substring(7); 

			userName = jwtUtil.extractUsername(jwt); 

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null ) {  

				UserDetails userDetails = operationUserJPA.loadUserByUsername(userName); 

				if (jwtUtil.validateToken(jwt, userDetails)) { 

					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(),userDetails.getPassword());
					Authentication auth = authenticationManager.authenticate(authToken);
					SecurityContextHolder.getContext().setAuthentication(auth); 

				}

			}

		}

		filterChain.doFilter(request, response); 

	}



}
