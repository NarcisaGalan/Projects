package dataAccessLayer;



import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class AbstractDAO<T> {

	public static final Logger logger = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private String getIdFromNameQuery(String name) {
		return "select id from " + type.getSimpleName() + " where name = '" + name + "';";
	}

	private String deleteQuery(int id) {
		return "SET FOREIGN_KEY_CHECKS=0; \ndelete from " + type.getSimpleName() + " where id =" + id + ";\nSET FOREIGN_KEY_CHECKS=1;";
	}

	private String createSelectAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append(" * ");
		sb.append(" from ");
		sb.append(type.getSimpleName());
		sb.append(" ;");
		return sb.toString();
	}

	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append(" * ");
		sb.append(" from ");
		sb.append(type.getSimpleName());
		sb.append(" where " + field + " = ?;");
		return sb.toString();
	}

	private String createInsertQuery(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ");
		sb.append(type.getSimpleName().toLowerCase());
		sb.append(" (");
		for (int i = 0; i < type.getDeclaredFields().length - 1; i++) {
			if (!type.getDeclaredFields()[i].getName().equals("id")) {
				sb.append(type.getDeclaredFields()[i].getName() + ", ");
			}
		}
		sb.append(type.getDeclaredFields()[type.getDeclaredFields().length - 1].getName());
		sb.append(") values ( ");
		try {
			Object value;

			for (Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.getName() != "id") {
					if (!field.equals(type.getDeclaredFields()[type.getDeclaredFields().length - 1])) {
						value = field.get(t);
						sb.append("'" + value + "', ");
					} else {
						value = field.get(t);
						sb.append("'" + value + "'); ");
					}
				}
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String createUpdateQuery(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("update ");
		sb.append(type.getSimpleName());
		sb.append(" set ");
		try {
			Object value;
			for (Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.getName() != "id") {
					if (!field.equals(type.getDeclaredFields()[type.getDeclaredFields().length - 1])) {
						value = field.get(t);
						sb.append(field.getName() + "= " + value + ",");
					} else {
						value = field.get(t);
						sb.append(field.getName() + "= " + value + "");
					}
				}
			}

			sb.append(" where id = " + t.getClass().getField("id") + "; ");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	@SuppressWarnings("deprecation")
	public List<T> createObjects(ResultSet rs) {
		List<T> list = new ArrayList();
		try {
			while (rs.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					field.setAccessible(true);
					Object value = rs.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<T> selectAll() {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = this.createSelectAllQuery();
		System.out.println(query);
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(query);
			rs = stm.executeQuery();
			return this.createObjects(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(stm);
			ConnectionFactory.close(con);
		}
		return null;
	}

	public T findById(int id) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = createSelectQuery("id");
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(query);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			return this.createObjects(rs).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(stm);
			ConnectionFactory.close(con);
		}
		return null;
	}

	public int getIdFromName(String name) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = this.getIdFromNameQuery(name);

		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(query);
			rs = stm.executeQuery();
			rs.next();
			return rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(stm);
			ConnectionFactory.close(con);
		}
		return 0;
	}

	public void delete(int id) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = this.deleteQuery(id);

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

	public void insert(T newObject) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = this.createInsertQuery(newObject);
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

	public void update(T t) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = this.createUpdateQuery(t);
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(query);
			rs = stm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(stm);
			ConnectionFactory.close(con);
		}
	}

}

