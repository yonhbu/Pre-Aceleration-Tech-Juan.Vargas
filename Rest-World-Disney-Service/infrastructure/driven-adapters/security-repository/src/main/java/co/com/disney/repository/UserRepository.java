package co.com.disney.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends CrudRepository<UserDataJPA,Long>{
	
	public UserDataJPA findByUsername (String username);

		

}
