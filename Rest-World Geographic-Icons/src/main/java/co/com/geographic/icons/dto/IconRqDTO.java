package co.com.geographic.icons.dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class IconRqDTO  {
	

	private String image;
    private String denomination;
    private LocalDate creationDate;
    private Long altitude;
    private String history;
   
    
}
