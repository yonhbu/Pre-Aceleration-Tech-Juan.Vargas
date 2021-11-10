package co.com.disney.model.dto.request;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MoviesFiltersDTO {
	
    private String title;
    private Set<Long> genre;
    private String order;
    
    
   
    public boolean isASC () {
    	return this.order.compareToIgnoreCase("ASC") == 0;
    }
    
    
    public boolean isDESC () {
    	return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
