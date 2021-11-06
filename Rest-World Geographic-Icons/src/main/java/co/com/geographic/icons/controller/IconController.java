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

import co.com.geographic.icons.dto.icon.IconDTOImageAndDenomination;
import co.com.geographic.icons.dto.icon.IconRqDTO;
import co.com.geographic.icons.dto.icon.IconRsDTO;
import co.com.geographic.icons.services.IIconService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@RequestMapping("icons")
@Slf4j
public class IconController {

	private final IIconService iIconService;


	@PostMapping()
	public ResponseEntity<IconRsDTO> insertIcon (@Valid @RequestBody IconRqDTO iconRqDTO) {

		IconRsDTO iconResponse = iIconService.save(iconRqDTO);
		log.info("Request received for Icon insert", iconRqDTO.toString());
		return new ResponseEntity<>(iconResponse, HttpStatus.CREATED);


	}


	@GetMapping("/fields")
	public ResponseEntity<List<IconDTOImageAndDenomination>> listIconImageAndDenomination () {

		List<IconDTOImageAndDenomination> listIconResponse = iIconService.getAllIconsSomeFields();
		return new ResponseEntity<>(listIconResponse, HttpStatus.OK);

	}


	@GetMapping("/all")
	public ResponseEntity<List<IconRsDTO>> getAllIcons () {

		List<IconRsDTO> iconResponse = iIconService.getAllIcons();
		return new ResponseEntity<>(iconResponse, HttpStatus.OK);

	}


	@GetMapping("/{iconId}")
	public ResponseEntity<IconRsDTO> findIconsforID (@PathVariable ("iconId") Long iconId) {

		IconRsDTO iconResponse = iIconService.findIcon(iconId);
		return new ResponseEntity<>(iconResponse, HttpStatus.OK);

	}


	@GetMapping()
	public ResponseEntity<List<IconRsDTO>> getIconsDetailsByFilters (
			@RequestParam (required = false) String name,
			@RequestParam (required = false) String date,
			@RequestParam (required = false) Long altitude,
			@RequestParam (required = false) Set<Long> countrys,
			@RequestParam (required = false, defaultValue = "ASC") String order) {


		List<IconRsDTO> listIconResponseFilter = iIconService.getIconByFilters(name,date,altitude,countrys,order);
		return new ResponseEntity<>(listIconResponseFilter, HttpStatus.OK);

	}




	@PutMapping("/{iconId}")
	public ResponseEntity<IconRsDTO> updateIcons (@PathVariable ("iconId") Long iconId, @Valid @RequestBody IconRqDTO iconRqDTO) {

		IconRsDTO iconResponse = iIconService.update(iconId, iconRqDTO);
		return new ResponseEntity<>(iconResponse, HttpStatus.OK);

	}



	@DeleteMapping("/{iconId}")
	public ResponseEntity<String> deleteIcon (@PathVariable ("iconId") Long iconId) {

		String response = iIconService.delete(iconId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}





}



