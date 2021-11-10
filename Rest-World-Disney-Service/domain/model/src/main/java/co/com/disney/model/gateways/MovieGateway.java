package co.com.disney.model.gateways;

import java.util.List;
import java.util.Set;
import co.com.disney.model.MovieEntity;


public interface MovieGateway {

	public MovieEntity save (MovieEntity movieEntity);
	
	public List<MovieEntity> findAllMovies();

	public MovieEntity findMovie (Long id);
	
	public List<MovieEntity> getMoviesByFilters (String name, Set<Long> gender, String order);
	
	public MovieEntity update (Long id, MovieEntity movieEntity);

	public String delete (Long id);

}
