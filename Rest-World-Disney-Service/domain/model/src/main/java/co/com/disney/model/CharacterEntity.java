package co.com.disney.model;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEntity    {
	

	private Long idCharacter;
	private String image;
	private String name;
	private Integer age;
	private Integer weight;
	private String history;
	private List<MovieEntity> listMovie;


}
