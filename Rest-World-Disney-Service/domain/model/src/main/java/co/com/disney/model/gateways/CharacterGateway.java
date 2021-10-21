package co.com.disney.model.gateways;

import java.util.List;
import co.com.disney.model.CharacterEntity;


public interface CharacterGateway {

	public CharacterEntity createCharacter (CharacterEntity character);

	public CharacterEntity updateCharacter (Integer id, CharacterEntity character);

	public void deleteCharacter (Integer id);

	public List<CharacterEntity> findCharacterDetail ();
	
	public List<CharacterEntity> findCharacter ();


}
