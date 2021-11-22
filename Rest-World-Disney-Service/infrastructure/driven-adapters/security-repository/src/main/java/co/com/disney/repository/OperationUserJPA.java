package co.com.disney.repository;



import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.com.disney.mapper.ObjectMapperUtils;
import co.com.disney.model.TokenEntity;
import co.com.disney.model.UserEntity;
import co.com.disney.model.gateways.EmailGateway;
import co.com.disney.model.gateways.UserGatewayService;
import co.com.disney.util.JWTUtil;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class OperationUserJPA implements UserGatewayService, UserDetailsService {

	private final UserRepository userRepository;
	
	private final JWTUtil jWTUtil;	
	
	private final EmailGateway emailGateway;
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	

	@Override
	public UserEntity saveUser(UserEntity userEntity) {
		
		UserDataJPA userDataJPA = new UserDataJPA ();
		userDataJPA.setUsername(userEntity.getUsername());	
		userDataJPA.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		
		UserDataJPA userRsDataJPASave = userRepository.save(userDataJPA);

		if (userRsDataJPASave != null) {
			emailGateway.sendWelcomeEmailTo(userRsDataJPASave.getUsername());
		}
		return ObjectMapperUtils.map(userRsDataJPASave, UserEntity.class);
	}
	
	@Override
	public TokenEntity authenticate(UserEntity userEntity) {
		
		UserDetails userDetails;
		  
        try {
        	Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword())); 
			userDetails = (UserDetails) auth.getPrincipal();

        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException("Incorrect username or password", e);
        }

        final String jwt = jWTUtil.generateToken(userDetails); 
        return new TokenEntity (jwt,"Successfully Generated Token");
  
	}
	

	public UserDetails loadUserByUsername (String username) {
		
		UserDataJPA userDataJPA = userRepository.findByUsername(username);
		UserEntity userEntityRsDTO = ObjectMapperUtils.map(userDataJPA, UserEntity.class);

		if (userEntityRsDTO != null) {
			return new User (userEntityRsDTO.getUsername(), userEntityRsDTO.getPassword(), Collections.emptyList());

		} else {
			throw new UsernameNotFoundException ("User " + username + "Not Found");
		}
	}




}


