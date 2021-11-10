package co.com.disney.jpa.gender;


import java.util.List;
import org.springframework.stereotype.Component;
import co.com.disney.jpa.util.ObjectMapperUtils;
import co.com.disney.model.GenreEntity;
import co.com.disney.model.gateways.GenreGateway;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OperationDataGenreJPA implements GenreGateway {

	private final GenreRepositoryJPA genreRepositoryJPA;


	@Override
	public GenreEntity save (GenreEntity genreEntity) {
		GenreDataJPA genreRequest =  ObjectMapperUtils.map(genreEntity, GenreDataJPA.class);
		GenreDataJPA genreDataJPA = genreRepositoryJPA.save(genreRequest);
		return ObjectMapperUtils.map(genreDataJPA, GenreEntity.class);
	}


	@Override
	public List<GenreEntity> findAllGenre () {
		List<GenreDataJPA> listGenreDataJPA = (List<GenreDataJPA>) genreRepositoryJPA.findAll();	
		return ObjectMapperUtils.mapAll(listGenreDataJPA, GenreEntity.class);
	}



}


