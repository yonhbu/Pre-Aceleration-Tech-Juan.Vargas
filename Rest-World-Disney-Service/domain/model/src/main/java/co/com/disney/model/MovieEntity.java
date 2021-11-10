package co.com.disney.model;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
	
	private Long idMovie;
	private String image;
	private String title;
	private LocalDate creationDate;	
	private Integer rating;
	private GenreEntity genre;
	private Long idGenre;
    private Set<CharacterEntity> character;
    
   

}
