package co.com.disney.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GenreRqDTO {

	private String title;
	private String image; 
	
}
