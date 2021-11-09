package co.com.disney.model.dto.response;

import java.util.List;

import co.com.disney.model.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CharacterRsDTO {
	
	private Long idCharacter;
	private String image;
	private String name;
	private Integer age;
	private Integer weight;
	private String history;
	//private List<MovieEntity> listMovie;

}
