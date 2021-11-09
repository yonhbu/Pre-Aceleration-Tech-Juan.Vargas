package co.com.disney.model.gateways;

import java.util.List;
import co.com.disney.model.MovieEntity;


public interface MovieGateway {

	public MovieEntity save (MovieEntity movieEntity);
	
	public List<MovieEntity> findAllMovies();

	public MovieEntity findMovie (Long id);
	
	public MovieEntity update (Long id, MovieEntity movieEntity);

	public String delete (Long id);

}
