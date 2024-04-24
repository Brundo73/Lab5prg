package lab5prg;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import exceptions.*;

import java.time.ZonedDateTime;

public class Product implements Comparable<Product> {
	
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

	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("coordinates")
	private Coordinates coordinates;
	@JsonProperty("creationDate")
	
	
	private java.time.ZonedDateTime creationDate;
	@JsonProperty("price")
	private Long price;
	@JsonProperty("partNumber")
	private String partNumber;
	@JsonProperty("manufactureCost")
	private Long manufactureCost;
	@JsonProperty("unitOfMeasure")
	private UnitOfMeasure unitOfMeasure;
	@JsonProperty("owner")
	private Person owner;
	
	public Product () {
		
	};
	
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
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws NotNullException, CanNotBeEmptyException {
		if (name==null) {
			throw new NotNullException("name");
		} else if (name.isBlank()) {
			throw new CanNotBeEmptyException("name");
		} else {
			this.name=name;
		}
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) throws NotNullException {
		if (coordinates==null) {
			throw new NotNullException("coordinates");
		} else {
			this.coordinates = coordinates;
		}
	}

	public java.time.ZonedDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.time.ZonedDateTime creationDate) throws NotNullException {
		if (creationDate==null) {
			throw new NotNullException("creationDate");
		} else {
			this.creationDate = creationDate;
		}
	}
	

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) throws MustBeBiggerThanException {
		if (price<=0) {
			throw new MustBeBiggerThanException("price", 0);
		} else {
			this.price = price;
		}
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) throws NotNullException, MustBeLessThanException {
		if (partNumber==null) {
			throw new NotNullException("partNumber");
		} else if (partNumber.length()>99) {
			throw new MustBeLessThanException("Длина partNumber", 99);
		} else {
			this.partNumber = partNumber;
		}
	}

	public Long getManufactureCost() {
		return manufactureCost;
	}

	public void setManufactureCost(Long manufactureCost) throws NotNullException {
		if (manufactureCost==null) {
			throw new NotNullException("manufactureCosr");
		} else {
			this.manufactureCost = manufactureCost;
		}
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) throws NotNullException {
		if (unitOfMeasure==null) {
			throw new NotNullException("unitOfMeasure");
		} else {
			this.unitOfMeasure = unitOfMeasure;
		}
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) throws NotNullException {
		if (owner==null) {
			throw new NotNullException("owner");
		} else {
			this.owner = owner;
		}
	}
	
	@Override
	public String toString() {
		return ("ID: " + id.toString() + "\nИмя: " +
	name + "\nКоординаты:\n\tX:" + coordinates.getX().toString() + "\n\tY: " + ((Double) coordinates.getY()).toString() + 
	"\nДата создания: " + creationDate.toString() + "\nЦена: " + price.toString() + "\nНомер части: " +
	partNumber + "\nЦена производства:" + manufactureCost.toString() + "\nЕдиницы измерения: " + 
	unitOfMeasure.getMessage() + "\nХозяин:\n\tИмя: " + owner.getName()+ "\n\tДата рождения: " + owner.getBirthday().toString() +"\n\tВес: " +
	owner.getWeight() + "\n\tНомер паспорта: " + owner.getPassportID());
		
	}
	
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
