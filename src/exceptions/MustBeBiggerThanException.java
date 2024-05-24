package exceptions;

public class MustBeBiggerThanException extends Exception {

	public MustBeBiggerThanException(String varName, Number minValue) {
		super(varName + " не может быть меньше, чем " + minValue.toString());
		
	}

}
