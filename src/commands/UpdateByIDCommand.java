package commands;

import exceptions.CanNotBeEmptyException;
import exceptions.MustBeBiggerThanException;
import exceptions.MustBeLessThanException;
import exceptions.NotNullException;
import lab5prg.Product;

public class UpdateByIDCommand extends Command {

	public UpdateByIDCommand(CommandManager requestor) {
		super("update", "обновляет значение элемента, id которого равен заданному", requestor);
		
	}

	@Override
	public void execute(String args) {
		
		String newProdString = args.trim().split(" ", 2)[1];
		Product newProduct = this.getRequestor().getParser().parseFromString(newProdString); 
		
		for ( Product prod : this.getRequestor().getCollection()) {
			if (prod.getId().equals(Long.valueOf(args.trim().split(" ")[0]))) {
				this.getRequestor().getUniqueChecker().deleteValue(prod);
				if (this.getRequestor().getUniqueChecker().checkUniqueFields(newProduct)) {
					try {
						prod.update(newProduct);
						System.out.println("Значение элемента успешно обновлено");
					} catch (MustBeBiggerThanException | NotNullException | CanNotBeEmptyException
							| MustBeLessThanException e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("Новое значение не соответствует критериям уникальности полей ID и partNumber");
					this.getRequestor().getUniqueChecker().addNewValues(prod);
				}
				break;
			}
		}

	}

}
