package co.com.geographic.icons.services.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.geographic.icons.dto.continent.ContinentRqDTO;
import co.com.geographic.icons.dto.continent.ContinentRsDTO;
import co.com.geographic.icons.model.ContinentEntity;
import co.com.geographic.icons.repository.ContinentRepository;
import co.com.geographic.icons.services.IContinentService;
import co.com.geographic.icons.util.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ContinentServiceImpl implements IContinentService {


	@Autowired
	private ContinentRepository continentRepository;


	@Override
	public ContinentRsDTO save (ContinentRqDTO continentRqDTO) {

		// convert DTO to entity
		ContinentEntity continentEntity = ObjectMapperUtils.map(continentRqDTO, ContinentEntity.class);
		ContinentEntity continent = continentRepository.save(continentEntity);

		// convert entity to DTO
		ContinentRsDTO continentResponse = ObjectMapperUtils.map(continent, ContinentRsDTO.class);
		log.info("Request received for Continent insert", continent.toString());

		return continentResponse;
	}


	@Override
	public List<ContinentRsDTO> getAllContinents() {

		List<ContinentEntity> listContinent =  (List<ContinentEntity>) continentRepository.findAll();
		// convert entity to DTO
		return ObjectMapperUtils.mapAll(listContinent, ContinentRsDTO.class);
	}


}