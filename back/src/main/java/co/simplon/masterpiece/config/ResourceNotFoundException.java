package co.simplon.masterpiece.config;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		//
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
