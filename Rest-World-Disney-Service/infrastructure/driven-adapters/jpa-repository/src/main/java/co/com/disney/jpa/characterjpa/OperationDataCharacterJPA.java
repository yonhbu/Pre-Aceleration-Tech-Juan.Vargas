package co.com.disney.jpa.characterjpa;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.disney.jpa.util.ObjectMapperUtils;
import co.com.disney.model.CharacterEntity;
import co.com.disney.model.commons.ResourceNotFoundException;
import co.com.disney.model.gateways.CharacterGateway;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class OperationDataCharacterJPA implements CharacterGateway {

	private final CharacterRepositoryJPA characterRepositoryJPA;

	@Override
	public CharacterEntity createCharacter(CharacterEntity characterEntity) {

		// Convert Entity Domain to Entity JPA
		CharacterDataJPA characterRequest =  ObjectMapperUtils.map(characterEntity, CharacterDataJPA.class);
		CharacterDataJPA characterDataJPA = characterRepositoryJPA.save(characterRequest);

		// Convert Entity JPA to Entity Domain
		return ObjectMapperUtils.map(characterDataJPA, CharacterEntity.class);


	}
	
	
	@Override
	public List<CharacterEntity> findCharacter () {
		List<CharacterDataJPA> listCharacterDataJPA = (List<CharacterDataJPA>) characterRepositoryJPA.findAll();	
		// Convert Entity JPA to Entity Domain
		return ObjectMapperUtils.mapAll(listCharacterDataJPA, CharacterEntity.class);

	}

	@Override
	public CharacterEntity updateCharacter (Long id, CharacterEntity characterEntity) {

		CharacterDataJPA characterFindDataJPA = characterRepositoryJPA.findCharacterByIdCharacter(id);

		if (characterFindDataJPA == null) {
			throw new ResourceNotFoundException(CharacterEntity.class, id);
		}

		characterFindDataJPA.setImage(characterEntity.getImage());
		characterFindDataJPA.setName(characterEntity.getName());
		characterFindDataJPA.setAge(characterEntity.getAge());
		characterFindDataJPA.setWeight(characterEntity.getWeight());
		characterFindDataJPA.setHistory(characterEntity.getHistory());


		characterRepositoryJPA.save(characterFindDataJPA);
		// Convert Entity JPA to Entity Domain
		return ObjectMapperUtils.map(characterFindDataJPA, CharacterEntity.class);

	}

	@Override
	public void deleteCharacter(Long id) {
		characterRepositoryJPA.deleteById(id);
	}



	@Override
	public List<CharacterEntity> findCharacterDetail() {
		// TODO Auto-generated method stub
		return null;
	}



}
