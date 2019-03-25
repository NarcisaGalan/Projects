package dataAccessLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import models.Order;


public class OrderDAO extends AbstractDAO<Order> {

	private ClientDAO clientDao = new ClientDAO();

	public Order findById(int id) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = "call ordermanagement.getOrders();";
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(query);
			rs = stm.executeQuery();
			for(Order order:this.createObjects(rs)) {
				if(order.getIdOrder() == id) {
					return order;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(stm);
			ConnectionFactory.close(con);
		}
		return null;
	}
	public int getOrderId() {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = "call ordermanagement.getMaxOrder();";
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(query);
			rs = stm.executeQuery();
			rs.next();
			return rs.getInt("idOrder");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(stm);
			ConnectionFactory.close(con);
		}
		return 0;
	}

	public void addOrder(Order order) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		int idClient = clientDao .getIdFromName(order.getClient());
		String query = "call ordermanagement.addOrder(" + order.getIdOrder() + "," + idClient + ",'"
				+ order.getProduct() + "'," + order.getQuantity() + ") ;";
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(query);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(stm);
			ConnectionFactory.close(con);
		}
	}

	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList();
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = "call ordermanagement.getOrders();";
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(query);
			rs = stm.executeQuery();
			return createObjects(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(stm);
			ConnectionFactory.close(con);
		}
		return null;
	}

/*	public List<OrderDetails> getCorrectOrders() {
		List<OrderDetails> correctOrders = new ArrayList();
		List<Order> orders = getAllOrders();
		int ok = 0;
		for (Order order : orders) {
			ok = 0;
			for (OrderDetails orderD : correctOrders) {
				if (order.getIdOrder() == orderD.getIdOrder()) {
					ok = 1;
				}
			}
			if (ok == 1) {
				for (OrderDetails orderD : correctOrders) {
					if (order.getIdOrder() == orderD.getIdOrder()) {
						orderD.addProduct(order.getProduct());
					}
				}
			} else {
				OrderDetails orderD = new OrderDetails();
				orderD.setIdClient(order.getIdClient());
				orderD.setIdOrder(order.getIdOrder());
				orderD.setQuantity(order.getQuant());
				orderD.addProduct(order.getProduct());
				correctOrders.add(orderD);
			}
		}
		return correctOrders;
	}*/
}
