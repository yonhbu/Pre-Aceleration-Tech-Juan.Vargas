package co.com.disney.jpa.movie;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;
import co.com.disney.jpa.util.ObjectMapperUtils;
import co.com.disney.model.MovieEntity;
import co.com.disney.model.dto.request.MoviesFiltersDTO;
import co.com.disney.model.exception.ResourceNotFoundException;
import co.com.disney.model.gateways.MovieGateway;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OperationDataMovieJPA implements MovieGateway {

	private final MovieRepositoryJPA movieRepositoryJPA;
	
	private final MovieSpecification movieSpecification;
	
	

	@Override
	public MovieEntity save (MovieEntity movieEntity) {
		MovieDataJPA movieRequest =  ObjectMapperUtils.map(movieEntity, MovieDataJPA.class);
		MovieDataJPA movieDataJPA = movieRepositoryJPA.save(movieRequest);
		return ObjectMapperUtils.map(movieDataJPA, MovieEntity.class);
	}


	@Override
	public List<MovieEntity> findAllMovies () {
		List<MovieDataJPA> listMovieDataJPA = (List<MovieDataJPA>) movieRepositoryJPA.findAll();	
		return ObjectMapperUtils.mapAll(listMovieDataJPA, MovieEntity.class);
	}



	@Override
	public MovieEntity findMovie (Long id) {
		Optional<MovieDataJPA> movieDataJPA = movieRepositoryJPA.findById(id);
		if (!movieDataJPA.isPresent()) {
			throw new ResourceNotFoundException ();
		}
		return ObjectMapperUtils.map(movieDataJPA.get(), MovieEntity.class);
	}
	
	
	@Override
	public List<MovieEntity> getMoviesByFilters(String name, Set<Long> genre, String order) {
		MoviesFiltersDTO moviesFiltersDTO = new MoviesFiltersDTO (name,genre,order);	

		List<MovieDataJPA> listMoviesResponseFilter = movieRepositoryJPA.findAll(movieSpecification.getByFilter(moviesFiltersDTO));
		return ObjectMapperUtils.mapAll(listMoviesResponseFilter, MovieEntity.class);
	}




	@Override
	public MovieEntity update (Long id, MovieEntity movieEntity) {

		MovieDataJPA movie = ObjectMapperUtils.map(movieEntity, MovieDataJPA.class);
		MovieDataJPA movieFindDataJPA = movieRepositoryJPA.findByidMovie(id);

		if (movieFindDataJPA == null) {
			throw new ResourceNotFoundException();
		}

		movieFindDataJPA.setImage(movie.getImage());
		movieFindDataJPA.setTitle(movie.getTitle());
		movieFindDataJPA.setCreationDate(movie.getCreationDate());
		movieFindDataJPA.setRating(movie.getRating());
		movieFindDataJPA.setCharacter(movie.getCharacter());

		movieRepositoryJPA.save(movieFindDataJPA);
		return ObjectMapperUtils.map(movieFindDataJPA, MovieEntity.class);
	}

	@Override
	public String delete (Long id) {
		try {
			movieRepositoryJPA.deleteById(id);
			return "Movie Delete Success";
		} catch (Exception e) {
			return "Movie cannot deleted";
		}

	}






}


