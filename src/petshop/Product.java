package petshop;

public class Product {
	String name, type,url;
	int total,price;
	int id;
	static int number;
	
	static {
		number= 101;
	}
	
	Product(int id){
		this.id = id;
	}
	
	Product(){
		id = number++;
	}
	
	Product(String name, String type, int total, int price, String url ){
		this();
		this.name = name;
		this.type = type;
		this.total = total;
		this.price = price;
		this.url = url;
	}
	Product(int id,String name, String type, int total, int price,String url ){
		this.id = id;
		this.name = name;
		this.type = type;
		this.total = total;
		this.price = price;
		this.url = url;
	}
	
	Product(int id, String name, int total, int price){
		this.id = id;
		this.name = name;
		this.total = total;
		this.price = price;
	}
	
	String getName() {
		return this.name;
	}
	
	String getType() {
		return this.type;
	}
	
	int getTotal() {
		return this.total;
	}
	
	int getPrice() {
		return this.price;
	}
	
	String getUrl() {
		return this.url;
	}
	
	int getId() {
		return this.id;
	}
}
