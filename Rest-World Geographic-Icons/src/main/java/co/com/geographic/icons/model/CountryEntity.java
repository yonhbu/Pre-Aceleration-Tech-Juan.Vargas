package co.com.geographic.icons.model;


import java.util.Set;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="COUNTRY")
@SQLDelete(sql = "update country SET deleted = true where id_Country = ?")
@Where(clause = "deleted = false")
public class CountryEntity  {


	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_Country")
	@Id
	private Long idCountry;

	@NotNull(message = "image cannot be null")
	private String image;

	@NotNull(message = "denomination cannot be null")
	private String denomination;

	@Column (name = "number_inhabitants")
	private Long numberInhabitants;

	private Long surface; //m2

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn (name = "id_Continent", insertable = false, updatable = false)
	private ContinentEntity continent;

	@Column (name = "id_Continent", nullable = false)
	private Long idContinent;

	@ManyToMany(fetch = FetchType.LAZY,  cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "FK_rel_icon_country",
	joinColumns = {
			@JoinColumn(name = "id_country", nullable = false)},
	inverseJoinColumns = {
			@JoinColumn(name = "id_icon", nullable = false)})
	private Set<IconEntity> icons;
	
	@Builder.Default
    private boolean deleted = Boolean.FALSE;




}
