package commands;

public abstract class Command {
	
	public abstract void execute(String args);
	private CommandManager requestor;
	private String name;
	private String description;
	
	public Command(String name, String description, CommandManager requestor) {
		//super();
		this.name = name;
		this.description = description;
		this.requestor=requestor;
		requestor.addCommand(this);
	}
	
//	public Command(String name2, String description2, CommandManager requestor2) {
//		// TODO Auto-generated constructor stub
//	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public CommandManager getRequestor() {
		return requestor;
	}
	
	
}
