package commands;

public class HelpCommand extends Command {

	public HelpCommand(CommandManager requestor) {
		super("help", "выводит справку по доступным командам", requestor);
		
	}

	@Override
	public void execute(String args) {
		System.out.println(
				"help : " + this.getRequestor().cmds.get("help").getDescription() + 
				"\ninfo : " + this.getRequestor().cmds.get("info").getDescription() +
				"\nshow : "+ this.getRequestor().cmds.get("show").getDescription() +
				"\nadd {element} : "+ this.getRequestor().cmds.get("add").getDescription() +
				"\nupdate id {element} : "+ this.getRequestor().cmds.get("update").getDescription() +
				"\nremove_by_id id : "+ this.getRequestor().cmds.get("remove_by_id").getDescription() +
				"\nclear : "+ this.getRequestor().cmds.get("clear").getDescription() +
				"\nsave : "+ this.getRequestor().cmds.get("save").getDescription() +
				"\nexecute_script file_name : "+ this.getRequestor().cmds.get("execute_script").getDescription() +
				"\nexit : завершает работу программы (без сохранения)" + 
				"\nremove_first : "+ this.getRequestor().cmds.get("remove_first").getDescription() +
				"\nhead : "+ this.getRequestor().cmds.get("head").getDescription() +
				"\nremove_head : "+ this.getRequestor().cmds.get("remove_head").getDescription() +
				"\nsum_of_price : "+ this.getRequestor().cmds.get("sum_of_price").getDescription() +
				"\ngroup_counting_by_manufacture_cost : "+ this.getRequestor().cmds.get("group_counting_by_manufacture_cost").getDescription() +
				"\nprint_ascending : " + this.getRequestor().cmds.get("print_ascending").getDescription());
		
		
		
	}

}
