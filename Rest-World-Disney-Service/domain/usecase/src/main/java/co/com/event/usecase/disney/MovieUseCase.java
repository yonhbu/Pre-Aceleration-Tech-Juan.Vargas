package co.com.event.usecase.disney;


import java.util.List;

import co.com.disney.model.MovieEntity;
import co.com.disney.model.dto.request.MovieRqDTO;
import co.com.disney.model.dto.response.MovieRsDTO;
import co.com.disney.model.dto.response.MoviesDTOTitleAndImage;
import co.com.disney.model.exception.ResourceNotFoundException;
import co.com.disney.model.gateways.MovieGateway;
import co.com.event.usecase.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovieUseCase {

	private final MovieGateway movieGateway;


	public MovieRsDTO saveMovie(MovieRqDTO movieRqDTO) {
		MovieEntity movieEntity = ObjectMapperUtils.map(movieRqDTO, MovieEntity.class);
		MovieEntity movie = movieGateway.save(movieEntity);
		return ObjectMapperUtils.map(movie, MovieRsDTO.class);
	}


	public List<MoviesDTOTitleAndImage> getListMovieTitleAndImage() {
		List<MovieEntity> listMovies = movieGateway.findAllMovies();
		return  ObjectMapperUtils.mapAll(listMovies, MoviesDTOTitleAndImage.class);
	}


	public List<MovieRsDTO> getAllMovies() {
		List<MovieEntity> listMovies = movieGateway.findAllMovies();
		return  ObjectMapperUtils.mapAll(listMovies, MovieRsDTO.class);
	}


	public MovieRsDTO findMovie(Long movieId) {
		MovieEntity movie = movieGateway.findMovie(movieId);
		return ObjectMapperUtils.map(movie, MovieRsDTO.class);
	}


	public MovieRsDTO update (Long movieId, MovieRqDTO movieRqDTO) {
		MovieEntity movieEntity = ObjectMapperUtils.map(movieRqDTO, MovieEntity.class);
		MovieEntity movieFind = movieGateway.findMovie(movieId);

		if (movieFind == null) {
			throw new ResourceNotFoundException();
		}

		movieFind.setImage(movieEntity.getImage());
		movieFind.setTitle(movieEntity.getTitle());
		movieFind.setCreationDate(movieEntity.getCreationDate());
		movieFind.setRating(movieEntity.getRating());
		movieFind.setCharacter(movieEntity.getCharacter());

		movieGateway.save(movieEntity);
		return ObjectMapperUtils.map(movieFind, MovieRsDTO.class);


	}


	public String deleteMovie(Long movieId) {
		try {
			movieGateway.delete(movieId);
			return "Movie Delete Success";
		} catch (Exception e) {
			return "Movie cannot deleted";
		}	

	}






}