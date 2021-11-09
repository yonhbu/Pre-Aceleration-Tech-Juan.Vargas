package co.com.disney.model.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException () {

		super(" Not found for parameter");

	}

}