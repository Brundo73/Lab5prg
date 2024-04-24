package lab5prg;
import java.io.IOException;
import java.util.ArrayDeque;

import commands.CommandManager;


public class Main {

	public static void main(String[] args) throws IOException {
		String fileName = args[0].trim();
		JSONParser parser = new JSONParser();
		ArrayDeque<Product> ad = new ArrayDeque<Product>();
		ad = parser.parseFrom(fileName);
		CommandManager commandManager = new CommandManager(parser, ad);
		commandManager.interactive();
	}
}
