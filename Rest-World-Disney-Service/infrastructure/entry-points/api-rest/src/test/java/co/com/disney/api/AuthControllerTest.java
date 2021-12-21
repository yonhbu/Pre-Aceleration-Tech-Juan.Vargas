package co.com.disney.api;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;


import co.com.disney.model.dto.request.AuthenticationRequestDTO;
import co.com.disney.model.dto.response.TokenResponseDTO;
import co.com.disney.model.dto.response.UserRsDTO;
import co.com.disney.usecase.security.SecurityUserDetailUseCase;



public class AuthControllerTest {

	@InjectMocks
	private AuthController authController;
	
	@Mock
	private SecurityUserDetailUseCase userDetailUseCase;
	
	private AuthenticationRequestDTO authenticationRequestDTO;
	
	private UserRsDTO userRsDTO;
	
	private TokenResponseDTO tokenResponseDTO;
	
	private static Long ID = 1L;
	
	
	@Before
	public void init () {
		MockitoAnnotations.initMocks(this);
		authenticationRequestDTO = AuthenticationRequestDTO.builder().username("user").password("123456").build();
		userRsDTO = UserRsDTO.builder().idUser(ID).username("user").password("1234").build();
		tokenResponseDTO = TokenResponseDTO.builder().jwt("sdf5ds4f5ds4f").message("Token Generate").build();
		when(userDetailUseCase.saveUser(authenticationRequestDTO)).thenReturn(userRsDTO);
		when(userDetailUseCase.userAuthenticate(authenticationRequestDTO)).thenReturn(tokenResponseDTO);


	}
	

    @Test
	public void signUp () {
    	
    	ResponseEntity<UserRsDTO> response;
		response = authController.signUp(authenticationRequestDTO);
		assertNotNull(response);
		assertEquals("user", response.getBody().getUsername());
		System.out.println(response.getBody().getUsername());

	}

	
    @Test
	public void createToken () {
    	
    	ResponseEntity<TokenResponseDTO> response;
		response = authController.createToken(authenticationRequestDTO);
		assertNotNull(response);
		assertEquals("sdf5ds4f5ds4f", response.getBody().getJwt());
		System.out.println(response.getBody().getJwt());
    	
	}


}