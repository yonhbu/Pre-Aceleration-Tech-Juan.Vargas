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
public class GenderEntity {	
	
	private Long idGender;
	private String name;
	private String image;   
	private List<MovieEntity> associatedMovie;
    
    
    

}
