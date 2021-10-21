package co.com.disney.jpa.moviejpa;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositoryJPA extends CrudRepository<MovieDataJPA, Integer> {
	
	@Query("SELECT p FROM MovieDataJPA p WHERE p.title = :title")
	List<MovieDataJPA> findMovieDataJPAByTitle (@Param("title") final String title);
	
	MovieDataJPA findMovieDataJPAByidMovie(Long id);
	

}
