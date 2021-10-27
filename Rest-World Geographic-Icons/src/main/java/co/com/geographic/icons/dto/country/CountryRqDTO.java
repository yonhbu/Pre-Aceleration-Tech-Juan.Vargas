package co.com.geographic.icons.dto.country;


import java.util.Set;


import co.com.geographic.icons.model.ContinentEntity;
import co.com.geographic.icons.model.IconEntity;
import lombok.Data;


@Data
public class CountryRqDTO  {
	
	private String image;
	private String denomination;
	private Long numberInhabitants;
	private Long surface;
	private ContinentEntity continent;
	private Long idContinent;
	private Set<IconEntity> icons;
    
}
