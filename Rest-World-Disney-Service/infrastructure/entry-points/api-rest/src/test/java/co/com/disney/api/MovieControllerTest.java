package co.com.disney.api;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
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
import co.com.disney.model.dto.request.MovieRqDTO;
import co.com.disney.model.dto.response.CharacterRsDTO;
import co.com.disney.model.dto.response.MovieRsDTO;
import co.com.disney.model.dto.response.MoviesDTOTitleAndImage;
import co.com.disney.usecase.disney.MovieUseCase;


public class MovieControllerTest {
	
	@InjectMocks
	private MovieController movieController;
	
	@Mock
	private MovieUseCase movieUseCase;
	
	private static Long ID = 1L;
	
	LocalDate time = LocalDate.now();
	
	private Set<Long> gender;
	
	private List<CharacterRqDTO> listCharacter = new ArrayList<>();
	
	private MovieRqDTO movieRqDTO;
	
	private List<MoviesDTOTitleAndImage> listMoviesDTOTitleAndImage = new ArrayList<>();
	
	private List<CharacterRsDTO> listCharacterRsDTO = new ArrayList<>();
	
	private List<MovieRsDTO> listMovieRsDTO = new ArrayList<>();
	
	private MoviesDTOTitleAndImage moviesDTOTitleAndImage;
	
	private MovieRsDTO movieRsDTO;
	
	@Before
	public void init () {
		MockitoAnnotations.initMocks(this);
		movieRqDTO = MovieRqDTO.builder().image("/src/disney/image/aladdin.jpg").title("Aladdin").idGenre(ID).rating(5).character(listCharacter).creationDate(time).build();
		moviesDTOTitleAndImage = MoviesDTOTitleAndImage.builder().image("/src/disney/image/aladdin.jpg").title("Aladdin").creationDate(time).build();
		listMoviesDTOTitleAndImage.add(moviesDTOTitleAndImage);
		movieRsDTO = MovieRsDTO.builder().idMovie(ID).image("/src/disney/image/aladdin.jpg").title("Aladdin").idGenre(ID).rating(5).character(listCharacterRsDTO).creationDate(time).build();
		listMovieRsDTO.add(movieRsDTO);
		when(movieUseCase.saveMovie(movieRqDTO)).thenReturn(movieRsDTO);
		when(movieUseCase.getListMovieTitleAndImage()).thenReturn(listMoviesDTOTitleAndImage);
		when(movieUseCase.getAllMovies()).thenReturn(listMovieRsDTO);
		when(movieUseCase.findMovie(ID)).thenReturn(movieRsDTO);
		when(movieUseCase.getMoviessByFilters("Aladdin", gender, "ASC")).thenReturn(listMovieRsDTO);
		when(movieUseCase.update(ID, movieRqDTO)).thenReturn(movieRsDTO);

	}

	
	@Test
	public void testInsertMovie () {
		
		ResponseEntity<MovieRsDTO> response;
		response = movieController.insertMovie(movieRqDTO);
		assertNotNull(response);
		assertEquals("Aladdin", response.getBody().getTitle());
		System.out.println(response.getBody().getTitle());

	}
	
	
	@Test
	public void testListMoviesTitleAndImage () {
		
		ResponseEntity<List<MoviesDTOTitleAndImage>> response;
		response = movieController.listMoviesTitleAndImage();
		assertNotNull(response);
		assertFalse(response.getBody().isEmpty()); //Return false if contains some object
		System.out.println(response.getBody().isEmpty());

	}
	
	@Test
	public void testGetAll () {
		
		ResponseEntity<List<MovieRsDTO>> response;
		response = movieController.getAll();
		assertNotNull(response);
		assertFalse(response.getBody().isEmpty()); //Return false if contains some object
		System.out.println(response.getBody().isEmpty());

	}
	
	@Test
	public void testFindMovieforID () {
		
		ResponseEntity<MovieRsDTO> response;
		response = movieController.findMovieforID(ID);
		assertNotNull(response);
		assertEquals("Aladdin", response.getBody().getTitle());
		System.out.println( response.getBody().getTitle());

	}
	
	@Test
	public void testGetMovieDetailsByFilters () {
		
		ResponseEntity<List<MovieRsDTO>> response;
		response = movieController.getMovieDetailsByFilters("Aladdin", gender, "ASC");
		assertNotNull(response);
		assertFalse(response.getBody().isEmpty()); //Return false if contains some object

	}
	
	@Test
	public void testUpdate () {
		
		ResponseEntity<MovieRsDTO> response;
		response = movieController.updateMovie(ID,movieRqDTO);
		assertNotNull(response);
		assertEquals("Aladdin", response.getBody().getTitle());
		System.out.println("*************");
		System.out.println(response.getBody().getTitle());

	}
	
	@Test
	public void testDelete () {
		Mockito.when(movieUseCase.deleteMovie(ID)).thenReturn("Delete Movie");
		ResponseEntity<String> response;
		response = movieController.deleteMovie(ID);
		assertNotNull(response);
		System.out.println("*************");
		System.out.println(response.getBody());


	}
	
	
	
	
	
	
	
	

	
}



