package co.com.geographic.icons.dto.continent;

import java.util.List;

import co.com.geographic.icons.model.CountryEntity;
import lombok.Data;

@Data
public class ContinentRsDTO {
	

	private Long idContinent;
	private String image;
    private String denomination;
    private List<CountryEntity> listCountry;

}
