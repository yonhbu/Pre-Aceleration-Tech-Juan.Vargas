package co.com.disney.jpa.genderjpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepositoryJPA extends CrudRepository<GenderDataJPA, Integer> {
	

}
