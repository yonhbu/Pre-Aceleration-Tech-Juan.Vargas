package co.com.disney.model.gateways;

import java.util.List;
import co.com.disney.model.CharacterEntity;


public interface CharacterGateway {

	public CharacterEntity createCharacter (CharacterEntity characterEntity);

	public CharacterEntity updateCharacter (Long id, CharacterEntity characterEntity);

	public void deleteCharacter (Long id);

	public List<CharacterEntity> findCharacterDetail ();
	
	public List<CharacterEntity> findCharacter ();


}
