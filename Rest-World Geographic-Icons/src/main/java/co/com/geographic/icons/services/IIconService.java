package co.com.geographic.icons.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import co.com.geographic.icons.model.IconEntity;


public interface IIconService {
	
	public IconEntity save (IconEntity iconEntity);
	
	public List<IconEntity> getAllIcons ();
	
	public Optional<IconEntity> findIcon (Long id);
	
	public List<IconEntity> getIconByFilters (String name, String date, Long altitude, Set<Long> countrys, String order);
	
	public void delete (Long id);
	
	public IconEntity update (Long id, IconEntity iconEntity);
	

		

}	
