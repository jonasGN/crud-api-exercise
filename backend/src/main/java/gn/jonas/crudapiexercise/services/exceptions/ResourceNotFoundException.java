package gn.jonas.crudapiexercise.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String defaultMessage = "Resource not found";

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

	public ResourceNotFoundException() {
		this(defaultMessage);
	}

	public ResourceNotFoundException(Long resourceId) {
		this("Could not find this resource by given ID: " + resourceId);
	}

}
