package co.com.geographic.icons.auth.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.geographic.icons.model.UserEntity;



@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>{
	
	public UserEntity findByUsername (String username);

		

}
