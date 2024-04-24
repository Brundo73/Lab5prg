package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import exceptions.NotUniqueException;
import exceptions.RecursionException;
import lab5prg.JSONParser;
import lab5prg.Product;
import lab5prg.UniqueChecker;

public class CommandManager {
	
	private JSONParser parser;
	ArrayDeque<Product> col;
	
	HashMap<String, Command> cmds = new HashMap<String, Command>();
	private UniqueChecker uCheck;
	
	private Stack<String> scriptCommands = new Stack<String>();
		
	public CommandManager(JSONParser parser, ArrayDeque<Product> col) {
		this.parser = parser;
		this.col=col;
		try {
			this.uCheck=new UniqueChecker(col);
		} catch (NotUniqueException e) {
			
			System.out.println(e.getMessage());
		}
		HelpCommand help = new HelpCommand(this);
		SaveCommand save = new SaveCommand(this);
		InfoCommand info = new InfoCommand(this);
		ShowCommand show = new ShowCommand(this);
		ClearCommand clear = new ClearCommand(this);
		HeadCommand head = new HeadCommand(this);
		RemoveFirstCommand removeFirst = new RemoveFirstCommand(this);
		RemoveHeadCommand removeHead = new RemoveHeadCommand(this);
		AddCommand add = new AddCommand(this);
		RemoveByIDCommand remove_by_id  = new RemoveByIDCommand(this);
		UpdateByIDCommand update = new UpdateByIDCommand(this);
		SumOfPriceCommand sum = new SumOfPriceCommand(this);
		GroupCountingByManufactureCostCommand groupBy = new GroupCountingByManufactureCostCommand(this);
		PrintAscendingCommand print = new PrintAscendingCommand(this);
		ExecuteScriptCommand exeScript = new ExecuteScriptCommand(this);
		
	}
	
	public UniqueChecker getUniqueChecker() {
		return this.uCheck;
	}
	
	public Stack<String> getCommandStack() {
		return this.scriptCommands;
	}
	
	void addCommand(Command cmd) {
		cmds.put(cmd.getName(), cmd);
	}
	
	public JSONParser getParser() {
		return this.parser;
	}
	
	public ArrayDeque<Product> getCollection() {
		return this.col;
	}
	
	public void interactive() {
		try (
			Scanner consoleScanner = new Scanner(System.in)) {
				String[] userCommandText = consoleScanner.nextLine().trim().split(" ", 2);
				String userCommand = userCommandText[0];
				String userArgs = "";
				while (!(userCommand.equals("exit"))) {
					if (userCommandText.length>1) {
						userArgs=userCommandText[1];
					} else {
						userArgs="";
					}
					try {
						cmds.get(userCommand).execute(userArgs);
					} catch (NullPointerException e) {
						System.out.println("Неправильно введено имя команды или её не существует: " + userCommand);
						
					}
					userCommandText = consoleScanner.nextLine().trim().split(" ", 2);
					userCommand = userCommandText[0];
				
				}
				System.out.println("Завершение работы программы");
				System.exit(0);
			}
	}
	
	public void script(String filePath) throws RecursionException {
		if (scriptCommands.contains(filePath)) {
			
			throw new RecursionException(scriptCommands);
			
		}
		try {
			Scanner scanner = new Scanner(new File(filePath));
			String[] scriptCommandText = scanner.nextLine().trim().split(" ", 2);
			String scriptCommand = scriptCommandText[0];
			String scriptArgs = "";
			while(!(scriptCommand.equals("exit")) ) {
				if (scriptCommandText.length>1) {
					scriptArgs=scriptCommandText[1];
				} else {
					scriptArgs="";
				}
				try {
					cmds.get(scriptCommand).execute(scriptArgs);
				} catch (NullPointerException e) {
					System.out.println("Неправильно введено имя команды или её не существует: " + scriptCommand);
				}
				if (!scanner.hasNext()) {
					break;
				}
				scriptCommandText = scanner.nextLine().trim().split(" ", 2);
				scriptCommand = scriptCommandText[0];
				scriptArgs = "";
				
			}
			scanner.close();
			this.scriptCommands.removeElement(filePath);
		} catch (FileNotFoundException e) {
			System.out.println("Файла не существует");
		}
		
	}

}
