package co.com.geographic.icons.auth.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class TokenResponseDTO {

	private String jwt;
	private String message;


}

