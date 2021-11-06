package co.com.geographic.icons.controller;


import java.util.List;
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
import co.com.geographic.icons.services.ICountryService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("country")
public class CountryController {

	private final ICountryService iCountryService;


	@PostMapping()
	public ResponseEntity<CountryRsDTO> insertIcon (@Valid @RequestBody CountryRqDTO countryRqDTO) {

		CountryRsDTO countryResponse  = iCountryService.save(countryRqDTO);
		return new ResponseEntity<>(countryResponse, HttpStatus.CREATED);

	}


	@GetMapping("/fields")
	public ResponseEntity<List<CountryDTOImageAndDenomination>> listCountryImageAndDenomination () {

		List<CountryDTOImageAndDenomination> countryResponse = iCountryService.getListCountryImageAndDenomination();
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);

	}

	@GetMapping("/all")
	public ResponseEntity<List<CountryRsDTO>> getAllCountrys () {

		List<CountryRsDTO> countryResponse = iCountryService.getAllCountrys();
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);

	}


	@GetMapping("/{countryId}")
	public ResponseEntity<CountryRsDTO> findCountry (@PathVariable ("countryId") Long countryId) {

		CountryRsDTO countryResponse = iCountryService.findCountryforID(countryId);
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);

	}


	@GetMapping()
	public ResponseEntity<List<CountryRsDTO>> getCountrysDetailsByFilters (
			@RequestParam (required = false) String name,
			@RequestParam (required = false) Set<Long> continent,
			@RequestParam (required = false, defaultValue = "ASC") String order) {


		List<CountryRsDTO> listCountryResponseFilter = iCountryService.getCountryByFilters(name,continent,order);
		return new ResponseEntity<>(listCountryResponseFilter, HttpStatus.OK);

	}



	@PutMapping("/{countryId}")
	public ResponseEntity<CountryRsDTO> updateCountry (@PathVariable ("countryId") Long countryId, @Valid @RequestBody CountryRqDTO countryRqDTO) {

		CountryRsDTO countryResponse = iCountryService.update(countryId, countryRqDTO);
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);

	}



	@DeleteMapping("/{countryId}")
	public ResponseEntity<String> deleteCountry (@PathVariable ("countryId") Long countryId) {

		String response = iCountryService.delete(countryId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}




