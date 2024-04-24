package commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayDeque;
import lab5prg.Product;

public class InfoCommand extends Command {

	//ArrayDeque<Product> col;
	//JSONParser parser;
	public InfoCommand(CommandManager requestor) {
		super("info", "выводит информацию о коллекции", requestor);
		
	}

	@Override
	public void execute(String args) {
		
		ArrayDeque<Product> col=this.getRequestor().getCollection();
		System.out.println("Тип коллекции: "+col.getClass().getSimpleName());
		
		Path filePath = Path.of(this.getRequestor().getParser().getDefaultPath());
        BasicFileAttributes attrs;
		try {
			attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
			String creationTime = attrs.creationTime().toString();
	        System.out.println("Дата создания файла: " + creationTime);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Количество элементов в коллекции: "+col.size());
		
        

	}

}
