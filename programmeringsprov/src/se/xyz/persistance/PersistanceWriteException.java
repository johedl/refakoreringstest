package se.xyz.persistance;

public class PersistanceWriteException extends Exception {

	public PersistanceWriteException(String message) {
		super(message);
	}

	public PersistanceWriteException(Throwable cause) {
		super(cause);
	}

	public PersistanceWriteException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistanceWriteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
