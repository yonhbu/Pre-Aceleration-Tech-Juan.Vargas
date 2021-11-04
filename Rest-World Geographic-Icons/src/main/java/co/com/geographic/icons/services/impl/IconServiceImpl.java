package co.com.geographic.icons.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.geographic.icons.dto.icon.IconsFiltersDTO;
import co.com.geographic.icons.exception.ResourceNotFoundException;
import co.com.geographic.icons.model.IconEntity;
import co.com.geographic.icons.repository.IconRepository;
import co.com.geographic.icons.repository.specifications.IconSpecification;
import co.com.geographic.icons.services.IIconService;


@Service
public class IconServiceImpl implements IIconService{


	@Autowired
	private IconRepository iconRepository;
	
	@Autowired
	private IconSpecification iconSpecification;


	@Override
	public IconEntity save (IconEntity iconEntity) {
		return iconRepository.save(iconEntity);
	}



	@Override
	public List<IconEntity> getAllIcons () {
		return (List<IconEntity>) iconRepository.findAll();
	}


	@Override
	public IconEntity update (Long id, IconEntity iconEntity) {

		IconEntity iconFind = iconRepository.findIconByidIcon(id);

		if (iconFind == null) {
			throw new ResourceNotFoundException(IconEntity.class, id);
		}

		iconFind.setImage(iconEntity.getImage());
		iconFind.setHistory(iconEntity.getHistory());
		iconFind.setDenomination(iconEntity.getDenomination());
		iconFind.setCreationDate(iconEntity.getCreationDate());
		iconFind.setAltitude(iconEntity.getAltitude());
		iconFind.setListCountry(iconEntity.getListCountry());

		iconRepository.save(iconFind);
		return iconFind;

	}



	@Override
	public void delete (Long id) {
		iconRepository.deleteById(id);
	}



	@Override
	public IconEntity findIcon(Long id) {
		return iconRepository.findIconByidIcon(id);
	}



	@Override
	public List<IconEntity> getIconByFilters(String name, String date, Long altitude, Set<Long> countrys, String order) {
		IconsFiltersDTO iconsFiltersDTO = new IconsFiltersDTO (name,date,altitude,countrys,order);	
		return iconRepository.findAll(iconSpecification.getByFilter(iconsFiltersDTO));
	}


}