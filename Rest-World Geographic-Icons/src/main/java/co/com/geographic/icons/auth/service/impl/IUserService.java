package co.com.geographic.icons.auth.service.impl;


import org.springframework.security.core.userdetails.UserDetails;

import co.com.geographic.icons.auth.dto.AuthenticationRequestDTO;
import co.com.geographic.icons.auth.dto.TokenResponseDTO;
import co.com.geographic.icons.auth.dto.UserRsDTO;

public interface IUserService {
	
	UserRsDTO saveUser(AuthenticationRequestDTO userDTO);

	UserDetails loadUserByUsername(String username);

	TokenResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO);

}
