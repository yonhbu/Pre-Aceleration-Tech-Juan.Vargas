package co.com.disney.model.dto.response;



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

