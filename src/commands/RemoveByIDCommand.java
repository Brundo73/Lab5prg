package commands;

import lab5prg.Product;

public class RemoveByIDCommand extends Command {

	public RemoveByIDCommand(CommandManager requestor) {
		super("remove_by_id", "удаляет элемент, id которого равен заданному", requestor);
		
	}

	@Override
	public void execute(String args) {
		for ( Product prod : this.getRequestor().getCollection()) {
			if (prod.getId().equals(Long.valueOf(args.trim().split(" ")[0]))) {
				this.getRequestor().getUniqueChecker().deleteValue(prod);
				this.getRequestor().getCollection().remove(prod);
				this.getRequestor().col.remove(prod);
				System.out.println("Удалено:\n" + prod.toString());
				break;
			}
		}

	}

}
