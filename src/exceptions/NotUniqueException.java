package exceptions;

public class NotUniqueException extends Exception {

	

	public NotUniqueException() {
		super("Коллекция не удовлетворяет требованиям уникальности полей id и partNumber");
		
	}

}
