package commands;

import exceptions.RecursionException;

public class ExecuteScriptCommand extends Command {

	public ExecuteScriptCommand(CommandManager requestor) {
		super("execute_script", "выполняет скрипт из файла", requestor);
	}

	@Override
	public void execute(String args) {
		try {
			this.getRequestor().script(args.trim());
		} catch (RecursionException e) {
			System.out.println(e.getMessage());
			this.getRequestor().getCommandStack().clear();
		}
		
	}

}
