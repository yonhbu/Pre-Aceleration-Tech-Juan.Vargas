package co.com.disney.jpa.moviejpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositoryJPA extends CrudRepository<MovieDataJPA, Long> {
	
	MovieDataJPA findByidMovie(Long id);
	

}
