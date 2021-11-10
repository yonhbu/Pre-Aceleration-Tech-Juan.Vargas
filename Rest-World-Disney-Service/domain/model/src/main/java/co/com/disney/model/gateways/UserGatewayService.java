package co.com.disney.model.gateways;

import org.springframework.security.core.userdetails.UserDetails;

import co.com.disney.model.TokenEntity;
import co.com.disney.model.UserEntity;

public interface UserGatewayService {
	
	UserEntity saveUser(UserEntity userEntity);
	TokenEntity authenticate(UserEntity userEntity);
	UserDetails loadUserByUsername(String username);

}
