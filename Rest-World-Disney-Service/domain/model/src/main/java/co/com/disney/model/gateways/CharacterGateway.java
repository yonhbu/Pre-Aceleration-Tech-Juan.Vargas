package co.com.disney.model.gateways;

import java.util.List;
import co.com.disney.model.CharacterEntity;


public interface CharacterGateway {

	public CharacterEntity save (CharacterEntity characterEntity);
	
	public List<CharacterEntity> findAllCharacters();
	
	public CharacterEntity findCharacter(Long characterId);

	public CharacterEntity updateCharacter (Long id, CharacterEntity characterEntity);

	public String deleteCharacter (Long id);




	

}
