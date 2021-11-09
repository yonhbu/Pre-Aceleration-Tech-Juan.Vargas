package co.com.disney.jpa.characterjpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CharacterRepositoryJPA extends CrudRepository<CharacterDataJPA, Long> {
	
	CharacterDataJPA findByIdCharacter(Long id);
		
}
