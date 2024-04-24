package commands;

import lab5prg.Product;

public class ShowCommand extends Command {

	public ShowCommand(CommandManager requestor) {
		super("show", "выводит все элементы коллекции в строковом представлении", requestor);
	}

	@Override
	public void execute(String args) {
		//System.out.println(this.getRequestor().getCollection().toString());
		for (Product prod : this.getRequestor().getCollection()) {
          System.out.print(prod.toString() + "\n\n");
      }
	}

}
