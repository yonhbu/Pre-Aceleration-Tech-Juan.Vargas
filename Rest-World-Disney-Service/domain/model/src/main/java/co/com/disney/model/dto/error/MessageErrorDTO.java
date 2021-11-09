package co.com.disney.model.dto.error;

import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageErrorDTO {
	
    private Date timestamp;
	private String status;
	private String message;
	private String detalles;
	private List<String> errors;


}
