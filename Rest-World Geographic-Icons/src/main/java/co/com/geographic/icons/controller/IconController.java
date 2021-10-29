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

import co.com.geographic.icons.dto.icon.IconDTOImageAndDenomination;
import co.com.geographic.icons.dto.icon.IconRqDTO;
import co.com.geographic.icons.dto.icon.IconRsDTO;
import co.com.geographic.icons.model.IconEntity;
import co.com.geographic.icons.services.IIconService;
import co.com.geographic.icons.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@Slf4j
public class IconController {

	private final IIconService iIconService;


	@PostMapping("/icons")
	public ResponseEntity<IconRsDTO> insertIcon (@RequestBody IconRqDTO iconRqDTO) {

		// convert DTO to entity
		IconEntity iconEntity = ObjectMapperUtils.map(iconRqDTO, IconEntity.class);
		IconEntity icon = iIconService.save(iconEntity);

		// convert entity to DTO
		IconRsDTO iconResponse = ObjectMapperUtils.map(icon, IconRsDTO.class);
		log.info("Request received for Icon insert", icon.toString());
		return new ResponseEntity<>(iconResponse, HttpStatus.CREATED);


	}


	@GetMapping("/icons")
	public ResponseEntity<List<IconDTOImageAndDenomination>> listIconImageAndDenomination () {

		List<IconEntity> listIcon = iIconService.getAllIcons();

		// convert entity to DTO
		List<IconDTOImageAndDenomination> iconResponse = ObjectMapperUtils.mapAll(listIcon, IconDTOImageAndDenomination.class);
		return new ResponseEntity<>(iconResponse, HttpStatus.OK);

	}
	
	
	@GetMapping("/icons/all")
	public ResponseEntity<List<IconRsDTO>> getAllIcons () {

		List<IconEntity> listIcon = iIconService.getAllIcons();

		// convert entity to DTO
		List<IconRsDTO> iconResponse = ObjectMapperUtils.mapAll(listIcon, IconRsDTO.class);
		return new ResponseEntity<>(iconResponse, HttpStatus.OK);

	}
	
	

	@PutMapping("/icons/{iconId}")
	public ResponseEntity<IconRsDTO> updateIcons (@PathVariable ("iconId") Long iconId, @RequestBody IconRqDTO iconRqDTO) {

		// convert DTO to entity
		IconEntity iconEntity = ObjectMapperUtils.map(iconRqDTO, IconEntity.class);
		IconEntity icon = iIconService.update(iconId, iconEntity);

		// convert entity to DTO
		IconRsDTO iconResponse = ObjectMapperUtils.map(icon, IconRsDTO.class);
		log.info("updateIcon() - start: id = {}, icon = {}", iconId, iconRqDTO);
		return new ResponseEntity<>(iconResponse, HttpStatus.OK);

	}



	@DeleteMapping("/icons/{iconId}")
	public ResponseEntity<String> deleteIcon (@PathVariable ("iconId") Long iconId) {
		try {
			iIconService.delete(iconId);
			log.info("Request received for Icon deletion with id= " + iconId);
			return new ResponseEntity<>("Icon Delete Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Icon cannot deleted", HttpStatus.OK);
		}

	}





}



