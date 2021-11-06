package co.com.geographic.icons.auth.service;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.geographic.icons.auth.dto.UserRqDTO;
import co.com.geographic.icons.auth.dto.UserRsDTO;
import co.com.geographic.icons.auth.repository.UserRepository;
import co.com.geographic.icons.auth.service.impl.IUserService;
import co.com.geographic.icons.model.UserEntity;
import co.com.geographic.icons.util.ObjectMapperUtils;


@Service
public class UserDetailService implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUsername(username);
		// convert entity to DTO
		UserRsDTO userRsDTO = ObjectMapperUtils.map(userEntity, UserRsDTO.class);

		if (userRsDTO != null) {
			return new User (userRsDTO.getUsername(), userRsDTO.getPassword(), Collections.emptyList());

		} else {
			throw new UsernameNotFoundException ("User " + username + "Not Found");
		}
	}


	@Override
	public UserRsDTO saveUser(UserRqDTO userDTO) {
		// convert DTO to entity
		UserEntity userSaveEntity = ObjectMapperUtils.map(userDTO, UserEntity.class);
		UserEntity userRsSave = userRepository.save(userSaveEntity);

		// convert entity to DTO
		return ObjectMapperUtils.map(userRsSave, UserRsDTO.class);
	}

}
