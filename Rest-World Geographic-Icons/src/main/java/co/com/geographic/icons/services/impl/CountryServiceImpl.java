package co.com.geographic.icons.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.geographic.icons.dto.country.CountryFiltersDTO;
import co.com.geographic.icons.exception.ResourceNotFoundException;
import co.com.geographic.icons.model.CountryEntity;
import co.com.geographic.icons.repository.CountryRepository;
import co.com.geographic.icons.repository.specifications.CountrySpecification;
import co.com.geographic.icons.services.ICountryService;



@Service
public class CountryServiceImpl implements ICountryService {


	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CountrySpecification countrySpecification;


	@Override
	public CountryEntity save (CountryEntity countryEntity) {
		return countryRepository.save(countryEntity);
	}



	@Override
	public List<CountryEntity> getAllCountrys () {
		return (List<CountryEntity>) countryRepository.findAll();
	}


	@Override
	public CountryEntity update (Long id, CountryEntity countryEntity) {

		CountryEntity countryFind = countryRepository.findCountryByidCountry(id);

		if (countryFind == null) {
			throw new ResourceNotFoundException();
		}

	 
		 countryFind.setImage(countryEntity.getImage());
		 countryFind.setDenomination(countryEntity.getDenomination());
		 countryFind.setNumberInhabitants(countryEntity.getNumberInhabitants());
		 countryFind.setSurface(countryEntity.getSurface());
		 countryFind.setIdContinent(countryEntity.getIdContinent());
		 countryFind.setIcons(countryEntity.getIcons());

		countryRepository.save(countryFind);
		return countryFind;

	}



	@Override
	public void delete (Long id) {
		countryRepository.deleteById(id);
	}



	@Override
	public Optional<CountryEntity> findCountryforID(Long countryId) {
		return countryRepository.findById(countryId);
	}



	@Override
	public List<CountryEntity> getCountryByFilters(String name, Set<Long> continent, String order) {
		CountryFiltersDTO countryFiltersDTO = new CountryFiltersDTO (name,continent,order);	
		return countryRepository.findAll(countrySpecification.getByFilter(countryFiltersDTO));
	}



}