package co.com.disney.api;




import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.disney.model.dto.request.CharacterRqDTO;
import co.com.disney.model.dto.response.CharacterDTONameAndImage;
import co.com.disney.model.dto.response.CharacterRsDTO;
import co.com.event.usecase.disney.CharacterUseCase;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("character")
@RequiredArgsConstructor
public class CharacterController {

	private final CharacterUseCase characterUseCase;


	@PostMapping()
	public ResponseEntity<CharacterRsDTO> insert (@RequestBody CharacterRqDTO characterRqDTO) {
		CharacterRsDTO characterResponse = characterUseCase.saveCharacter(characterRqDTO);
		return new ResponseEntity<>(characterResponse, HttpStatus.CREATED);

	}


	@GetMapping("/field")
	public ResponseEntity<List<CharacterDTONameAndImage>> listCharactersNameAndImage () {
		List<CharacterDTONameAndImage> characterResponse = characterUseCase.getListCharacterNameAndImage();
		return new ResponseEntity<>(characterResponse, HttpStatus.OK);

	}


	@GetMapping("/all")
	public ResponseEntity<List<CharacterRsDTO>> getAll () {
		List<CharacterRsDTO> characterResponse = characterUseCase.getAllIcons();
		return new ResponseEntity<>(characterResponse, HttpStatus.OK);

	}


	@GetMapping("/{characterId}")
	public ResponseEntity<CharacterRsDTO> findCharacterforID (@PathVariable ("characterId") Long characterId) {

		CharacterRsDTO characterResponse = characterUseCase.findCharacter(characterId);
		return new ResponseEntity<>(characterResponse, HttpStatus.OK);

	}



	@PutMapping("/{characterId}")
	public ResponseEntity<CharacterRsDTO> update (@PathVariable ("characterId") Long characterId, @RequestBody CharacterRqDTO characterRqDTO) {
		CharacterRsDTO characterResponse = characterUseCase.updateCharacter(characterId, characterRqDTO);
		return new ResponseEntity<>(characterResponse, HttpStatus.OK);

	}



	@DeleteMapping("/{characterId}")
	public ResponseEntity<String> delete (@PathVariable ("characterId") Long characterId) {

		String response = characterUseCase.deleteCharacter(characterId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}



