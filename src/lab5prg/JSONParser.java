package lab5prg;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSONParser {
	
	ObjectMapper mapper;
	
	ArrayList<Product> list = new ArrayList<Product>();
	private String defaultPath = "files/products.json";
	
	
	public JSONParser() {
		this.mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
	}
	
	public JSONParser(String filePath) {
		this.defaultPath=filePath;
		mapper.registerModule(new JavaTimeModule());
	}
	
	public String getDefaultPath() {
		return this.defaultPath;
	}
	
	public ArrayDeque<Product> parseFrom(String fileName) {
		
		
		try {
			
			Path path = Paths.get(fileName);
			File file = path.toFile();
			
			if (!file.exists()) {
				if (fileName!=defaultPath) {
	            System.out.println("Файл не существует. Чтение из файла по умолчанию" + defaultPath);
				path = Paths.get(defaultPath);
				file = path.toFile();}
				else {
					System.out.println("Файл по умолчанию не существует.");
				}
	        }
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			list = mapper.readValue(bis, new TypeReference<ArrayList<Product>>() {});
			bis.close();
			
		} catch (IOException e) {
				System.out.println("Возникла ошибка при чтении файла.");
				e.printStackTrace();
				
			
		}
		ArrayDeque<Product> ad = new ArrayDeque<Product>(list);
		
		return ad;
	}
	
	public Product parseFromString(String productString) {
		try {
			Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
			ZonedDateTime creationDate = ZonedDateTime.now();
			Long epochSeconds = creationDate.toEpochSecond();
			String product = "{\"id\": " + id.toString()+", " + productString + ", \"creationDate\":"+ epochSeconds.toString() +"}";
			
			Product newProduct = mapper.readValue(product, Product.class);
			return newProduct;
			
		} catch (JsonMappingException e) {
			
			System.out.println("Неправильно введен новый элемент. Пример ввода элемента:\n"
					+ "\"name\":\"Product 2\",\"coordinates\":{\"x\":15,\"y\":25.0},\"price\":150,\"partNumber\":\"PN456\",\"manufactureCost\":75,\"unitOfMeasure\":\"SQUARE_METERS\",\"owner\":{\"name\":\"Jane Smith\",\"birthday\":[1985,2,15],\"weight\":65.0,\"passportID\":\"CD789012\"}");
			
		} catch (JsonProcessingException e) {
			
			System.out.println("Неправильно введен новый элемент. Пример ввода элемента:\n"
					+ "\"name\":\"Product 2\",\"coordinates\":{\"x\":15,\"y\":25.0},\"price\":150,\"partNumber\":\"PN456\",\"manufactureCost\":75,\"unitOfMeasure\":\"SQUARE_METERS\",\"owner\":{\"name\":\"Jane Smith\",\"birthday\":[1985,2,15],\"weight\":65.0,\"passportID\":\"CD789012\"}");	
		}
		return null;
	}
	
	
	public void parseTo(String fileName, ArrayDeque<Product> col) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				System.out.println("Файл не найден, создаем новый файл");
				try {
	            file.createNewFile();
				} catch (IOException e) {
					System.out.println("Произошла ошибка при создании файла: " + e.getMessage());
				}
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			mapper.writeValue(bw, col);
			bw.close();
			System.out.println("Запись в файл произошла успешно");
		} catch (IOException e) {
			System.out.println("Возникла ошибка при записи в файл");
		}
	}
	
	public void parseTo(ArrayDeque<Product> col) {
		this.parseTo(defaultPath, col);
	}
	
	public ArrayDeque<Product> parseFrom() {
		return this.parseFrom(defaultPath);
	}
}
