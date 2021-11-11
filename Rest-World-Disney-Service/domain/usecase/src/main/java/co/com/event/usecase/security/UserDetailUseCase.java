package co.com.event.usecase.security;


import org.springframework.security.core.userdetails.UserDetails;

import co.com.disney.model.TokenEntity;
import co.com.disney.model.UserEntity;
import co.com.disney.model.dto.request.AuthenticationRequestDTO;
import co.com.disney.model.dto.response.TokenResponseDTO;
import co.com.disney.model.dto.response.UserRsDTO;
import co.com.disney.model.gateways.UserGatewayService;
import co.com.event.usecase.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDetailUseCase {
	

	private final UserGatewayService userGatewayService;
	
	
	//@Autowired
	//EmailServiceImpl emailServiceImpl;

	public UserRsDTO saveUser(AuthenticationRequestDTO authenticationRequestDTO) {
		UserEntity userSaveEntity = new UserEntity ();
		userSaveEntity.setUsername(authenticationRequestDTO.getUsername());	
		userSaveEntity.setPassword(authenticationRequestDTO.getPassword());
		
		UserEntity userRsSave = userGatewayService.saveUser(userSaveEntity);
	
//		if (userRsSave != null) {
//			emailServiceImpl.sendWelcomeEmailTo(userRsSave.getUsername());
//		}
		return ObjectMapperUtils.map(userRsSave, UserRsDTO.class);
	}
	
	
	
	public TokenResponseDTO userAuthenticate (AuthenticationRequestDTO authenticationRequestDTO) {
		
		UserEntity userEntity = ObjectMapperUtils.map(authenticationRequestDTO, UserEntity.class);
		TokenEntity tokenEntity = userGatewayService.authenticate(userEntity);

		return ObjectMapperUtils.map(tokenEntity, TokenResponseDTO.class);
	}


	public UserDetails loadUserByUsername(String userName) {
		return userGatewayService.loadUserByUsername(userName);
	}
}
