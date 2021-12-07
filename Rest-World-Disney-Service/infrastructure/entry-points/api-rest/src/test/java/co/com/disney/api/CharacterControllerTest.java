package co.com.disney.api;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import co.com.disney.model.dto.request.CharacterRqDTO;
import co.com.disney.model.dto.response.CharacterDTONameAndImage;
import co.com.disney.model.dto.response.CharacterRsDTO;
import co.com.disney.model.dto.response.MovieRsDetailsDTO;


public class CharacterControllerTest {
	
	@InjectMocks
	private CharacterController characterController;
	
	@Mock
	private co.com.disney.usecase.disney.CharacterUseCase characterUseCase;
	
	private static Long ID = 1L;
	
	private Set<Long> movies;
	
	private List<MovieRsDetailsDTO> listMovie = new ArrayList<>();
	
	private CharacterRqDTO characterRqDTO;
	
	private String history = "Jasmine is a beautiful fifteen-year-old princess daughter of the Sultan of Agrabah. Unwilling to marry any of the princes her father suggests, she runs away from home, but because she has never been outside the palace before.";
	
	private List<CharacterDTONameAndImage> listCharacterDTONameAndImage = new ArrayList<>();
	
	private List<CharacterRsDTO> listCharacterRsDTO = new ArrayList<>();
	
	private CharacterDTONameAndImage characterDTONameAndImage;
	
	private CharacterRsDTO characterRsDTO;
	
	@Before
	public void init () {
		MockitoAnnotations.initMocks(this);
		characterRqDTO = CharacterRqDTO.builder().name("Jasmine").age(18).history(history).image("/src/disney/image/jasmine.jpg").weight(54).build();
		characterDTONameAndImage = CharacterDTONameAndImage.builder().name("Jasmine").image("/src/disney/image/jasmine.jpg").build();
		listCharacterDTONameAndImage.add(characterDTONameAndImage);
		characterRsDTO = CharacterRsDTO.builder().age(18).idCharacter(ID).image("/src/disney/image/jasmine.jpg").name("Noel").weight(54).listMovie(listMovie).build();	
		listCharacterRsDTO.add(characterRsDTO);
		when(characterUseCase.saveCharacter(characterRqDTO)).thenReturn(characterRqDTO);
		when(characterUseCase.getListCharacterNameAndImage()).thenReturn(listCharacterDTONameAndImage);
		when(characterUseCase.getAllCharacter()).thenReturn(listCharacterRsDTO);
		when(characterUseCase.findCharacter(ID)).thenReturn(characterRsDTO);
		when(characterUseCase.getCharactersByFilters("Noel", 18, movies, "ASC")).thenReturn(listCharacterRsDTO);
		when(characterUseCase.updateCharacter(ID, characterRqDTO)).thenReturn(characterRqDTO);

	}

	
	@Test
	public void testInsert () {
		
		ResponseEntity<CharacterRqDTO> response;
		response = characterController.insert(characterRqDTO);
		assertNotNull(response);
		assertEquals("Jasmine", response.getBody().getName());
		System.out.println(response.getBody().getName());

	}
	
	
	@Test
	public void testListCharactersNameAndImage () {
		
		ResponseEntity<List<CharacterDTONameAndImage>> response;
		response = characterController.listCharactersNameAndImage();
		assertNotNull(response);
		assertFalse(response.getBody().isEmpty()); //Return false if contains some object
		System.out.println(response.getBody().isEmpty());

	}
	
	@Test
	public void testGetAll () {
		
		ResponseEntity<List<CharacterRsDTO>> response;
		response = characterController.getAll();
		assertNotNull(response);
		assertFalse(response.getBody().isEmpty()); //Return false if contains some object
		System.out.println(response.getBody().isEmpty());

	}
	
	@Test
	public void testFindCharacterforID () {
		
		ResponseEntity<CharacterRsDTO> response;
		response = characterController.findCharacterforID(ID);
		assertNotNull(response);
		assertEquals("Noel", response.getBody().getName());
		System.out.println( response.getBody().getName());

	}
	
	@Test
	public void testGetCharacterDetailsByFilters () {
		
		ResponseEntity<List<CharacterRsDTO>> response;
		response = characterController.getCharacterDetailsByFilters("Noel", 18, movies, "ASC");
		assertNotNull(response);
		assertFalse(response.getBody().isEmpty()); //Return false if contains some object

	}
	
	@Test
	public void testUpdate () {
		
		ResponseEntity<CharacterRqDTO> response;
		response = characterController.update(ID,characterRqDTO);
		assertNotNull(response);
		assertEquals("Jasmine", response.getBody().getName());
		System.out.println("*************");
		System.out.println(response.getBody().getName());

	}
	
	@Test
	public void testDelete () {
		Mockito.when(characterUseCase.deleteCharacter(ID)).thenReturn("Delete User");
		ResponseEntity<String> response;
		response = characterController.delete(ID);
		assertNotNull(response);
		System.out.println("*************");
		System.out.println(response.getBody());


	}
	
	
	
	
	
	
	
	

	
}



