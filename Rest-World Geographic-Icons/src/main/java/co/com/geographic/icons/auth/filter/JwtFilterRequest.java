package co.com.geographic.icons.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.com.geographic.icons.auth.service.JWTUtil;
import co.com.geographic.icons.auth.service.CustomUserDetailService;



@Component
public class JwtFilterRequest extends OncePerRequestFilter {


	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private CustomUserDetailService userDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;


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

				UserDetails userDetails = userDetailService.loadUserByUsername(userName); 

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
