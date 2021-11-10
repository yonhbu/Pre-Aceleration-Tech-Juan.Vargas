package co.com.event.usecase.disney;


import java.util.List;
import co.com.disney.model.GenreEntity;
import co.com.disney.model.dto.request.GenreRqDTO;
import co.com.disney.model.dto.response.GenreRsDTO;
import co.com.disney.model.gateways.GenreGateway;
import co.com.event.usecase.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenreUseCase {

	private final GenreGateway genreGateway;


	public GenreRsDTO saveGenre(GenreRqDTO genreRqDTO) {
		GenreEntity genreEntity = ObjectMapperUtils.map(genreRqDTO, GenreEntity.class);
		GenreEntity genre = genreGateway.save(genreEntity);
		return ObjectMapperUtils.map(genre, GenreRsDTO.class);
	}



	public List<GenreRsDTO> getAllGenres() {
		List<GenreEntity> listGenre = genreGateway.findAllGenre();
		return  ObjectMapperUtils.mapAll(listGenre, GenreRsDTO.class);
	}




}