package co.com.disney.jpa.genderjpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import co.com.disney.model.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="GENDER")
public class GenderDataJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_gender")
	@Id	
	private Long idGender;
	
	private String name;
	
	private String image;  
	  
	//private List<MovieEntity> associatedMovie;

}
    