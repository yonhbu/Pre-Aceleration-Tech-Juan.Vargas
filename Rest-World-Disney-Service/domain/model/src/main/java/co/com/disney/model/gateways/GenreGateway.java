package co.com.disney.model.gateways;

import java.util.List;

import co.com.disney.model.GenreEntity;


public interface GenreGateway {

	public GenreEntity save (GenreEntity genreEntity);
	
	public List<GenreEntity> findAllGenre();

}
