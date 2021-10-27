package co.com.geographic.icons.services;

import java.util.List;

import co.com.geographic.icons.model.ContinentEntity;


public interface IContinentService {
	
	public ContinentEntity save (ContinentEntity continentEntity);
	
	public List<ContinentEntity> getAllContinents();
		

}	
