package co.com.geographic.icons.dto.country;

import java.util.Set;

import co.com.geographic.icons.dto.icon.IconRqDTO;
import lombok.Data;

@Data
public class CountryRsDTO {
	

	private Long idCountry;
	private String image;
	private String denomination;
	private Long numberInhabitants;
	private Long surface;
	private Set<IconRqDTO> icons;

}
