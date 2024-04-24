package commands;

public class SaveCommand extends Command {
	
	
	

	public SaveCommand(CommandManager requestor) {
		super("save", "сохраняет коллекцию в файл", requestor);
		
	}

	@Override
	public void execute(String args) {
		this.getRequestor().getParser().parseTo(this.getRequestor().getCollection());
		

	}

}
