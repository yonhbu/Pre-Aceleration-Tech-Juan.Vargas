package co.com.disney.model.dto.request;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CharactersFiltersDTO {
	
    private String name;
    private Integer age;
    private Set<Long> listMovie;
    private String order;
    
    
   
    public boolean isASC () {
    	return this.order.compareToIgnoreCase("ASC") == 0;
    }
    
    
    public boolean isDESC () {
    	return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
