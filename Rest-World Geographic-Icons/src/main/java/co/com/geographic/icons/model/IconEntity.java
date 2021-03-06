package co.com.geographic.icons.model;

import java.time.LocalDate;
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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ICON")
@SQLDelete(sql = "update icon SET deleted = true where id_icon = ?")
@Where(clause = "deleted = false")
public class IconEntity  {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_icon")
	@Id	
	private Long idIcon;
	
	@NotNull(message = "image cannot be null")
	private String image;
	
	@NotNull(message = "denomination cannot be null")
    private String denomination;

    @Column (name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-dd-MM")
    private LocalDate creationDate;
    
    private Long altitude;
    
    private String history;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "icons", cascade = CascadeType.ALL)
    private List<CountryEntity> listCountry;
    
    @Builder.Default
    private boolean deleted = Boolean.FALSE;
    
  
    
}
