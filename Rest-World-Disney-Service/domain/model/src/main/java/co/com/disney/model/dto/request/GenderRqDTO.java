package co.com.disney.model.dto.request;


import java.util.List;

import co.com.disney.model.MovieEntity;
import lombok.Data;


@Data
public class GenderRqDTO {

	private String name;
	private String image;   
	private List<MovieEntity> associatedMovie;
	
}
