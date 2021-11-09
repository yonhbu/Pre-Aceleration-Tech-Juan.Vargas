package co.com.event.usecase.disney;

import java.util.List;


import co.com.disney.model.CharacterEntity;
import co.com.disney.model.dto.request.CharacterRqDTO;
import co.com.disney.model.dto.response.CharacterDTONameAndImage;
import co.com.disney.model.dto.response.CharacterRsDTO;
import co.com.disney.model.exception.ResourceNotFoundException;
import co.com.disney.model.gateways.CharacterGateway;
import co.com.event.usecase.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CharacterUseCase {

	private final CharacterGateway characterGateway;


	public CharacterRsDTO saveCharacter (CharacterRqDTO characterRqDTO) {
		CharacterEntity characterEntity = ObjectMapperUtils.map(characterRqDTO, CharacterEntity.class);
		CharacterEntity character = characterGateway.save(characterEntity);
		return ObjectMapperUtils.map(character, CharacterRsDTO.class);
	}


	public List<CharacterDTONameAndImage> getListCharacterNameAndImage () {
		List<CharacterEntity> listCharacters = characterGateway.findAllCharacters();
		return  ObjectMapperUtils.mapAll(listCharacters, CharacterDTONameAndImage.class);
	}

	public List<CharacterRsDTO> getAllCharacter() {
		List<CharacterEntity> listCharacters = characterGateway.findAllCharacters();
		return  ObjectMapperUtils.mapAll(listCharacters, CharacterRsDTO.class);
	}


	public CharacterRsDTO findCharacter(Long characterId) {
		CharacterEntity character = characterGateway.findCharacter (characterId);
		return ObjectMapperUtils.map(character, CharacterRsDTO.class);
	}


	public CharacterRsDTO updateCharacter(Long characterId, CharacterRqDTO characterRqDTO) {
		CharacterEntity characterEntity = ObjectMapperUtils.map(characterRqDTO, CharacterEntity.class);
		CharacterEntity characterFind = characterGateway.findCharacter(characterId);

		if (characterFind == null) {
			throw new ResourceNotFoundException();
		}

		characterFind.setImage(characterEntity.getImage());
		characterFind.setName(characterEntity.getName());
		characterFind.setWeight(characterEntity.getWeight());
		characterFind.setAge(characterEntity.getAge());
		characterFind.setHistory(characterEntity.getHistory());

		characterGateway.save(characterEntity);
		return ObjectMapperUtils.map(characterFind, CharacterRsDTO.class);

	}


	public String deleteCharacter(Long characterId) {
		try {
			characterGateway.deleteCharacter(characterId);
			return "Character Delete Success";
		} catch (Exception e) {
			return "Character cannot deleted";
		}	

	}






}
