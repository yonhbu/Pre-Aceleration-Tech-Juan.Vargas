package co.com.geographic.icons.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import co.com.geographic.icons.model.CountryEntity;


public interface ICountryService {
	
	public List<CountryEntity> getAllCountrys ();
	
	public CountryEntity save (CountryEntity countryEntity);
	
	public void delete (Long id);
	
	public CountryEntity update (Long id, CountryEntity countryEntity);

	public Optional<CountryEntity> findCountryforID(Long countryId);

	public List<CountryEntity> getCountryByFilters(String name, String numberHabitants, Set<Long> continent, String order);
		

}	
