package co.com.disney.model.gateways;

import java.util.List;
import co.com.disney.model.CharacterEntity;
import co.com.disney.model.dto.response.CharacterDTONameAndImage;


public interface CharacterGateway {

	public CharacterEntity save (CharacterEntity characterEntity);
	
	public List<CharacterDTONameAndImage> getListCharacterNameAndImage();
	
	public List<CharacterEntity> findAllCharacters();
	
	public CharacterEntity findCharacter(Long characterId);

	public CharacterEntity updateCharacter (Long id, CharacterEntity characterEntity);

	public String deleteCharacter (Long id);




	

}
