package co.com.geographic.icons.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.geographic.icons.exception.ResourceNotFoundException;
import co.com.geographic.icons.model.CountryEntity;
import co.com.geographic.icons.model.IconEntity;
import co.com.geographic.icons.repository.CountryRepository;
import co.com.geographic.icons.repository.IconRepository;
import co.com.geographic.icons.services.ICountryService;
import co.com.geographic.icons.services.IIconService;


@Service
public class CountryServiceImpl implements ICountryService {


	@Autowired
	private CountryRepository countryRepository;


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
			throw new ResourceNotFoundException(CountryEntity.class, id);
		}

		CountryEntity.builder()
				.image(countryEntity.getImage())
				.denomination(countryEntity.getDenomination())
				.numberInhabitants(countryEntity.getNumberInhabitants())
				.surface(countryEntity.getSurface())
			    .icons(countryEntity.getIcons())
		    .build();


		countryRepository.save(countryFind);
		return countryFind;

	}



	@Override
	public void delete (Long id) {
		countryRepository.deleteById(id);
	}



}