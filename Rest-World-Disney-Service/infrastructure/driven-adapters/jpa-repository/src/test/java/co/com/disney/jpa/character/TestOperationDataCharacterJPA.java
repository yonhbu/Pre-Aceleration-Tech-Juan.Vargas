package co.com.disney.jpa.character;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import co.com.disney.model.CharacterEntity;


public class TestOperationDataCharacterJPA {
	
	@InjectMocks
	private OperationDataCharacterJPA operationDataCharacterJPA;
	
	@Mock
    private CharacterRepositoryJPA characterRepositoryJPA;
	
	@Mock
	private CharacterSpecification iconSpecification;
	
	private String history = "Jasmine is a beautiful fifteen-year-old princess daughter of the Sultan of Agrabah. Unwilling to marry any of the princes her father suggests, she runs away from home, but because she has never been outside the palace before.";
		
	private CharacterDataJPA characterRequest;
	
	private CharacterEntity characterEntity;
	
	List<CharacterDataJPA> listCharacterDataJPA = new ArrayList<>();
	
	List<CharacterEntity> listCharacterEntity = new ArrayList<>();
	
	
	
	
	@Before
	public void init () {
		MockitoAnnotations.initMocks(this);
		characterRequest = CharacterDataJPA.builder().name("Jasmine").age(18).history(history).image("/src/disney/image/jasmine.jpg").weight(54).build();
		characterEntity =  CharacterEntity.builder().name("Jasmine").age(18).history(history).image("/src/disney/image/jasmine.jpg").weight(54).build();
		listCharacterDataJPA.add(characterRequest);
		Mockito.when(characterRepositoryJPA.save(characterRequest)).thenReturn(characterRequest);
		Mockito.when(characterRepositoryJPA.findAll()).thenReturn(listCharacterDataJPA);
	}
	
	
	@Test
    public void save () {
		
		CharacterEntity response;
		response = operationDataCharacterJPA.save(characterEntity);
		assertNotNull(response);
		assertEquals("Jasmine", response.getName());
		System.out.println(response.getName());
		
		
	}
	
	@Test
	public void findAllCharacters () {
		listCharacterEntity = operationDataCharacterJPA.findAllCharacters();
		assertNotNull(listCharacterEntity);
		assertFalse(listCharacterEntity.isEmpty());
		assertEquals(1,listCharacterEntity.size());
		
	}

}
