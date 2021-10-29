package co.com.geographic.icons.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CONTINENT")
public class ContinentEntity {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_Continent")
	@Id
	private Long idContinent;
	private String image;
    private String denomination;
   

}
