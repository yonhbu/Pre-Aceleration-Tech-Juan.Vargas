package co.com.disney.model.commons;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException (Class<?> entityClass, Object id) {

		super(String.format("%s was not found for parameter %s", entityClass.getSimpleName(), id));

	}

}