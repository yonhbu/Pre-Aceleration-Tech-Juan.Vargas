package co.com.disney.api;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.disney.model.dto.request.GenreRqDTO;
import co.com.disney.model.dto.response.GenreRsDTO;
import co.com.event.usecase.disney.GenreUseCase;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("genre")
@RequiredArgsConstructor
public class GenreController {

	private final GenreUseCase genreUseCase;



	@PostMapping()
	public ResponseEntity<GenreRsDTO> insertGenre (@RequestBody GenreRqDTO genreRqDTO) {
		GenreRsDTO genreResponse = genreUseCase.saveGenre(genreRqDTO);
		return new ResponseEntity<>(genreResponse, HttpStatus.CREATED);

	}


	@GetMapping()
	public ResponseEntity<List<GenreRsDTO>> getAll () {
		List<GenreRsDTO> genresResponse = genreUseCase.getAllGenres();
		return new ResponseEntity<>(genresResponse, HttpStatus.OK);

	}

}




