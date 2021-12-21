package co.com.disney.model.dto.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRqDTO  {
	
	private String image;
	private String title;
	private LocalDate creationDate;	
	private Long idGenre;
	private Integer rating;
    private List<CharacterRqDTO> character;
	
    

}
