package co.com.event.usecase.disney;


import java.util.List;
import co.com.disney.model.GenderEntity;
import co.com.disney.model.dto.request.GenderRqDTO;
import co.com.disney.model.dto.response.GenderRsDTO;
import co.com.disney.model.gateways.GenderGateway;
import co.com.event.usecase.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenderUseCase {

	private final GenderGateway genderGateway;


	public GenderRsDTO saveGender(GenderRqDTO genderRqDTO) {
		GenderEntity genderEntity = ObjectMapperUtils.map(genderRqDTO, GenderEntity.class);
		GenderEntity gender = genderGateway.save(genderEntity);
		return ObjectMapperUtils.map(gender, GenderRsDTO.class);
	}



	public List<GenderRsDTO> getAllGenders() {
		List<GenderEntity> listGender = genderGateway.findAllGender();
		return  ObjectMapperUtils.mapAll(listGender, GenderRsDTO.class);
	}




}