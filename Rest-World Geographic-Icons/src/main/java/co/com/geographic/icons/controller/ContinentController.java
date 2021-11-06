package co.com.geographic.icons.controller;


import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.geographic.icons.dto.continent.ContinentRqDTO;
import co.com.geographic.icons.dto.continent.ContinentRsDTO;
import co.com.geographic.icons.services.IContinentService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class ContinentController {

	private final IContinentService iContinentService;


	@PostMapping("/continent")
	public ResponseEntity<ContinentRsDTO> insertContinent (@Valid @RequestBody ContinentRqDTO continentRqDTO) {
		ContinentRsDTO continentResponse = iContinentService.save(continentRqDTO);
		return new ResponseEntity<>(continentResponse, HttpStatus.CREATED);


	}
	
	
	@GetMapping("/continent")
	public ResponseEntity<List<ContinentRsDTO>> getAllContinents () {
		List<ContinentRsDTO> listContinentResponse = iContinentService.getAllContinents();
		return new ResponseEntity<>(listContinentResponse, HttpStatus.OK);

	}



}



