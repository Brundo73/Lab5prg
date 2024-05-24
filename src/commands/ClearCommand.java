package commands;

public class ClearCommand extends Command {

	public ClearCommand(CommandManager requestor) {
		super("clear", "очищает коллекцию", requestor);
		
	}

	@Override
	public void execute(String args) {
		this.getRequestor().getCollection().clear();
		this.getRequestor().getUniqueChecker().clearValues();
		System.out.println("Коллекция очищена");
	}

}
