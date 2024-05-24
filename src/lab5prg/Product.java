package lab5prg;
import java.util.UUID;



import com.fasterxml.jackson.annotation.JsonProperty;

import exceptions.*;

import java.time.ZonedDateTime;

/*
 * Класс, коллекцией которого управляет программа
 */
public class Product implements Comparable<Product> {
	

	/**
	 * id продукта
	 */
	@JsonProperty("id")
	private Long id;
	
	/**
	 * Название продукта
	 */
	@JsonProperty("name")
	private String name;
	
	/**
	 * Координаты продукта
	 * @see Coordinates
	 */
	@JsonProperty("coordinates")
	private Coordinates coordinates;
	
	/**
	 * Дата создания продукта
	 */
	@JsonProperty("creationDate")
	private java.time.ZonedDateTime creationDate;
	
	/**
	 * Цена продукта
	 */
	@JsonProperty("price")
	private Long price;
	
	/**
	 * Номер части продукта
	 */
	@JsonProperty("partNumber")
	private String partNumber;
	
	/**
	 * Цена производства продукта
	 */
	@JsonProperty("manufactureCost")
	private Long manufactureCost;
	
	/**
	 * Единица измерения продукта
	 * @see unitOfMeasure
	 */
	@JsonProperty("unitOfMeasure")
	private UnitOfMeasure unitOfMeasure;
	
	/**
	 * Хозяин продукта
	 * @see Person
	 */
	@JsonProperty("owner")
	private Person owner;
	
	
	
	
	/**
	 * Конструктор по умолчанию
	 */
	public Product () {
		
	};
	
	
	
	/**
	 * Конструктор для Product
	 * @param name имя продукта
	 * @param coordinates координаты продукта
	 * @param price цена продукта
	 * @param partNumber номер части пролукта
	 * @param manufactureCost цена производства продукта
	 * @param unitOfMeasure елиница измерения продукта
	 * @param owner хозяин продукта
	 */
	public Product(String name, Coordinates coordinates, Long price,
			String partNumber, Long manufactureCost, UnitOfMeasure unitOfMeasure, Person owner) throws NotNullException, MustBeBiggerThanException, CanNotBeEmptyException, MustBeLessThanException {
		this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE; //максимальный long имеет 0 в знаковом бите и при побитовом "и" превращает знаковый бит в 0, то есть число может быть только положительным 
		
		if (name==null) {
			throw new NotNullException("name");
		} else if (name.isBlank()) {
			throw new CanNotBeEmptyException("name");
		} else {
			this.name=name;
		}
		
		if (coordinates==null) {
			throw new NotNullException("coordinates");
		} else {
			this.coordinates = coordinates;
		}
		
		this.creationDate = ZonedDateTime.now();
		
		if (price<=0) {
			throw new MustBeBiggerThanException("price", 0);
		} else {
			this.price = price;
		}
		
		if (partNumber==null) {
			throw new NotNullException("partNumber");
		} else if (partNumber.length()>99) {
			throw new MustBeLessThanException("Длина partNumber", 99);
		} else {
			this.partNumber = partNumber;
		}
		
		if (manufactureCost==null) {
			throw new NotNullException("manufactureCost");
		} else {
			this.manufactureCost = manufactureCost;
		}
		if (unitOfMeasure==null) {
			throw new NotNullException("unitOfMeasure");
		} else {
			this.unitOfMeasure = unitOfMeasure;
		}
		
		if (owner==null) {
			throw new NotNullException("owner");
		} else {
			this.owner = owner;
		}
	}
	
	
	
	
	/**
	 * Устанавливает значение Id равным данному
	 * @param id данный id
	 * @throws MustBeBiggerThanException Исключение, выкидываемое при невыполнении условия "должен быть больше 0"
	 * @throws NotNullException Исключение, выкидываемое при невыполнении условия "не может быть null"
	 */
	public void setId(Long id) throws MustBeBiggerThanException, NotNullException {
		if (id==null) {
			throw new NotNullException("ID");
		}
		else if (id.compareTo(Long.valueOf(0))<=0) {
			throw new MustBeBiggerThanException("ID", 0);
		} else {
		this.id = id;
		}
		
	}
	
	
	/**
	 * Сравнивает этот объект с данным. Возвращает true, если объекты равны, и false, если не равны
	 * @param o объект для сравнения
	 * @return результат сравнения
	 */
	@Override
	public boolean equals(Object o) {
		if (o!=null && o instanceof Product) {
			if (this.id.equals(((Product) o).getId()) 
					&& this.name.equals( ((Product) o).getName()) 
					&& this.coordinates.equals(((Product) o).getCoordinates())
					&& this.getCreationDate().equals( ((Product) o).getCreationDate() ) 
					&& this.price.equals(((Product) o).getPrice())
					&& this.partNumber.equals( ((Product) o).getPartNumber() )
					&& this.manufactureCost.equals( ((Product) o).getManufactureCost() )
					&& this.unitOfMeasure==((Product) o).getUnitOfMeasure() 
					&& this.owner.equals( ((Product) o).getOwner())) {
				return true;
			}
		}
		return false;
		
	}

