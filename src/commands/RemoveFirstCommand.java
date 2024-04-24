package commands;

public class RemoveFirstCommand extends Command {

	public RemoveFirstCommand(CommandManager requestor) {
		super("remove_first", "удаляет первый элемент коллекции", requestor);
		
		
	}

	@Override
	public void execute(String args) {
		this.getRequestor().getUniqueChecker().deleteValue(this.getRequestor().getCollection().getFirst());
		this.getRequestor().getCollection().removeFirst();
		
		System.out.println("Удален первый элемент коллекции");
		

	}

}
