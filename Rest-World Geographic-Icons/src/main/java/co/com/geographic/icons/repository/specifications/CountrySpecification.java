package co.com.geographic.icons.repository.specifications;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import co.com.geographic.icons.dto.country.CountryFiltersDTO;
import co.com.geographic.icons.model.ContinentEntity;
import co.com.geographic.icons.model.CountryEntity;

@Component
public class CountrySpecification {

	public Specification<CountryEntity> getByFilter(CountryFiltersDTO countryFiltersDTO) {
		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<> ();

			if (StringUtils.hasLength(countryFiltersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("denomination")),
								"%" + countryFiltersDTO.getName().toLowerCase() + "%"

								)
						);
			}


			
		if (!CollectionUtils.isEmpty(countryFiltersDTO.getListIdContinent())) {
			Join<ContinentEntity, CountryEntity> join = root.join("continent", JoinType.INNER);
			Expression<String> continentId = join.get("idContinent");
			predicates.add(continentId.in(countryFiltersDTO.getListIdContinent()));
		}

		//Remove Duplicates
		query.distinct(true);


		//Order

		String orderByField = "denomination";
		query.orderBy(

				countryFiltersDTO.isASC()?
						criteriaBuilder.asc(root.get(orderByField)) :
							criteriaBuilder.desc(root.get(orderByField))
				);


		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


	};
}

}
