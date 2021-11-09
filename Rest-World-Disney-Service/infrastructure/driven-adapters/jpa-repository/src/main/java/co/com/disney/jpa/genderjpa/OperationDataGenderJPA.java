package co.com.disney.jpa.genderjpa;


import java.util.List;
import org.springframework.stereotype.Component;
import co.com.disney.jpa.util.ObjectMapperUtils;
import co.com.disney.model.GenderEntity;
import co.com.disney.model.gateways.GenderGateway;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OperationDataGenderJPA implements GenderGateway {

	private final GenderRepositoryJPA genderRepositoryJPA;


	@Override
	public GenderEntity save (GenderEntity genderEntity) {
		GenderDataJPA genderRequest =  ObjectMapperUtils.map(genderEntity, GenderDataJPA.class);
		GenderDataJPA genderDataJPA = genderRepositoryJPA.save(genderRequest);
		return ObjectMapperUtils.map(genderDataJPA, GenderEntity.class);
	}


	@Override
	public List<GenderEntity> findAllGender () {
		List<GenderDataJPA> listGenderDataJPA = (List<GenderDataJPA>) genderRepositoryJPA.findAll();	
		return ObjectMapperUtils.mapAll(listGenderDataJPA, GenderEntity.class);
	}



}


