package co.com.disney.jpa.character;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import co.com.disney.jpa.movie.MovieDataJPA;
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
@SQLDelete(sql = "update characterdata SET deleted = true where id_character = ?")
@Where(clause = "deleted = false")
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

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "character", cascade = CascadeType.ALL)
	private List<MovieDataJPA> listMovie;
	
	@Builder.Default
    private boolean deleted = Boolean.FALSE;


}


