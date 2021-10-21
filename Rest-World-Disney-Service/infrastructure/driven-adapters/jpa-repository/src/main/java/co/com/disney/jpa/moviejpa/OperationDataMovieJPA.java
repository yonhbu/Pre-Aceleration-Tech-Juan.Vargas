package co.com.disney.jpa.moviejpa;


import java.util.List;

import org.springframework.stereotype.Component;

import co.com.disney.model.MovieEntity;
import co.com.disney.model.gateways.MovieGateway;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OperationDataMovieJPA implements MovieGateway {

	private final MovieRepositoryJPA movieRepositoryJPA;

	@Override
	public MovieEntity createMovie(MovieEntity movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovieEntity updateMovie(Integer id, MovieEntity movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMovie(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MovieEntity> findMovieDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieEntity> findMovie() {
		// TODO Auto-generated method stub
		return null;
	}

}