package co.com.geographic.icons.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.geographic.icons.auth.dto.AuthenticationRequestDTO;
import co.com.geographic.icons.auth.dto.TokenResponseDTO;
import co.com.geographic.icons.auth.dto.UserRsDTO;
import co.com.geographic.icons.auth.service.impl.IUserService;


@RestController
@RequestMapping("/auth")
public class AuthController {


	@Autowired
	private IUserService iUserService;


	@PostMapping("/signup")
	public ResponseEntity<UserRsDTO> signUp (@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws Exception {
		UserRsDTO userRsDTO = iUserService.saveUser(authenticationRequestDTO);
		return new ResponseEntity<>(userRsDTO, HttpStatus.CREATED);
	}

	
	@PostMapping("/authenticate")  
	public ResponseEntity<TokenResponseDTO> createToken (@RequestBody AuthenticationRequestDTO authenticationRequestDTO ) {
		TokenResponseDTO tokenResponseDTO = iUserService.authenticate(authenticationRequestDTO);
		return new ResponseEntity<>(tokenResponseDTO, HttpStatus.OK);

	}


}