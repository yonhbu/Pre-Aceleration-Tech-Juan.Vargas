package co.com.disney.repository;



import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import co.com.disney.model.TokenEntity;
import co.com.disney.model.UserEntity;
import co.com.disney.model.gateways.UserGatewayService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OperationUserJPA implements UserGatewayService, UserDetailsService {

	private final UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private JWTUtil jWTUtil;	
	

	@Override
	public UserEntity saveUser(UserEntity userDTO) {
		
		UserDataJPA userDataJPA = new UserDataJPA ();
		userDataJPA.setUsername(userDTO.getUsername());	
		userDataJPA.setPassword(userDTO.getPassword());
		
		UserDataJPA userRsDataJPASave = userRepository.save(userDataJPA);
	
//		if (userRsSave != null) {
//			emailServiceImpl.sendWelcomeEmailTo(userRsSave.getUsername());
//		}
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