	/**
	 *Возвращает 1, если данный продукт меньше этого, -1 если больше и 0, если они равны
	 *@param o данный продукт
	 *@see Comparable
	 *@return результат сравнения (1 - данный меньше, 0 - равны, -1 - этот меньше)
	 */
	@Override
	public int compareTo(Product o) {
		if (this.equals(o)) {
			return 0;
		} else if ( this.name.compareTo(o.getName())>0 ) {
			return 1;
		} else if (this.name.compareTo(o.getName())<0) {
			return -1;
		} else if (this.coordinates.compareTo(o.getCoordinates())>0) {
			return 1;
		} else if (this.coordinates.compareTo(o.getCoordinates())<0) {
			return -1;
		} else if (this.creationDate.compareTo(o.getCreationDate())>0) {
			return 1;
		} else if (this.creationDate.compareTo(o.getCreationDate())<0) {
			return -1;
		} else if (this.price>o.getPrice()) {
			return 1;
		} else if (this.price<o.getPrice()) {
			return -1;
		} else if (this.partNumber.compareTo(o.getPartNumber())>0) {
			return 1;
		} else if (this.partNumber.compareTo(o.getPartNumber())<0) {
			return -1;
		} else if (this.manufactureCost>o.getManufactureCost()) {
			return 1;
		} else if (this.manufactureCost<o.getManufactureCost()) {
			return -1;
		} else if (this.unitOfMeasure.toString().compareTo(o.getUnitOfMeasure().toString())>0) {
			return 1;
		} else if (this.unitOfMeasure.toString().compareTo(o.getUnitOfMeasure().toString())<0) {
			return -1;
		} else if (this.owner.getName().compareTo(o.getOwner().getName()) > 0) {
			return 1;
		} else if (this.owner.getName().compareTo(o.getOwner().getName()) < 0) {
			return -1;
		} else if (this.id>o.getId()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	/**
	 * Возвращает id этого товара
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Возвращает название продукта
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Устанавливает название продукта равным данному
	 * @param name данное название
	 * @throws NotNullException Исключение, выкидываемое при невыполнении условия "не может быть null"
	 * @throws CanNotBeEmptyException Исключение, выкидываемое при невыполнении условия "не может быть пустой строкой"
	 */
	public void setName(String name) throws NotNullException, CanNotBeEmptyException {
		if (name==null) {
			throw new NotNullException("name");
		} else if (name.isBlank()) {
			throw new CanNotBeEmptyException("name");
		} else {
			this.name=name;
		}
	}

	/**
	 * Возвращает координаты этого продукта
	 * @return координаты
	 * @see Coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * Устанавливает координаты этого продукта равными данному
	 * @param coordinates данные координаты
	 * @throws NotNullException Исключение, выкидываемое при невыполнении условия "не может быть null"
	 * @see Coordinates
	 */
	public void setCoordinates(Coordinates coordinates) throws NotNullException {
		if (coordinates==null) {
			throw new NotNullException("coordinates");
		} else {
			this.coordinates = coordinates;
		}
	}

	/**
	 * Возвращает дату создания этого продукта
	 * @return дата создания 
	 */
	public java.time.ZonedDateTime getCreationDate() {
		return creationDate;
	}

	/**
	 * Устанавливает дату создания этого продукта
	 * @param creationDate данная цена
	 * @throws NotNullException Исключение, выкидываемое при невыполнении условия "не может быть null"
	 */
	public void setCreationDate(java.time.ZonedDateTime creationDate) throws NotNullException {
		if (creationDate==null) {
			throw new NotNullException("creationDate");
		} else {
			this.creationDate = creationDate;
		}
	}
	

	/**
	 * Возвращает цену этого продукта
	 * @return цена продукта
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * Устанавливает цену этого продукта 
	 * @param price данная цена
	 * @throws MustBeBiggerThanException
	 */
	public void setPrice(Long price) throws MustBeBiggerThanException {
		if (price<=0) {
			throw new MustBeBiggerThanException("price", 0);
		} else {
			this.price = price;
		}
	}

	/**
	 * Возвращает номер части
	 * @return номер части
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 * Устанавливает номер части для этого продукта в соответствие с данным номером части
	 * @param partNumber данный номер части
	 * @throws NotNullException Исключение, выкидываемое при неудовлетворении условию "не может быть null"
	 * @throws MustBeLessThanException Исключение, выкидываемое при неудовлетворении условию "длина строки не может превышать 99"
	 */
	public void setPartNumber(String partNumber) throws NotNullException, MustBeLessThanException {
		if (partNumber==null) {
			throw new NotNullException("partNumber");
		} else if (partNumber.length()>99) {
			throw new MustBeLessThanException("Длина partNumber", 99);
		} else {
			this.partNumber = partNumber;
		}
	}

	/**
	 * Возвращает цену производства для этого продукта
	 * @return Цена производства этого продукта
	 */
	public Long getManufactureCost() {
		return manufactureCost;
	}

	/**
	 * Устанавливает значение цены производства этого продукта на данное
	 * @param manufactureCost Новая цена продукта
	 * @throws NotNullException Исключение, выкидываемое при нарушении условния "не может быть null"
	 */
	public void setManufactureCost(Long manufactureCost) throws NotNullException {
		if (manufactureCost==null) {
			throw new NotNullException("manufactureCost");
		} else {
			this.manufactureCost = manufactureCost;
		}
	}

	/**
	 * Возвращает единицы измерения данного продукта
	 * @return единица измерения этого продукта
	 * @see UnitOfMeasure
	 */
	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	/**
	 * Этот метод устанавливает единицу измерения для этого продукта
	 * @param unitOfMeasure Устанавливаемое значение единицы измерения
	 * @throws NotNullException Исключение, если единица измерения не модет быть null
	 * @see UnitOfMeasure
	 */
	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) throws NotNullException {
		if (unitOfMeasure==null) {
			throw new NotNullException("unitOfMeasure");
		} else {
			this.unitOfMeasure = unitOfMeasure;
		}
	}

	/**
	 * Этот метод возвращает хозяина этого продукта
	 * @return Хозяин данного продукта
	 * @see Person
	 */
	public Person getOwner() {
		return owner;
	}

	/**
	 * Устанавливает данного человека как хозяина этого продукта
	 * @param owner Хозяин продукта
	 * @throws NotNullException Исключение. Выкидывается, если данный хозяин не удовлетворяет требованию "не может быть null"
	 * @see Person
	 */
	public void setOwner(Person owner) throws NotNullException {
		if (owner==null) {
			throw new NotNullException("owner");
		} else {
			this.owner = owner;
		}
	}
	
	/**
	 *Возвращает строковое представление этого продукта
	 *@return строка, соответствующая этому продукту
	 */
	@Override
	public String toString() {
		return ("ID: " + id.toString() + "\nИмя: " +
				name + "\nКоординаты:\n\tX:" + coordinates.getX().toString() + "\n\tY: " + ((Double) coordinates.getY()).toString() + 
				"\nДата создания: " + creationDate.toString() + "\nЦена: " + price.toString() + "\nНомер части: " +
				partNumber + "\nЦена производства:" + manufactureCost.toString() + "\nЕдиницы измерения: " + 
				unitOfMeasure.getMessage() + "\nХозяин:\n\tИмя: " + owner.getName()+ "\n\tДата рождения: " + owner.getBirthday().toString() +"\n\tВес: " +
				owner.getWeight() + "\n\tНомер паспорта: " + owner.getPassportID());
		
	}
	
	/*
	 * Обновляет значение этого продукта в соответствии с данным объектом
	 * @param prod нужный продукт
	 */
	public void update(Product prod) throws MustBeBiggerThanException, NotNullException, CanNotBeEmptyException, MustBeLessThanException {
		this.setId(prod.getId());
		this.setCoordinates(prod.getCoordinates());
		this.setCreationDate(prod.getCreationDate());
		this.setManufactureCost(prod.getManufactureCost());
		this.setName(prod.getName());
		this.setOwner(prod.getOwner());
		this.setPartNumber(prod.getPartNumber());
		this.setPrice(prod.getPrice());
		this.setUnitOfMeasure(prod.getUnitOfMeasure());
	}

	

}
