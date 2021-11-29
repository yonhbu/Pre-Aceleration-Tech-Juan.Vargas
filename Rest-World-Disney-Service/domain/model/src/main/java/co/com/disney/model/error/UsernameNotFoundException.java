package co.com.disney.model.error;

public class UsernameNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UsernameNotFoundException () {

		super(" Not found for Username");

	}

}