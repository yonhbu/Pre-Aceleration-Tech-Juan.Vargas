package co.com.disney.model.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GenreRsDTO {	
	
	private Long idGenre;
	private String title;
	private String image;   
  

}
