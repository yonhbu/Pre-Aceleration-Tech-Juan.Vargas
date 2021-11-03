package co.com.geographic.icons.services;

import java.util.List;

import co.com.geographic.icons.model.CountryEntity;


public interface ICountryService {
	
	public List<CountryEntity> getAllCountrys ();
	
	public CountryEntity save (CountryEntity countryEntity);
	
	public void delete (Long id);
	
	public CountryEntity update (Long id, CountryEntity countryEntity);

	public CountryEntity findCountryforID(Long countryId);
		

}	
