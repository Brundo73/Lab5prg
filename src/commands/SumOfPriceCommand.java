package commands;

import lab5prg.Product;

public class SumOfPriceCommand extends Command {

	public SumOfPriceCommand(CommandManager requestor) {
		super("sum_of_price", "выводит сумму цен на все продукты в коллекции", requestor);
		
	}

	@Override
	public void execute(String args) {
		long sum = 0L;
		for ( Product prod : this.getRequestor().getCollection()) {
			sum+=prod.getPrice();
		}
		System.out.println("Сумма цен элементов коллекции равна " + sum);
	}

}


