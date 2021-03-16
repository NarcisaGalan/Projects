package businessLayer;



import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import dataAccessLayer.ClientDAO;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;
import models.Client;
import models.Order;

import models.Product;

public class OrderBll {

	private OrderDAO orderDao;
	private ProductDAO productDao;
	

	public OrderBll() {
		this.orderDao = new OrderDAO();
		
		this.productDao = new ProductDAO();
	}

	public Order findById(int id) {
		Order order = null;
		order = orderDao.findById(id);
		if (order == null) {
			throw new NoSuchElementException("The element with the id " + id + " doesnt exist!");
		}
		return order;
	}

	public void insert(Order client) {
		orderDao.insert(client);
	}

	public void update(Order client) {
		orderDao.update(client);
	}

	public int getNewOrderId() {
		return orderDao.getOrderId() + 1;
	}

	public void addOrder(int idOrder, String client, String product, int quantity) throws Exception {
		//
		Order order = new Order(idOrder, client, product, quantity);
		
		int id = productDao.getIdFromName(product);
		Product product1 = productDao.findById(id);
		if(product1.getQuantity() >= quantity) {
			orderDao.addOrder(order);
		}else {
			throw new Exception("Exista doar "+product1.getQuantity()+" bucati de "+product+"!");
		}
		
	}

	public List<Order> getCorrectOrders() {
		List<Order> orderDetails = new ArrayList();
		orderDetails = orderDao.getAllOrders();
		return orderDetails;
	}

}
