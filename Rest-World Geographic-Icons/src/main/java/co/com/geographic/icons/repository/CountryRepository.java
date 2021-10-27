package co.com.geographic.icons.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.geographic.icons.model.CountryEntity;


@Repository
public interface CountryRepository extends CrudRepository<CountryEntity,Long>{
	
	CountryEntity findCountryByidCountry (Long id);
		

}
