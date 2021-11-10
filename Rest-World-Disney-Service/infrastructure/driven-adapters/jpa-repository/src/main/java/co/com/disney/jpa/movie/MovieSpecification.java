package co.com.disney.jpa.movie;


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

import co.com.disney.jpa.gender.GenreDataJPA;
import co.com.disney.model.dto.request.MoviesFiltersDTO;


@Component
public class MovieSpecification {

	public Specification<MovieDataJPA> getByFilter(MoviesFiltersDTO moviesFiltersDTO) {
		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<> ();

			if (StringUtils.hasLength(moviesFiltersDTO.getTitle())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("title")),
								"%" + moviesFiltersDTO.getTitle().toLowerCase() + "%"

								)
						);
			}


			if (!CollectionUtils.isEmpty(moviesFiltersDTO.getGenre())) {
				Join<GenreDataJPA, MovieDataJPA> join = root.join("genre", JoinType.INNER);
				Expression<String> countrysId = join.get("idGenre");
				predicates.add(countrysId.in(moviesFiltersDTO.getGenre()));
			}

			//Remove Duplicates
			query.distinct(true);


			//Order

			String orderByField = "title";
			query.orderBy(

					moviesFiltersDTO.isASC()?
							criteriaBuilder.asc(root.get(orderByField)) :
								criteriaBuilder.desc(root.get(orderByField))
					);


			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


		};
	}

}
