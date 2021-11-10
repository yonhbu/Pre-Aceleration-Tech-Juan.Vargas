package co.com.disney.jpa.movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.com.disney.jpa.character.CharacterDataJPA;
import co.com.disney.jpa.gender.GenreDataJPA;
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
@SQLDelete(sql = "update movie SET deleted = true where id_movie = ?")
@Where(clause = "deleted = false")
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
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn (name = "id_Genre", insertable = false, updatable = false)
	private GenreDataJPA genre;
	
	@Column (name = "id_Genre", nullable = false)
	private Long idGenre;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "FK_rel_character_movie",
	joinColumns = {
			@JoinColumn(name = "id_movie", nullable = false)},
	inverseJoinColumns = {
			@JoinColumn(name = "id_character", nullable = false)})
    private List<CharacterDataJPA> character;
	
	@Builder.Default
    private boolean deleted = Boolean.FALSE;
    
}
    