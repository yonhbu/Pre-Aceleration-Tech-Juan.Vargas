package co.com.disney.jpa.moviejpa;


import java.util.List;

import org.springframework.stereotype.Component;

import co.com.disney.jpa.characterjpa.CharacterDataJPA;
import co.com.disney.jpa.util.ObjectMapperUtils;
import co.com.disney.model.CharacterEntity;
import co.com.disney.model.MovieEntity;
import co.com.disney.model.commons.ResourceNotFoundException;
import co.com.disney.model.gateways.MovieGateway;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OperationDataMovieJPA implements MovieGateway {

	private final MovieRepositoryJPA movieRepositoryJPA;

	@Override
	public MovieEntity createMovie(MovieEntity movieEntity) {
		
		// Convert Entity Domain to Entity JPA
		MovieDataJPA movieRequest =  ObjectMapperUtils.map(movieEntity, MovieDataJPA.class);
		MovieDataJPA movieDataJPA = movieRepositoryJPA.save(movieRequest);

		// Convert Entity JPA to Entity Domain
		return ObjectMapperUtils.map(movieDataJPA, MovieEntity.class);
	}


	@Override
	public List<MovieEntity> findMovie() {
		List<MovieDataJPA> listMovieDataJPA = (List<MovieDataJPA>) movieRepositoryJPA.findAll();	
		// Convert Entity JPA to Entity Domain
		return ObjectMapperUtils.mapAll(listMovieDataJPA, MovieEntity.class);
	}

	@Override
	public MovieEntity updateMovie(Long id, MovieEntity movieEntity) {
		
		MovieDataJPA movieFindDataJPA = movieRepositoryJPA.findMovieDataJPAByidMovie(id);

		if (movieFindDataJPA == null) {
			throw new ResourceNotFoundException(MovieEntity.class, id);
		}

		movieFindDataJPA.setImage(movieEntity.getImage());
		movieFindDataJPA.setTitle(movieEntity.getTitle());
		movieFindDataJPA.setCreationDate(movieEntity.getCreationDate());
		movieFindDataJPA.setRating(movieEntity.getRating());

		movieRepositoryJPA.save(movieFindDataJPA);
		// Convert Entity JPA to Entity Domain
		return ObjectMapperUtils.map(movieFindDataJPA, MovieEntity.class);
	}

	@Override
	public void deleteMovie(Long id) {
		movieRepositoryJPA.deleteById(id);
		
	}
	
	
	@Override
	public List<MovieEntity> findMovieDetail() {
		return null;
	
	}

}