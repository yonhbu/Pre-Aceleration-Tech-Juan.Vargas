package co.com.geographic.icons.repository.specifications;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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



import co.com.geographic.icons.dto.icon.IconsFiltersDTO;
import co.com.geographic.icons.model.CountryEntity;
import co.com.geographic.icons.model.IconEntity;

@Component
public class IconSpecification {

	public Specification<IconEntity> getByFilter(IconsFiltersDTO iconsFiltersDTO) {
		return (root, query, criteriaBuilder) -> {
			
		List<Predicate> predicates = new ArrayList<> ();
		
		if (StringUtils.hasLength(iconsFiltersDTO.getName())) {
			predicates.add(
					criteriaBuilder.like(
							criteriaBuilder.lower(root.get("denomination")),
							"%" + iconsFiltersDTO.getName().toLowerCase() + "%"
							
							)
					);
		}
		
		
		if (StringUtils.hasLength(iconsFiltersDTO.getCreationDate())) {
			
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(iconsFiltersDTO.getCreationDate(), format);
			
			predicates.add( 
					criteriaBuilder.equal(root.<LocalDate>get("creationDate"), date));

		}
		
		
		if (!CollectionUtils.isEmpty(iconsFiltersDTO.getListCountry())) {
			Join<CountryEntity, IconEntity> join = root.join("listCountry", JoinType.INNER);
			Expression<String> countrysId = join.get("idCountry");
			predicates.add(countrysId.in(iconsFiltersDTO.getListCountry()));
		}
		
		//Remove Duplicates
		query.distinct(true);
		
		
		//Order
		
		String orderByField = "denomination";
		query.orderBy(
				
				iconsFiltersDTO.isASC()?
						criteriaBuilder.asc(root.get(orderByField)) :
						criteriaBuilder.desc(root.get(orderByField))
				);
		
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
			
		};
	}

}
