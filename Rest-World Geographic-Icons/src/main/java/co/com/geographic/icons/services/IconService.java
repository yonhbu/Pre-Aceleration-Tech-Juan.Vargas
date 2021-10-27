package co.com.geographic.icons.services;

import java.util.List;

import co.com.geographic.icons.model.IconEntity;


public interface IconService {
	
	public List<IconEntity> getAllIcons ();
	
	public IconEntity save (IconEntity iconEntity);
	
	public void delete (Long id);
	
	public IconEntity update (Long id, IconEntity iconEntity);
		

}	
