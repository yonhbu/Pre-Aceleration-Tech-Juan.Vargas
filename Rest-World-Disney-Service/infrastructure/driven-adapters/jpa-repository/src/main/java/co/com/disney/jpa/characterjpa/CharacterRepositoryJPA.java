package co.com.disney.jpa.characterjpa;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CharacterRepositoryJPA extends CrudRepository<CharacterDataJPA, Integer> {
	
	
	@Query("SELECT p FROM CharacterDataJPA p WHERE p.name = :name")
	List<CharacterDataJPA> findCharacterByName(@Param("name") final String name);

	CharacterDataJPA findCharacterByIdCharacter(Long id);
		
}
