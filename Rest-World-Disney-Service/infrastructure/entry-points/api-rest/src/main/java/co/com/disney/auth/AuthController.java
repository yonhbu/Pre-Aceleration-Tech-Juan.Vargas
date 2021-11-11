package co.com.disney.auth;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.disney.model.dto.request.AuthenticationRequestDTO;
import co.com.disney.model.dto.response.TokenResponseDTO;
import co.com.disney.model.dto.response.UserRsDTO;
import co.com.disney.usecase.security.UserDetailUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final UserDetailUseCase userDetailUseCase;


	@PostMapping("/signup")
	public ResponseEntity<UserRsDTO> signUp (@RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws Exception {
		UserRsDTO userRsDTO = userDetailUseCase.saveUser(authenticationRequestDTO);
		return new ResponseEntity<>(userRsDTO, HttpStatus.CREATED);
	}

	
	@PostMapping("/authenticate")  
	public ResponseEntity<TokenResponseDTO> createToken (@RequestBody AuthenticationRequestDTO authenticationRequestDTO ) {
		TokenResponseDTO tokenResponseDTO = userDetailUseCase.userAuthenticate(authenticationRequestDTO);
		return new ResponseEntity<>(tokenResponseDTO, HttpStatus.OK);

	}


}