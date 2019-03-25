package businessLayer;



import java.util.List;
import java.util.NoSuchElementException;
import dataAccessLayer.ProductDAO;
import models.Product;

public class ProductBll {

	private ProductDAO productDao;

	public ProductBll() {
		this.productDao = new ProductDAO();
	}

	public Product findById(int id) {
		Product product = null;
		product = productDao.findById(id);
		if (product == null) {
			throw new NoSuchElementException("The element with the id " + id + " doesnt exist!");
		}
		return product;
	}

	public void insert(Product client) {
		productDao.insert(client);
	}

	public void update(Product client) {
		productDao.update(client);
	}

	public List<Product> selectAll() {
		return productDao.selectAll();
	}
	
	public void delete(String name) {
		int id = productDao.getIdFromName(name);
		productDao.delete(id);
	}
	
	public void addQuantity(String name, int quantity) {
		int id = productDao.getIdFromName(name);
		productDao.addQuantity(id, quantity);
	}
	
	public int getPrice(String name) {
		int id = productDao.getIdFromName(name);
		Product product = findById(id);
		return product.getPricePerUnit();
	}
}

