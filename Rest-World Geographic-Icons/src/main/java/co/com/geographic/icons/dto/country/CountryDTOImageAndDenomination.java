package co.com.geographic.icons.dto.country;


import lombok.Data;

@Data
public class CountryDTOImageAndDenomination {
	
	private Long idCountry;
	private String image;
    private String denomination;
	private Long numberInhabitants;

}
