package co.com.geographic.icons.repository;



import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.geographic.icons.model.IconEntity;


@Repository
public interface IconRepository extends CrudRepository<IconEntity,Long>, JpaSpecificationExecutor<IconEntity>{
	
	IconEntity findIconByidIcon (Long id);
	
	List<IconEntity> findAll (Specification<IconEntity> spec);
		

}
