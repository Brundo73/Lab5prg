package exceptions;

public class MustBeLessThanException extends Exception {
	
	public MustBeLessThanException(String varName, Number maxValue) {
		super(varName + " не может быть больше, чем " + maxValue.toString());
	}
}
