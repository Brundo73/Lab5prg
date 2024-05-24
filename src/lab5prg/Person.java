package lab5prg;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import exceptions.*;

public class Person {
	@JsonProperty("name")
	private String name;
	@JsonProperty("birthday")
	//private String birthdayString;
	private java.time.LocalDate birthday;
	@JsonProperty("weight")
	private Float weight;
	@JsonProperty("passportID")
	private String passportID;
	
	public Person() {
		
	};
	
	public Person(String name, java.time.LocalDate birthday, Float weight, String passportID) throws NotNullException, CanNotBeEmptyException, MustBeBiggerThanException {
		if (name==null) {
			throw new NotNullException("name");
		} else if (name.isBlank()) {
			throw new CanNotBeEmptyException("name");
		} else {
			this.name=name;
		}
		this.birthday=birthday;
		if (weight==null) {
			throw new NotNullException("weight");
		} else if (weight<=0) {
			throw new MustBeBiggerThanException("weight", 0);
		} else {
			this.weight=weight;
		}
		
		this.passportID=passportID;
	}
		
	
	public String getName() {
		return name;
	}
	
	public java.time.LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		//this.birthday = birthday;
	}
	
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) throws NotNullException, MustBeBiggerThanException {
		if (weight==null) {
			throw new NotNullException("weight");
		} else if (weight<=0) {
			throw new MustBeBiggerThanException("weight", 0);
		} else {
			this.weight=weight;
		}
	}
	
	public String getPassportID() {
		return passportID;
	}
	public void setPassportID(String passportID) {
		this.passportID = passportID;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o!=null && o instanceof Person) {
			if ( this.name.equals(((Person) o).getName()) 
				&& this.birthday.equals(((Person) o).getBirthday()) 
				&& this.weight.equals(((Person) o).getWeight()) 
				&& this.passportID.equals(((Person) o).getPassportID())) {				
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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
	
}
