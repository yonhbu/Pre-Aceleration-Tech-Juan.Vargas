package co.com.disney.model.gateways;

import java.util.List;
import co.com.disney.model.MovieEntity;


public interface MovieGateway {

	public MovieEntity createMovie (MovieEntity movieEntity);

	public MovieEntity updateMovie (Long id, MovieEntity movieEntity);

	public void deleteMovie (Long id);

	public List<MovieEntity> findMovieDetail ();
	
	public List<MovieEntity> findMovie ();


}
