package co.com.disney.model.gateways;

import java.util.List;
import co.com.disney.model.MovieEntity;


public interface MovieGateway {

	public MovieEntity createMovie (MovieEntity movie);

	public MovieEntity updateMovie (Integer id, MovieEntity movie);

	public void deleteMovie (Integer id);

	public List<MovieEntity> findMovieDetail ();
	
	public List<MovieEntity> findMovie ();


}
