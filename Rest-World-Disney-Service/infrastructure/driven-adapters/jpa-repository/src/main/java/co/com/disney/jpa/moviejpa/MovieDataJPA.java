package co.com.disney.jpa.moviejpa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.com.disney.jpa.characterjpa.CharacterDataJPA;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="MOVIE")
public class MovieDataJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_movie")
	@Id	
	private Long idMovie;
	
	private String title;
	
	private String image;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM")
	private LocalDate creationDate;	
	
	private Integer rating;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "FK_rel_character_movie",
	joinColumns = {
			@JoinColumn(name = "id_movie", nullable = false)},
	inverseJoinColumns = {
			@JoinColumn(name = "id_character", nullable = false)})
    private List<CharacterDataJPA> character;
    
}
    