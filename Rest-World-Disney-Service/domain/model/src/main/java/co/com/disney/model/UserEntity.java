package co.com.disney.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
	
	private Long idUser;
	
	private String username;

	private String password;


}
