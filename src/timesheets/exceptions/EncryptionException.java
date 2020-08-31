package timesheets.exceptions;

public class EncryptionException extends Exception {

	private static final long serialVersionUID = -7036395524029086903L;

	public EncryptionException() {
	}

	public EncryptionException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
