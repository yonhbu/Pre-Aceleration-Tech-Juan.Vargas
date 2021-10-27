package co.com.geographic.icons.services.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.geographic.icons.model.ContinentEntity;
import co.com.geographic.icons.repository.ContinentRepository;
import co.com.geographic.icons.services.IContinentService;


@Service
public class ContinentServiceImpl implements IContinentService {


	@Autowired
	private ContinentRepository continentRepository;


	@Override
	public ContinentEntity save (ContinentEntity continentEntity) {
		return continentRepository.save(continentEntity);
	}


}