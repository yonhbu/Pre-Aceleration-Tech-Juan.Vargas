package co.com.disney.model.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GenderRsDTO {	
	
	private Long idGender;
	private String name;
	private String image;   
  

}
