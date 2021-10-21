package co.com.disney.jpa.characterjpa;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.disney.model.CharacterEntity;
import co.com.disney.model.gateways.CharacterGateway;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class OperationDataCharacterJPA implements CharacterGateway {

	private final CharacterRepositoryJPA characterRepositoryJPA;

	@Override
	public CharacterEntity createCharacter(CharacterEntity character) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharacterEntity updateCharacter(Integer id, CharacterEntity character) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCharacter(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CharacterEntity> findCharacterDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CharacterEntity> findCharacter() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
