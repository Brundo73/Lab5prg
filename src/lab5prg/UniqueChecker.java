package lab5prg;

import java.util.ArrayDeque;
import java.util.HashSet;

import exceptions.NotUniqueException;


public class UniqueChecker {
	HashSet<String> uniquePartNumber;
	HashSet<Long> uniqueID;
	
	public UniqueChecker(ArrayDeque<Product> products) throws NotUniqueException{
		this.uniqueID=new HashSet<Long>();
		this.uniquePartNumber=new HashSet<String>();
		boolean isUnique=true;
		for ( Product prod : products) {
	           
            String partNumberValue = prod.getPartNumber();
            Long idValue = prod.getId();
            isUnique=this.uniquePartNumber.add(partNumberValue) && this.uniqueID.add(idValue);            
            if (!isUnique) {
            	throw new NotUniqueException();
		}
		}
	}
	
    public boolean checkUniqueFields(Product newProduct) {        
        return (this.uniquePartNumber.add(newProduct.getPartNumber()) && this.uniqueID.add(newProduct.getId()));
    }
    
    public void addNewValues(Product newProduct) {
    	this.uniqueID.add(newProduct.getId());
    	this.uniquePartNumber.add(newProduct.getPartNumber());
    }
    
    public void deleteValue(Product prod) {
    	this.uniqueID.remove(prod.getId());
    	this.uniquePartNumber.remove(prod.getPartNumber());
    }
    
    public void clearValues() {
    	this.uniqueID.clear();
    	this.uniquePartNumber.clear();
    }
}

