package gn.jonas.crudapiexercise.services.exceptions;

public class DatabaseViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatabaseViolationException(String msg) {
		super(msg);
	}

	public DatabaseViolationException() {
		this("Database integrity violated");
	}

}
