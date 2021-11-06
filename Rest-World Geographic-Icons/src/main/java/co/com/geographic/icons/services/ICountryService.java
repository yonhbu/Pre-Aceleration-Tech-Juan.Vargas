package co.com.geographic.icons.services;

import java.util.List;
import java.util.Set;

import co.com.geographic.icons.dto.country.CountryDTOImageAndDenomination;
import co.com.geographic.icons.dto.country.CountryRqDTO;
import co.com.geographic.icons.dto.country.CountryRsDTO;


public interface ICountryService {
	
	public CountryRsDTO save (CountryRqDTO countryRqDTO);
	
	public List<CountryDTOImageAndDenomination> getListCountryImageAndDenomination ();
	
	public List<CountryRsDTO> getAllCountrys ();
	
	public CountryRsDTO findCountryforID(Long countryId);
	
	public List<CountryRsDTO> getCountryByFilters(String name, Set<Long> continent, String order);
	
	public String delete (Long id);
	
	public CountryRsDTO update (Long id, CountryRqDTO countryRqDTO);

	
		

}	
