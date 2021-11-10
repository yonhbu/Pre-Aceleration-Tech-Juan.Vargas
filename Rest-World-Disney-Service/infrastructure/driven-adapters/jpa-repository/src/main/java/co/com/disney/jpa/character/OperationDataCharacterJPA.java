package co.com.disney.jpa.character;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;

import co.com.disney.jpa.util.ObjectMapperUtils;
import co.com.disney.model.CharacterEntity;
import co.com.disney.model.dto.request.CharactersFiltersDTO;
import co.com.disney.model.exception.ResourceNotFoundException;
import co.com.disney.model.gateways.CharacterGateway;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class OperationDataCharacterJPA implements CharacterGateway {

	private final CharacterRepositoryJPA characterRepositoryJPA;
	
	private final CharacterSpecification iconSpecification;
	

	@Override
	public CharacterEntity save (CharacterEntity characterEntity) {

		CharacterDataJPA characterRequest =  ObjectMapperUtils.map(characterEntity, CharacterDataJPA.class);
		CharacterDataJPA characterDataJPA = characterRepositoryJPA.save(characterRequest);
		return ObjectMapperUtils.map(characterDataJPA, CharacterEntity.class);


	}
	
	@Override
	public List<CharacterEntity> findAllCharacters() {
		List<CharacterDataJPA> listCharacterDataJPA = (List<CharacterDataJPA>) characterRepositoryJPA.findAll();	
		return ObjectMapperUtils.mapAll(listCharacterDataJPA, CharacterEntity.class);
	}
	
	

	@Override
	public CharacterEntity findCharacter(Long characterId) {
		Optional<CharacterDataJPA> characterDataJPA = characterRepositoryJPA.findById(characterId);
		if (!characterDataJPA.isPresent()) {
			throw new ResourceNotFoundException ();
		}
		return ObjectMapperUtils.map(characterDataJPA.get(), CharacterEntity.class);
	}

	
	
	@Override
	public List<CharacterEntity> getCharactersByFilters(String name, Integer age, Set<Long> movies, String order) {
		CharactersFiltersDTO charactersFiltersDTO = new CharactersFiltersDTO (name,age,movies,order);	

		List<CharacterDataJPA> listCharacterResponseFilter = characterRepositoryJPA.findAll(iconSpecification.getByFilter(charactersFiltersDTO));
		return ObjectMapperUtils.mapAll(listCharacterResponseFilter, CharacterEntity.class);
	}


	

	@Override
	public CharacterEntity updateCharacter (Long id, CharacterEntity characterEntity) {
		
		CharacterDataJPA character = ObjectMapperUtils.map(characterEntity, CharacterDataJPA.class);
		CharacterDataJPA characterFind = characterRepositoryJPA.findByIdCharacter(id);

		if (characterFind == null) {
			throw new ResourceNotFoundException();
		}

		characterFind.setImage(character.getImage());
		characterFind.setName(character.getName());
		characterFind.setAge(character.getAge());
		characterFind.setWeight(character.getWeight());
		characterFind.setHistory(character.getHistory());
		characterFind.setListMovie(character.getListMovie());

		characterRepositoryJPA.save(characterFind);
		return ObjectMapperUtils.map(characterFind, CharacterEntity.class);

	}

	@Override
	public String deleteCharacter(Long id) {
		
		try {
			characterRepositoryJPA.deleteById(id);
			return "Character Delete Success";
		} catch (Exception e) {
			return "Character cannot deleted";
		}
		
	}





}
