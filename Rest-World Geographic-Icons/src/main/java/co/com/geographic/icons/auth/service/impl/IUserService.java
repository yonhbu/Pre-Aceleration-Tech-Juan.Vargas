package co.com.geographic.icons.auth.service.impl;

import co.com.geographic.icons.auth.dto.UserRqDTO;
import co.com.geographic.icons.auth.dto.UserRsDTO;

public interface IUserService {
	
	UserRsDTO saveUser(UserRqDTO userDTO);

}
