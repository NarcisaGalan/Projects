package models;





public class Order {

	private int idOrder;
	private String Product;
	private int quantity;
	private String client;
	
	public Order() {

	}

	public Order(int id, String idC,String idP,int idQ) {
		this.idOrder = id;
		this.client = idC;
		this.Product = idP;
		this.quantity = idQ;

	}

	public String getProduct() {
		return Product;
	}

	public void setProduct(String idProduct) {
		this.Product = idProduct;
	}

	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String idClient) {
		this.client = idClient;
	}

	
}

