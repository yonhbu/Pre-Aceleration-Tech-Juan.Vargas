package co.com.disney.api;




import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.disney.model.CharacterEntity;
import co.com.disney.model.dto.request.CharacterRqDTO;
import co.com.disney.model.dto.response.CharacterDTONameAndImage;
import co.com.disney.model.dto.response.CharacterRsDTO;
import co.com.disney.util.ObjectMapperUtils;
import co.com.event.usecase.disney.CharacterUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CharacterController {

	private final CharacterUseCase characterUseCase;


	@PostMapping("/character")
	public ResponseEntity<CharacterRsDTO> insertCharacter (@RequestBody CharacterRqDTO characterRqDTO) {

		// convert DTO to entity
		CharacterEntity characterEntity = ObjectMapperUtils.map(characterRqDTO, CharacterEntity.class);
		CharacterEntity character = characterUseCase.saveCharacter(characterEntity);

		// convert entity to DTO
		CharacterRsDTO characterResponse = ObjectMapperUtils.map(character, CharacterRsDTO.class);
		log.info("Request received for character insert", characterResponse.toString());
		return new ResponseEntity<>(characterResponse, HttpStatus.CREATED);

	}


	@GetMapping("/character")
	public ResponseEntity<List<CharacterDTONameAndImage>> listCharactersNameAndImage () {

		List<CharacterEntity> listCharacter = characterUseCase.getListCharacterNameAndImage();

		// convert entity to DTO
		List<CharacterDTONameAndImage> characterResponse = ObjectMapperUtils.mapAll(listCharacter, CharacterDTONameAndImage.class);
		return new ResponseEntity<>(characterResponse, HttpStatus.OK);

	}
	
	

	@PutMapping("/character/{characterId}")
	public ResponseEntity<CharacterRsDTO> updateCharacter (@PathVariable ("characterId") Long characterId, @RequestBody CharacterRqDTO characterRqDTO) {

		// convert DTO to entity
		CharacterEntity characterEntity = ObjectMapperUtils.map(characterRqDTO, CharacterEntity.class);
		CharacterEntity character = characterUseCase.editCharacter(characterId, characterEntity);

		// convert entity to DTO
		CharacterRsDTO characterResponse = ObjectMapperUtils.map(character, CharacterRsDTO.class);
		log.info("updateEvent() - start: id = {}, character = {}", characterId, characterRqDTO);
		return new ResponseEntity<>(characterResponse, HttpStatus.OK);

	}



	@DeleteMapping("/character/{characterId}")
	public ResponseEntity<String> deleteCharacter (@PathVariable ("characterId") Long characterId) {
		try {
			characterUseCase.deleteCharacter(characterId);
			log.info("Request received for Character deletion with id= " + characterId);
			return new ResponseEntity<>("Character Delete Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Character cannot deleted", HttpStatus.OK);
		}

	}





}



