package co.com.geographic.icons.repository;



import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.geographic.icons.model.CountryEntity;


@Repository
public interface CountryRepository extends CrudRepository<CountryEntity,Long>, JpaSpecificationExecutor<CountryEntity> {
	
	List<CountryEntity> findAll (Specification<CountryEntity> spec);
	
	CountryEntity findCountryByidCountry (Long id);
		

}
