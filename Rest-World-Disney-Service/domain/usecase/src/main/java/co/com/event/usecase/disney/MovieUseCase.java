package co.com.event.usecase.disney;


import java.util.List;

import co.com.disney.model.MovieEntity;
import co.com.disney.model.gateways.MovieGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovieUseCase {

	private final MovieGateway movieGateway;

	public MovieEntity saveMovie(MovieEntity movieEntity) {
		return movieGateway.createMovie(movieEntity);
	}
	
	
	public List<MovieEntity> getListMovieTitleAndImage() {
		return movieGateway.findMovie();
	
	}

	
	public MovieEntity editMovie(Long movieId, MovieEntity movieEntity) {
		return movieGateway.updateMovie(movieId, movieEntity);
	}

	public void deleteMovie(Long movieId) {
		movieGateway.deleteMovie(movieId);
		
	}


}
