package co.com.geographic.icons.services;

import java.util.List;

import co.com.geographic.icons.dto.continent.ContinentRqDTO;
import co.com.geographic.icons.dto.continent.ContinentRsDTO;



public interface IContinentService {
	
	public ContinentRsDTO save (ContinentRqDTO continentRqDTO);
	
	public List<ContinentRsDTO> getAllContinents();
		

}	
