package exceptions;

public class NotNullException extends Exception {
	public NotNullException (String varName) {
		super(varName+" не может быть null");
	}
}
