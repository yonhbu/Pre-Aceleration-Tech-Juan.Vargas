package co.com.disney.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterRqDTO {
	
	private String image;
	private String name;
	private Integer age;
	private Integer weight;
	private String history;

}
