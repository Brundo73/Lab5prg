package commands;

import java.util.HashSet;

import lab5prg.Product;

public class GroupCountingByManufactureCostCommand extends Command {

	public GroupCountingByManufactureCostCommand(CommandManager requestor) {
		super("group_counting_by_manufacture_cost", "выводит количество групп по цене производства", requestor);
	}

	@Override
	public void execute(String args) {
		HashSet<Long> manufactureCosts = new HashSet<Long>();
		
		for ( Product prod : this.getRequestor().getCollection()) {            
            manufactureCosts.add(prod.getManufactureCost());
		}
		System.out.println("Количество групп в коллекции: " + manufactureCosts.size());
	}

}
