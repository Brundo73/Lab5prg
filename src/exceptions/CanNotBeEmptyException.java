package exceptions;

public class CanNotBeEmptyException extends Exception {


	public CanNotBeEmptyException(String varName) {
		super(varName + " не может быть пустой строкой");
	}
}
