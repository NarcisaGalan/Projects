package presesntation;



import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import businessLayer.ClientBll;
import businessLayer.FacturaBll;
import businessLayer.OrderBll;
import businessLayer.ProductBll;
import models.Client;
import models.Order;
import models.Product;

public class Controller {

	private View view;
	private ClientBll clientBll;
	private ProductBll productBll;
	private OrderBll orderBll;
	private FacturaBll facturaBll;
	private String clientName;
	private String productName;
	private int productPrice;
	private int productQuantity;

	public Controller() {
		view = new View();
		clientBll = new ClientBll();
		productBll = new ProductBll();
		orderBll = new OrderBll();
		facturaBll = new FacturaBll();
		fillClientsTable();
		fillCbClients();
		fillProductsTable();
		fillCbProducts();
		fillCbOrders();
		fillOrdersTable();

		view.actionAddClient(l -> {
			clientName = view.getTextNumeClient().getText();
			clientBll.insert(new Client(clientName));
			fillClientsTable();
			fillCbClients();
		});

		view.actionDeleteClient(l -> {
			clientName = view.getCbDeleteClient().getSelectedItem().toString();
			clientBll.deleteClient(clientName);
			fillClientsTable();
			fillCbClients();
		});

		view.actionAddProduct(l -> {
			productName = view.getTextNameProduct().getText();
			productPrice = Integer.parseInt(view.getTextPriceProduct().getText());
			productQuantity = Integer.parseInt(view.getTextQuantityProduct().getText());
			productBll.insert(new Product(productPrice, productQuantity, productName));
			fillProductsTable();
			fillCbProducts();
		});

		view.actionDeleteProduct(l -> {
			productName = view.getCbDeleteProduct().getSelectedItem().toString();
			productBll.delete(productName);
			fillProductsTable();
			fillCbProducts();
		});

		view.actionAddStock(l -> {
			productName = view.getCbAddProduct().getSelectedItem().toString();
			productQuantity = (int) view.getSpinnerAddQuantity().getValue();
			productBll.addQuantity(productName, productQuantity);
			fillProductsTable();
			fillCbProducts();
		});

		view.actionAddOrder(l -> {
			productQuantity = Integer.parseInt(view.getTextQuantityOrders().getText());
			clientName = view.getCbClientOrder().getSelectedItem().toString();

			int idOrder = orderBll.getNewOrderId();
			try {
				orderBll.addOrder(idOrder, clientName, view.getCbProductOrder().getSelectedItem().toString(),
						productQuantity);
				fillOrdersTable();
				fillProductsTable();
				facturaBll.printFactura(idOrder);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.view, e.getMessage());
			}

		});
	}

	public void fillClientsTable() {
		DefaultTableModel model = (DefaultTableModel) view.getTableClients().getModel();
		Object rowData[] = new Object[2];
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}

		List<Client> clients = clientBll.selectAll();

		for (int i = 0; i < clients.size(); i++) {
			rowData[0] = clients.get(i).getId();
			rowData[1] = clients.get(i).getName();
			model.addRow(rowData);
		}
	}

	@SuppressWarnings("unchecked")
	public void fillCbClients() {
		this.view.getCbDeleteClient().removeAllItems();
		List<Client> clients = clientBll.selectAll();
		for (int i = 0; i < clients.size(); i++) {
			this.view.getCbDeleteClient().addItem((clients.get(i).getName()));
		}
	}

	public void fillProductsTable() {
		DefaultTableModel model = (DefaultTableModel) view.getTableProducts().getModel();
		Object rowData[] = new Object[4];
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		List<Product> products = productBll.selectAll();

		for (int i = 0; i < products.size(); i++) {
			rowData[0] = products.get(i).getId();
			rowData[1] = products.get(i).getName();
			rowData[2] = products.get(i).getPricePerUnit();
			rowData[3] = products.get(i).getQuantity();
			model.addRow(rowData);
		}
	}

	@SuppressWarnings("unchecked")
	public void fillCbProducts() {
		this.view.getCbDeleteProduct().removeAllItems();
		this.view.getCbAddProduct().removeAllItems();
		this.view.getCbProductOrder().removeAllItems();
		List<Product> products = productBll.selectAll();
		for (int i = 0; i < products.size(); i++) {
			this.view.getCbDeleteProduct().addItem((products.get(i).getName()));
			this.view.getCbAddProduct().addItem(products.get(i).getName());
			this.view.getCbProductOrder().addItem(products.get(i).getName());
		}
	}

	public void fillCbOrders() {
		this.view.getCbClientOrder().removeAllItems();
		List<Client> products = clientBll.selectAll();
		for (int i = 0; i < products.size(); i++) {
			this.view.getCbClientOrder().addItem((products.get(i).getName()));

		}
	}

	public void fillOrdersTable() {

		DefaultTableModel model = (DefaultTableModel) view.getTableOrders().getModel();
		Object rowData[] = new Object[4];
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}

		List<Order> orders = orderBll.getCorrectOrders();

		for (int i = 0; i < orders.size(); i++) {
			rowData[0] = orders.get(i).getIdOrder();
			rowData[1] = orders.get(i).getClient();
			rowData[2] = orders.get(i).getProduct();
			rowData[3] = orders.get(i).getQuantity();
			model.addRow(rowData);

		}
	}
}

