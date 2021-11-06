package co.com.geographic.icons.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.geographic.icons.dto.country.CountryDTOImageAndDenomination;
import co.com.geographic.icons.dto.country.CountryFiltersDTO;
import co.com.geographic.icons.dto.country.CountryRqDTO;
import co.com.geographic.icons.dto.country.CountryRsDTO;
import co.com.geographic.icons.exception.ResourceNotFoundException;
import co.com.geographic.icons.model.CountryEntity;
import co.com.geographic.icons.repository.CountryRepository;
import co.com.geographic.icons.repository.specifications.CountrySpecification;
import co.com.geographic.icons.services.ICountryService;
import co.com.geographic.icons.util.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class CountryServiceImpl implements ICountryService {


	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CountrySpecification countrySpecification;


	@Override
	public CountryRsDTO save (CountryRqDTO countryRqDTO) {

		CountryEntity countryEntity = ObjectMapperUtils.map(countryRqDTO, CountryEntity.class);
		CountryEntity country = countryRepository.save(countryEntity);
		CountryRsDTO countryResponse = ObjectMapperUtils.map(country, CountryRsDTO.class);
		log.info("Request received for Country insert", country.toString());

		return countryResponse;
	}


	@Override
	public List<CountryDTOImageAndDenomination> getListCountryImageAndDenomination() {
		List<CountryEntity> listCountry = (List<CountryEntity>) countryRepository.findAll();
		return ObjectMapperUtils.mapAll(listCountry, CountryDTOImageAndDenomination.class);

	}



	@Override
	public List<CountryRsDTO> getAllCountrys () {
		List<CountryEntity> listCountry = (List<CountryEntity>) countryRepository.findAll();
		return ObjectMapperUtils.mapAll(listCountry, CountryRsDTO.class);
	}



	@Override
	public CountryRsDTO findCountryforID(Long countryId) {
		Optional<CountryEntity> country = countryRepository.findById(countryId);
		if (!country.isPresent()) {
			throw new ResourceNotFoundException ();
		}
		return  ObjectMapperUtils.map(country.get(), CountryRsDTO.class);
	}



	@Override
	public List<CountryRsDTO> getCountryByFilters(String name, Set<Long> continent, String order) {
		CountryFiltersDTO countryFiltersDTO = new CountryFiltersDTO (name,continent,order);	
		List<CountryEntity> listCountryResponseFilter = countryRepository.findAll(countrySpecification.getByFilter(countryFiltersDTO));	
		return ObjectMapperUtils.mapAll(listCountryResponseFilter, CountryRsDTO.class);
	}




	@Override
	public CountryRsDTO update (Long id, CountryRqDTO countryRqDTO) {

		CountryEntity countryEntity = ObjectMapperUtils.map(countryRqDTO, CountryEntity.class);
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

		log.info("updateCountry() - start: id = {}, country = {}", id, countryRqDTO);
		countryRepository.save(countryFind);

		return ObjectMapperUtils.map(countryFind, CountryRsDTO.class);



	}



	@Override
	public String delete (Long id) {

		try {
			countryRepository.deleteById(id);
			log.info("Request received for Country deletion with id= " + id);
			return "Country Delete Success";
		} catch (Exception e) {
			return "Country cannot deleted";
		}

	}







}