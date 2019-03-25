package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DefaultDBConnection implements DBConnection{

	private String url;
	
	public DefaultDBConnection(String url) {
		this.url=url+"root";
	}
	
	public DefaultDBConnection(String url,String user,String password) {
		this.url=url+user+"&password="+password;
	}
	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url);
		
	}

}
