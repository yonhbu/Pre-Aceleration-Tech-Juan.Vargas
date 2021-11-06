package co.com.geographic.icons.exception;

public class UsernameNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UsernameNotFoundException () {

		super(" Not found for Username");

	}

}