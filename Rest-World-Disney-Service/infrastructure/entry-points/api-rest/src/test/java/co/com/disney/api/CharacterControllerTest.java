package co.com.disney.api;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import co.com.disney.model.dto.request.CharacterRqDTO;


public class CharacterControllerTest {
	
	@InjectMocks
	private CharacterController characterController;
	
	@Mock
	private co.com.disney.usecase.disney.CharacterUseCase characterUseCase;
	
	private CharacterRqDTO characterRqDTO;
	
	private String history = "Jasmine is a beautiful fifteen-year-old princess daughter of the Sultan of Agrabah. Unwilling to marry any of the princes her father suggests, she runs away from home, but because she has never been outside the palace before.";
	
	
	@Before
	public void init () {
		MockitoAnnotations.initMocks(this);
		characterRqDTO = CharacterRqDTO.builder().name("Jasmine").age(18).history(history).image("/src/disney/image/jasmine.jpg").weight(54).build();
		when(characterUseCase.saveCharacter(characterRqDTO)).thenReturn(characterRqDTO);
	}

	
	@Test
	public void testInsert () {
		
		ResponseEntity<CharacterRqDTO> response;
		response = characterController.insert(characterRqDTO);
		assertNotNull(response);

	}

	
}



