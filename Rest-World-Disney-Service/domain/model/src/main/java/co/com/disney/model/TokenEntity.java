package co.com.disney.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class TokenEntity {

	private String jwt;
	private String message;


}

