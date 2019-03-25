package models;


public class Product {

	private int id;
	private String name;
	private int quantity;
	private int pricePerUnit;

	public Product() {

	}

	public Product(int costPerUnit, int quantity, String name) {
		this.pricePerUnit = costPerUnit;
		this.quantity = quantity;
		this.name = name;
	}

	public Product(int id, int costPerUnit, int quantity, String name) {
		super();
		this.id = id;
		this.pricePerUnit = costPerUnit;
		this.quantity = quantity;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

