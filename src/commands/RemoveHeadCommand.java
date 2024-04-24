package commands;

public class RemoveHeadCommand extends Command {
	
	RemoveFirstCommand remove = new RemoveFirstCommand(this.getRequestor());
	HeadCommand head = new HeadCommand(this.getRequestor());

	public RemoveHeadCommand(CommandManager requestor) {
		super("remove_head", "выводит первый элемент коллекции и удаляет его", requestor);
		
	}

	@Override
	public void execute(String args) {
		head.execute("");
		remove.execute("");
		}

}
