package co.com.disney.jpa.gender;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepositoryJPA extends CrudRepository<GenreDataJPA, Long> {
	

}
