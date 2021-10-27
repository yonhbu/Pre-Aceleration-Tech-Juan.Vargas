package co.com.disney.api;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.disney.model.MovieEntity;
import co.com.disney.model.dto.request.MovieRqDTO;
import co.com.disney.model.dto.response.MovieRsDTO;
import co.com.disney.model.dto.response.MoviesDTOTitleAndImage;
import co.com.disney.util.ObjectMapperUtils;
import co.com.event.usecase.disney.MovieUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MovieController {

	private final MovieUseCase movieUseCase;



	@PostMapping("/movie")
	public ResponseEntity<MovieRsDTO> insertMovie (@RequestBody MovieRqDTO movieRqDTO) {

		// convert DTO to entity
		MovieEntity movieEntity = ObjectMapperUtils.map(movieRqDTO, MovieEntity.class);
		MovieEntity movie = movieUseCase.saveMovie(movieEntity);

		// convert entity to DTO
		MovieRsDTO movieResponse = ObjectMapperUtils.map(movie, MovieRsDTO.class);
		log.info("Request received for movie insert", movieResponse.toString());
		return new ResponseEntity<>(movieResponse, HttpStatus.CREATED);

	}


	@GetMapping("/movie")
	public ResponseEntity<List<MoviesDTOTitleAndImage>> listMoviesTitleAndImage () {

		List<MovieEntity> listMovie = movieUseCase.getListMovieTitleAndImage();

		// convert entity to DTO
		List<MoviesDTOTitleAndImage> movieResponse = ObjectMapperUtils.mapAll(listMovie, MoviesDTOTitleAndImage.class);
		return new ResponseEntity<>(movieResponse, HttpStatus.OK);

	}



	@PutMapping("/movie/{movieId}")
	public ResponseEntity<MovieRsDTO> updateMovie (@PathVariable ("movieId") Long movieIdId, @RequestBody MovieRqDTO movieRqDTO) {

		// convert DTO to entity
		MovieEntity movieEntity = ObjectMapperUtils.map(movieRqDTO, MovieEntity.class);
		MovieEntity movie = movieUseCase.editMovie(movieIdId, movieEntity);

		// convert entity to DTO
		MovieRsDTO movieResponse = ObjectMapperUtils.map(movie, MovieRsDTO.class);
		log.info("updateMovie() - start: id = {}, movie = {}", movieIdId, movieRqDTO);
		return new ResponseEntity<>(movieResponse, HttpStatus.OK);

	}



	@DeleteMapping("/movie/{movieId}")
	public ResponseEntity<String> deleteMovie (@PathVariable ("movieId") Long movieId) {
		try {
			movieUseCase.deleteMovie(movieId);
			log.info("Request received for Movie deletion with id= " + movieId);
			return new ResponseEntity<>("Movie Delete Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Movie cannot deleted", HttpStatus.OK);
		}

	}



}



