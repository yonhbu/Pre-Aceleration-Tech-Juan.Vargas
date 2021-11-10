package co.com.disney.jpa.character;


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

import co.com.disney.jpa.movie.MovieDataJPA;
import co.com.disney.model.dto.request.CharactersFiltersDTO;


@Component
public class CharacterSpecification {

	public Specification<CharacterDataJPA> getByFilter(CharactersFiltersDTO charactersFiltersDTO) {
		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<> ();

			if (StringUtils.hasLength(charactersFiltersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("name")),
								"%" + charactersFiltersDTO.getName().toLowerCase() + "%"

								)
						);
			}


			if (!CollectionUtils.isEmpty(charactersFiltersDTO.getListMovie())) {
				Join<MovieDataJPA, CharacterDataJPA> join = root.join("listMovie", JoinType.INNER);
				Expression<String> countrysId = join.get("idMovie");
				predicates.add(countrysId.in(charactersFiltersDTO.getListMovie()));
			}

			//Remove Duplicates
			query.distinct(true);


			//Order

			String orderByField = "name";
			query.orderBy(

					charactersFiltersDTO.isASC()?
							criteriaBuilder.asc(root.get(orderByField)) :
								criteriaBuilder.desc(root.get(orderByField))
					);


			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


		};
	}

}
