package co.com.disney.api;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import co.com.disney.model.dto.request.GenreRqDTO;
import co.com.disney.model.dto.response.GenreRsDTO;
import co.com.disney.usecase.disney.GenreUseCase;


public class GenreControllerTest {
	
	@InjectMocks
	private GenreController genreController;
	
	@Mock
	private GenreUseCase mockGenreUseCase;
	
	private GenreRqDTO genreRqDTO;
	private GenreRsDTO genreRsDTO;

	
	
	@Before
	public void init () {
		MockitoAnnotations.initMocks(this);
		genreRqDTO = GenreRqDTO.builder().title("Aladdin").image("/src/disney/image/aladdin.jpg").build();
		genreRsDTO = GenreRsDTO.builder().idGenre(1L).image("/src/disney/image/aladdin.jpg").title("Aladdin").build();
		when(mockGenreUseCase.saveGenre(genreRqDTO)).thenReturn(genreRsDTO);
	}

	
	@Test
	public void testInsertGenre () {
		
		ResponseEntity<GenreRsDTO> response;
		response = genreController.insertGenre(genreRqDTO);
		assertNotNull(response);

	}
	
		
	@Test
	public void testGetAll () {
		
		ResponseEntity<List<GenreRsDTO>> response;
		response = genreController.getAll();
		assertNotNull(response);

	}
	


}




