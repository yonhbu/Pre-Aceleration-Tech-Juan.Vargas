package co.com.geographic.icons.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException () {

		super(" Was not found for parameter");

	}

}