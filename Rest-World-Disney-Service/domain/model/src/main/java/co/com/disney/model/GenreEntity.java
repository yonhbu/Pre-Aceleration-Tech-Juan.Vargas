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
public class GenreEntity {	
	
	private Long idGenre;
	private String title;
	private String image;   
	private List<MovieEntity> movie;
  

}
