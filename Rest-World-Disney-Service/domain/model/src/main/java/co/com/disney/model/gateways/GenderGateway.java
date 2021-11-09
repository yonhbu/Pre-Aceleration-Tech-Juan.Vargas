package co.com.disney.model.gateways;

import java.util.List;

import co.com.disney.model.GenderEntity;


public interface GenderGateway {

	public GenderEntity save (GenderEntity genderEntity);
	
	public List<GenderEntity> findAllGender();

}
