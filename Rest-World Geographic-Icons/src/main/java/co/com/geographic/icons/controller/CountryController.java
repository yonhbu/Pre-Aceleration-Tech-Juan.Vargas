package co.com.geographic.icons.controller;


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

import co.com.geographic.icons.dto.country.CountryDTOImageAndDenomination;
import co.com.geographic.icons.dto.country.CountryRqDTO;
import co.com.geographic.icons.dto.country.CountryRsDTO;
import co.com.geographic.icons.model.CountryEntity;
import co.com.geographic.icons.services.impl.CountryServiceImpl;
import co.com.geographic.icons.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CountryController {

	private final CountryServiceImpl countryServiceImpl;


	@PostMapping("/country")
	public ResponseEntity<CountryRsDTO> insertIcon (@RequestBody CountryRqDTO countryRqDTO) {

		// convert DTO to entity
		CountryEntity countryEntity = ObjectMapperUtils.map(countryRqDTO, CountryEntity.class);
		CountryEntity country = countryServiceImpl.save(countryEntity);

		// convert entity to DTO
		CountryRsDTO countryResponse = ObjectMapperUtils.map(country, CountryRsDTO.class);
		log.info("Request received for Country insert", country.toString());
		return new ResponseEntity<>(countryResponse, HttpStatus.CREATED);


	}


	@GetMapping("/country")
	public ResponseEntity<List<CountryDTOImageAndDenomination>> listCountryImageAndDenomination () {

		List<CountryEntity> listCountry = countryServiceImpl.getAllCountrys();

		// convert entity to DTO
		List<CountryDTOImageAndDenomination> countryResponse = ObjectMapperUtils.mapAll(listCountry, CountryDTOImageAndDenomination.class);
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);

	}



	@PutMapping("/country/{countryId}")
	public ResponseEntity<CountryRsDTO> updateCountry (@PathVariable ("countryId") Long countryId, @RequestBody CountryRqDTO countryRqDTO) {

		// convert DTO to entity
		CountryEntity countryEntity = ObjectMapperUtils.map(countryRqDTO, CountryEntity.class);
		CountryEntity country = countryServiceImpl.update(countryId, countryEntity);

		// convert entity to DTO
		CountryRsDTO countryResponse = ObjectMapperUtils.map(country, CountryRsDTO.class);
		log.info("updateCountry() - start: id = {}, country = {}", countryId, countryRqDTO);
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);

	}



	@DeleteMapping("/country/{countryId}")
	public ResponseEntity<String> deleteCountry (@PathVariable ("countryId") Long countryId) {
		try {
			countryServiceImpl.delete(countryId);
			log.info("Request received for Country deletion with id= " + countryId);
			return new ResponseEntity<>("Country Delete Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Country cannot deleted", HttpStatus.OK);
		}

	}





}



