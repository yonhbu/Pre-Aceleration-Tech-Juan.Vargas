package co.com.disney.model.gateways;

import java.util.List;
import java.util.Set;

import co.com.disney.model.CharacterEntity;


public interface CharacterGateway {

	public CharacterEntity save (CharacterEntity characterEntity);
	
	public List<CharacterEntity> findAllCharacters();
	
	public CharacterEntity findCharacter(Long characterId);
	
	public List<CharacterEntity> getCharactersByFilters (String name, Integer age, Set<Long> movies, String order);

	public CharacterEntity updateCharacter (Long id, CharacterEntity characterEntity);

	public String deleteCharacter (Long id);




	

}
