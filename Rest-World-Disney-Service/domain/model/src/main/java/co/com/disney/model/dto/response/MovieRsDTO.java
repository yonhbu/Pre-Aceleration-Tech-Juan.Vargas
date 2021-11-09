package co.com.disney.model.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MovieRsDTO {
	
	private Long idMovie;
	private String image;
	private String title;
	private LocalDate creationDate;	
	private Integer rating;
    //private List<CharacterRsDTO> associatedCharacter;

}
