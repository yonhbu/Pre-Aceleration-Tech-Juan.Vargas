package co.com.disney.jpa.movie;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositoryJPA extends CrudRepository<MovieDataJPA, Long>, JpaSpecificationExecutor<MovieDataJPA> {
	
	List<MovieDataJPA> findAll (Specification<MovieDataJPA> spec);
	
	MovieDataJPA findByidMovie(Long id);
	

}
