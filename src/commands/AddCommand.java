package commands;

import lab5prg.Product;

public class AddCommand extends Command {

	public AddCommand(CommandManager requestor) {
		super("add", "добавляет элемент в коллекцию", requestor);
	}

	@Override
	public void execute(String args)  {
		Product newProduct=this.getRequestor().getParser().parseFromString(args);
		if (!this.getRequestor().getUniqueChecker().checkUniqueFields(newProduct)) {
			System.out.println("Значения полей не удовлетворяют требованиям уникальности полей id и partNumber");
		} else {
			this.getRequestor().getCollection().add(newProduct);
		}
	}

}