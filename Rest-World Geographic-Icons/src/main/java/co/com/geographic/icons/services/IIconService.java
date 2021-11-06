package co.com.geographic.icons.services;

import java.util.List;
import java.util.Set;

import co.com.geographic.icons.dto.icon.IconDTOImageAndDenomination;
import co.com.geographic.icons.dto.icon.IconRqDTO;
import co.com.geographic.icons.dto.icon.IconRsDTO;



public interface IIconService {
	
	public IconRsDTO save (IconRqDTO iconRqDTO);
	
	public List<IconDTOImageAndDenomination> getAllIconsSomeFields();
	
	public List<IconRsDTO> getAllIcons ();
	
	public IconRsDTO findIcon (Long id);
	
	public List<IconRsDTO> getIconByFilters (String name, String date, Long altitude, Set<Long> countrys, String order);
	
	public String delete (Long id);
	
	public IconRsDTO update (Long id, IconRqDTO iconRqDTO);


	

		

}	
