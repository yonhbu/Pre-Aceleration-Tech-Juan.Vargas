package co.com.disney.jpa.characterjpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import co.com.disney.jpa.moviejpa.MovieDataJPA;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CHARACTERDATA")
public class CharacterDataJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_character")
	@Id	
	private Long idCharacter;
	
	private String image;
	
	private String name;
	
	private Integer age;
	
	private Integer weight;
	
	private String history;


	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "FK_rel_character_movie",
	joinColumns = {
			@JoinColumn(name = "id_character", nullable = false)},
	inverseJoinColumns = {
			@JoinColumn(name = "id_movie", nullable = false)})
	private List<MovieDataJPA> movieID;


}


