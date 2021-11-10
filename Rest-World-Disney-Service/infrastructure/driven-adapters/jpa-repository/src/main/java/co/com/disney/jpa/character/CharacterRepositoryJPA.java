package co.com.disney.jpa.character;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CharacterRepositoryJPA extends CrudRepository<CharacterDataJPA, Long>, JpaSpecificationExecutor<CharacterDataJPA> {
	
	List<CharacterDataJPA> findAll (Specification<CharacterDataJPA> spec);

	CharacterDataJPA findByIdCharacter(Long id);
		
}
