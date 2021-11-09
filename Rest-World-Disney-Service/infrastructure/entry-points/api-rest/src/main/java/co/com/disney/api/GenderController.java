package co.com.disney.api;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.disney.model.dto.request.GenderRqDTO;
import co.com.disney.model.dto.response.GenderRsDTO;
import co.com.event.usecase.disney.GenderUseCase;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("gender")
@RequiredArgsConstructor
public class GenderController {

	private final GenderUseCase genderUseCase;



	@PostMapping()
	public ResponseEntity<GenderRsDTO> insertGender (@RequestBody GenderRqDTO genderRqDTO) {
		GenderRsDTO genderResponse = genderUseCase.saveGender(genderRqDTO);
		return new ResponseEntity<>(genderResponse, HttpStatus.CREATED);

	}


	@GetMapping()
	public ResponseEntity<List<GenderRsDTO>> getAll () {
		List<GenderRsDTO> genderResponse = genderUseCase.getAllGenders();
		return new ResponseEntity<>(genderResponse, HttpStatus.OK);

	}

}




