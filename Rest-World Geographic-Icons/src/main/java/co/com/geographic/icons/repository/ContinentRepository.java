package co.com.geographic.icons.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.geographic.icons.model.ContinentEntity;



@Repository
public interface ContinentRepository extends CrudRepository<ContinentEntity,Long>{
			

}
