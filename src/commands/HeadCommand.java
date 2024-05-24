package commands;

public class HeadCommand extends Command {

	public HeadCommand(CommandManager requestor) {
		super("head", "показывает первый элемент коллекции", requestor);
		
	}

	@Override
	public void execute(String args) {
		System.out.println(this.getRequestor().getCollection().getFirst().toString());
	}

}
