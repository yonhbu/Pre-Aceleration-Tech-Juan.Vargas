package co.com.geographic.icons.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.geographic.icons.dto.continent.ContinentRqDTO;
import co.com.geographic.icons.dto.continent.ContinentRsDTO;
import co.com.geographic.icons.model.ContinentEntity;
import co.com.geographic.icons.services.IContinentService;
import co.com.geographic.icons.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ContinentController {

	private final IContinentService iContinentService;


	@PostMapping("/continent")
	public ResponseEntity<ContinentRsDTO> insertContinent (@RequestBody ContinentRqDTO continentRqDTO) {

		// convert DTO to entity
		ContinentEntity continentEntity = ObjectMapperUtils.map(continentRqDTO, ContinentEntity.class);
		ContinentEntity continent = iContinentService.save(continentEntity);

		// convert entity to DTO
		ContinentRsDTO continentResponse = ObjectMapperUtils.map(continent, ContinentRsDTO.class);
		log.info("Request received for Continent insert", continent.toString());
		return new ResponseEntity<>(continentResponse, HttpStatus.CREATED);


	}
	
	
	@GetMapping("/continent")
	public ResponseEntity<List<ContinentRsDTO>> getAllContinents () {

		List<ContinentEntity> listContinent = iContinentService.getAllContinents();

		// convert entity to DTO
		List<ContinentRsDTO> listContinentResponse = ObjectMapperUtils.mapAll(listContinent, ContinentRsDTO.class);
		return new ResponseEntity<>(listContinentResponse, HttpStatus.OK);

	}



}



