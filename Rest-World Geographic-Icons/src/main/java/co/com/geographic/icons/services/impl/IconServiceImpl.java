package co.com.geographic.icons.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.geographic.icons.dto.icon.IconDTOImageAndDenomination;
import co.com.geographic.icons.dto.icon.IconRqDTO;
import co.com.geographic.icons.dto.icon.IconRsDTO;
import co.com.geographic.icons.dto.icon.IconsFiltersDTO;
import co.com.geographic.icons.exception.ResourceNotFoundException;
import co.com.geographic.icons.model.IconEntity;
import co.com.geographic.icons.repository.IconRepository;
import co.com.geographic.icons.repository.specifications.IconSpecification;
import co.com.geographic.icons.services.IIconService;
import co.com.geographic.icons.util.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class IconServiceImpl implements IIconService{


	@Autowired
	private IconRepository iconRepository;

	@Autowired
	private IconSpecification iconSpecification;


	@Override
	public IconRsDTO save (IconRqDTO iconRqDTO) {
		IconEntity iconEntity = ObjectMapperUtils.map(iconRqDTO, IconEntity.class);
		IconEntity icon = iconRepository.save(iconEntity);

		return ObjectMapperUtils.map(icon, IconRsDTO.class);
	}



	@Override
	public List<IconDTOImageAndDenomination> getAllIconsSomeFields() {
		List<IconEntity> listIcon = (List<IconEntity>) iconRepository.findAll();
		return ObjectMapperUtils.mapAll(listIcon, IconDTOImageAndDenomination.class);

	}


	@Override
	public List<IconRsDTO> getAllIcons () {

		List<IconEntity> listIcon = (List<IconEntity>) iconRepository.findAll();
		return  ObjectMapperUtils.mapAll(listIcon, IconRsDTO.class);
	}


	@Override
	public IconRsDTO findIcon(Long id) {

		Optional<IconEntity> icon = iconRepository.findById(id);
		if (!icon.isPresent()) {
			throw new ResourceNotFoundException ();
		}
		return ObjectMapperUtils.map(icon.get(), IconRsDTO.class);
	}


	@Override
	public List<IconRsDTO> getIconByFilters(String name, String date, Long altitude, Set<Long> countrys, String order) {
		IconsFiltersDTO iconsFiltersDTO = new IconsFiltersDTO (name,date,altitude,countrys,order);	

		List<IconEntity> listIconResponseFilter = iconRepository.findAll(iconSpecification.getByFilter(iconsFiltersDTO));
		return ObjectMapperUtils.mapAll(listIconResponseFilter, IconRsDTO.class);

	}



	@Override
	public IconRsDTO update (Long id, IconRqDTO iconRqDTO) {

		IconEntity iconEntity = ObjectMapperUtils.map(iconRqDTO, IconEntity.class);
		IconEntity iconFind = iconRepository.findIconByidIcon(id);

		if (iconFind == null) {
			throw new ResourceNotFoundException();
		}

		iconFind.setImage(iconEntity.getImage());
		iconFind.setHistory(iconEntity.getHistory());
		iconFind.setDenomination(iconEntity.getDenomination());
		iconFind.setCreationDate(iconEntity.getCreationDate());
		iconFind.setAltitude(iconEntity.getAltitude());
		iconFind.setListCountry(iconEntity.getListCountry());

		log.info("updateIcon() - start: id = {}, icon = {}", id, iconRqDTO);
		iconRepository.save(iconFind);

		return ObjectMapperUtils.map(iconFind, IconRsDTO.class);

	}



	@Override
	public String delete (Long id) {
		try {
			iconRepository.deleteById(id);
			log.info("Request received for Icon deletion with id= " + id);
			return "Icon Delete Success";
		} catch (Exception e) {
			return "Icon cannot deleted";
		}

	}




}