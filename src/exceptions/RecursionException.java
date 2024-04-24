package exceptions;

import java.util.Stack;

public class RecursionException extends Exception {
	public RecursionException(Stack<String> commandStack) {
		super("Обнаружена рекурсия при вызове файлов:\n"+commandStack.toString());
	}

}
