package co.com.geographic.icons.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.geographic.icons.exception.ResourceNotFoundException;
import co.com.geographic.icons.model.IconEntity;
import co.com.geographic.icons.repository.IconRepository;


@Service
public class IconServiceImp implements IconService{


	@Autowired
	private IconRepository iconRepository;


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


}