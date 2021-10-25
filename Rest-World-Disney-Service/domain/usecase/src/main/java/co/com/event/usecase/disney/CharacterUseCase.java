package co.com.event.usecase.disney;



import java.util.List;

import co.com.disney.model.CharacterEntity;
import co.com.disney.model.gateways.CharacterGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CharacterUseCase {

	private final CharacterGateway characterGateway;


	public CharacterEntity saveCharacter (CharacterEntity character) {
		return characterGateway.createCharacter(character);
	}
	
	
	public List<CharacterEntity> getListCharacterNameAndImage() {
		return characterGateway.findCharacter();
	}


	public CharacterEntity editCharacter(Long characterId, CharacterEntity characterEntity) {
		return characterGateway.updateCharacter(characterId, characterEntity);
	}


	public void deleteCharacter(Long characterId) {
		characterGateway.deleteCharacter(characterId);

	}



}
