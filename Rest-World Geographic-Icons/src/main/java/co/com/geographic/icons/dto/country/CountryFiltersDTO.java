package co.com.geographic.icons.dto.country;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountryFiltersDTO {
	
    private String name;
    private Set<Long> listIdContinent;
    private String order;
    
    
   
    public boolean isASC () {
    	return this.order.compareToIgnoreCase("ASC") == 0;
    }
    
    
    public boolean isDESC () {
    	return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
