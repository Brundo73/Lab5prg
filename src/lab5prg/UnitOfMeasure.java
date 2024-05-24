package lab5prg;

public enum UnitOfMeasure {
	CENTIMETERS("см"),
	SQUARE_METERS("кв м"),
	LITERS("л"),
	GRAMS("г");
	
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;
	UnitOfMeasure(String message) {
		this.message=message;
	}
	
	
	public String getMessage() {
		return message;
	}
}
