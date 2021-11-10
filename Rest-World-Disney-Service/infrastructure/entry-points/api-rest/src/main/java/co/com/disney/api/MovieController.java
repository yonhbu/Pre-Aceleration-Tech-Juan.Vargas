package co.com.disney.api;


import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.disney.model.dto.request.MovieRqDTO;
import co.com.disney.model.dto.response.MovieRsDTO;
import co.com.disney.model.dto.response.MoviesDTOTitleAndImage;
import co.com.event.usecase.disney.MovieUseCase;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("movie")
@RequiredArgsConstructor
public class MovieController {

	private final MovieUseCase movieUseCase;



	@PostMapping()
	public ResponseEntity<MovieRsDTO> insertMovie (@RequestBody MovieRqDTO movieRqDTO) {
		MovieRsDTO movieResponse = movieUseCase.saveMovie(movieRqDTO);
		return new ResponseEntity<>(movieResponse, HttpStatus.CREATED);

	}


	@GetMapping("/field")
	public ResponseEntity<List<MoviesDTOTitleAndImage>> listMoviesTitleAndImage () {

		List<MoviesDTOTitleAndImage> movieResponse = movieUseCase.getListMovieTitleAndImage();
		return new ResponseEntity<>(movieResponse, HttpStatus.OK);

	}


	@GetMapping("/all")
	public ResponseEntity<List<MovieRsDTO>> getAll () {
		List<MovieRsDTO> movieResponse = movieUseCase.getAllMovies();
		return new ResponseEntity<>(movieResponse, HttpStatus.OK);

	}


	@GetMapping("/{movieId}")
	public ResponseEntity<MovieRsDTO> findCharacterforID (@PathVariable ("movieId") Long movieId) {
		MovieRsDTO movieResponse = movieUseCase.findMovie(movieId);
		return new ResponseEntity<>(movieResponse, HttpStatus.OK);

	}
	
	@GetMapping()
	public ResponseEntity<List<MovieRsDTO>> getMovieDetailsByFilters (
			@RequestParam (required = false) String name,
			@RequestParam (required = false) Set<Long> gender,
			@RequestParam (required = false, defaultValue = "ASC") String order) {

		List<MovieRsDTO> listMovieResponseFilter = movieUseCase.getMoviessByFilters(name, gender, order);
		return new ResponseEntity<>(listMovieResponseFilter, HttpStatus.OK);

	}


	@PutMapping("/{movieId}")
	public ResponseEntity<MovieRsDTO> updateMovie (@PathVariable ("movieId") Long movieIdId, @RequestBody MovieRqDTO movieRqDTO) {
		MovieRsDTO movieResponse = movieUseCase.update(movieIdId, movieRqDTO);
		return new ResponseEntity<>(movieResponse, HttpStatus.OK);

	}


	@DeleteMapping("/{movieId}")
	public ResponseEntity<String> deleteMovie (@PathVariable ("movieId") Long movieId) {
		String response = 	movieUseCase.deleteMovie(movieId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}




