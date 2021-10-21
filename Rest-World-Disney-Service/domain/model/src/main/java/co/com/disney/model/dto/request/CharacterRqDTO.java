package co.com.disney.model.dto.request;

import java.util.List;

import co.com.disney.model.MovieEntity;
import lombok.Data;

@Data
public class CharacterRqDTO {
	
	private String image;
	private String name;
	private Integer age;
	private Integer weight;
	private String history;
	private List<MovieEntity> movieID;

}
