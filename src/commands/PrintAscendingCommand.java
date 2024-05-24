package commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lab5prg.Product;

public class PrintAscendingCommand extends Command {

	public PrintAscendingCommand(CommandManager requestor) {
		super("print_ascending", "выводит элементы коллекции в порядке возрастания", requestor);
	}

	@Override
	public void execute(String args) {
		List<Product> productsList = Arrays.asList(this.getRequestor().getCollection().toArray(new Product[0]));
        Collections.sort(productsList);
		for (Product prod : productsList) {
	          System.out.print(prod.toString() + "\n\n");
	      }

	}

}
