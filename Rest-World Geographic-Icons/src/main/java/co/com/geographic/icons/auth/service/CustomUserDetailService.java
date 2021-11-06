package co.com.geographic.icons.auth.service;


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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.geographic.icons.auth.dto.AuthenticationRequestDTO;
import co.com.geographic.icons.auth.dto.TokenResponseDTO;
import co.com.geographic.icons.auth.dto.UserRsDTO;
import co.com.geographic.icons.auth.repository.UserRepository;
import co.com.geographic.icons.auth.service.impl.IUserService;
import co.com.geographic.icons.model.UserEntity;
import co.com.geographic.icons.util.ObjectMapperUtils;


@Service
public class CustomUserDetailService implements IUserService, UserDetailsService {
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtil jWTUtil;	

	@Autowired
	private AuthenticationManager authenticationManager; 


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUsername(username);
		// convert entity to DTO
		UserRsDTO userRsDTO = ObjectMapperUtils.map(userEntity, UserRsDTO.class);

		if (userRsDTO != null) {
			return new User (userRsDTO.getUsername(), userRsDTO.getPassword(), Collections.emptyList());

		} else {
			throw new UsernameNotFoundException ("User " + username + "Not Found");
		}
	}


	@Override
	public UserRsDTO saveUser(AuthenticationRequestDTO authenticationRequestDTO) {
		// convert DTO to entity
		UserEntity userSaveEntity = new UserEntity ();
		userSaveEntity.setUsername(authenticationRequestDTO.getUsername());	
		userSaveEntity.setPassword(authenticationRequestDTO.getPassword());
		
		userSaveEntity.setPassword(passwordEncoder.encode(authenticationRequestDTO.getPassword()));
				
		UserEntity userRsSave = userRepository.save(userSaveEntity);

		// convert entity to DTO
		return ObjectMapperUtils.map(userRsSave, UserRsDTO.class);
	}
	
	
	
	public TokenResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO) {
		UserDetails userDetails;
  
        try {
        	Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword())); 
			userDetails = (UserDetails) auth.getPrincipal();

        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException("Incorrect username or password", e);
        }

        final String jwt = jWTUtil.generateToken(userDetails); 
        return new TokenResponseDTO(jwt,"Successfully Generated Token");
  
	}
}
