package co.com.geographic.icons.controller;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

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

import co.com.geographic.icons.dto.country.CountryDTOImageAndDenomination;
import co.com.geographic.icons.dto.country.CountryRqDTO;
import co.com.geographic.icons.dto.country.CountryRsDTO;
import co.com.geographic.icons.exception.ResourceNotFoundException;
import co.com.geographic.icons.model.CountryEntity;
import co.com.geographic.icons.services.ICountryService;
import co.com.geographic.icons.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@RequestMapping("country")
@Slf4j
public class CountryController {

	private final ICountryService iCountryService;


	@PostMapping()
	public ResponseEntity<CountryRsDTO> insertIcon (@Valid @RequestBody CountryRqDTO countryRqDTO) {

		// convert DTO to entity
		CountryEntity countryEntity = ObjectMapperUtils.map(countryRqDTO, CountryEntity.class);
		CountryEntity country = iCountryService.save(countryEntity);

		// convert entity to DTO
		CountryRsDTO countryResponse = ObjectMapperUtils.map(country, CountryRsDTO.class);
		log.info("Request received for Country insert", country.toString());
		return new ResponseEntity<>(countryResponse, HttpStatus.CREATED);


	}


	@GetMapping("/fields")
	public ResponseEntity<List<CountryDTOImageAndDenomination>> listCountryImageAndDenomination () {

		List<CountryEntity> listCountry = iCountryService.getAllCountrys();

		// convert entity to DTO
		List<CountryDTOImageAndDenomination> countryResponse = ObjectMapperUtils.mapAll(listCountry, CountryDTOImageAndDenomination.class);
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);

	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CountryRsDTO>> getAllCountrys () {

		List<CountryEntity> listCountry = iCountryService.getAllCountrys();

		// convert entity to DTO
		List<CountryRsDTO> countryResponse = ObjectMapperUtils.mapAll(listCountry, CountryRsDTO.class);
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);

	}
	
	
	
	
	
	@GetMapping("/{countryId}")
	public ResponseEntity<CountryRsDTO> findCountry (@PathVariable ("countryId") Long countryId) {

		Optional<CountryEntity> country = iCountryService.findCountryforID(countryId);
		
		if (!country.isPresent()) {
			throw new ResourceNotFoundException ();
		}

		// convert entity to DTO
		CountryRsDTO countryResponse = ObjectMapperUtils.map(country, CountryRsDTO.class);
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);

	}
	
	
	@GetMapping()
	public ResponseEntity<List<CountryRsDTO>> getCountrysDetailsByFilters (
			                               @RequestParam (required = false) String name,
			                               @RequestParam (required = false) String numberHabitants,
			                               @RequestParam (required = false) Set<Long> continent,
			                               @RequestParam (required = false, defaultValue = "ASC") String order) {
	

		List<CountryEntity> listCountryFilter = iCountryService.getCountryByFilters(name,numberHabitants,continent,order);

		// convert entity to DTO
		List<CountryRsDTO> listCountryResponseFilter = ObjectMapperUtils.mapAll(listCountryFilter, CountryRsDTO.class);
		return new ResponseEntity<>(listCountryResponseFilter, HttpStatus.OK);

	}



	@PutMapping("/{countryId}")
	public ResponseEntity<CountryRsDTO> updateCountry (@PathVariable ("countryId") Long countryId, @Valid @RequestBody CountryRqDTO countryRqDTO) {

		// convert DTO to entity
		CountryEntity countryEntity = ObjectMapperUtils.map(countryRqDTO, CountryEntity.class);
		CountryEntity country = iCountryService.update(countryId, countryEntity);

		// convert entity to DTO
		CountryRsDTO countryResponse = ObjectMapperUtils.map(country, CountryRsDTO.class);
		log.info("updateCountry() - start: id = {}, country = {}", countryId, countryRqDTO);
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);

	}



	@DeleteMapping("/{countryId}")
	public ResponseEntity<String> deleteCountry (@PathVariable ("countryId") Long countryId) {
		try {
			iCountryService.delete(countryId);
			log.info("Request received for Country deletion with id= " + countryId);
			return new ResponseEntity<>("Country Delete Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Country cannot deleted", HttpStatus.OK);
		}

	}

	
	




}



