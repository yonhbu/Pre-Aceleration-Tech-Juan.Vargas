package co.com.geographic.icons.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.geographic.icons.model.IconEntity;


@Repository
public interface IconRepository extends CrudRepository<IconEntity,Long>{
	
	IconEntity findIconByidIcon (Long id);
		

}
