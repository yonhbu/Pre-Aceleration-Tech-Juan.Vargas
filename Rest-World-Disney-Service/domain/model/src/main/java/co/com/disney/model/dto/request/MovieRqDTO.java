package co.com.disney.model.dto.request;

import java.time.LocalDate;


import lombok.Data;

@Data
public class MovieRqDTO  {
	
	private String title;
	private LocalDate creationDate;	
	private Integer rating;

}
