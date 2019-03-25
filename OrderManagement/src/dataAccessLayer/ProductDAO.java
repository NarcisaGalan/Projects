package dataAccessLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import models.Product;

public class ProductDAO extends AbstractDAO<Product> {

	public void addQuantity(int id, int quantity) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = "call ordermanagement.addQuantity(" + id + ", " + quantity + ");";
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

	public void delete(int id) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = "call ordermanagement.deleteProduct(" + id + ");";

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

}
