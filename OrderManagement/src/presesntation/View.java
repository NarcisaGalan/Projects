package presesntation;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class View extends JFrame {

	private JPanel contentPane;
	private JTable tableClients;
	private JTextField textNumeClient;
	private JTable tableProducts;
	private JTextField textNameProduct;
	private JTextField textPriceProduct;
	private JTextField textQuantityProduct;
	private JTextField textQuantityOrders;
	private JTable tableOrders;
	private JButton btnAddClient;
	private JComboBox cbClientOrder;
	private JButton btnAddOrder;
	private JButton btnAddStock;
	private JSpinner spinnerAddQuantity;
	private JComboBox cbAddProduct;
	private JButton btnDeleteProduct;
	private JComboBox cbDeleteProduct;
	private JButton btnAddProduct;
	private JButton btnDeleteClient;
	private JComboBox cbDeleteClient;
	private List<JCheckBox> checkBoxes;
	private DefaultTableModel tabelModel;
	private JComboBox cbProductOrder;

	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkBoxes = new ArrayList();
		this.setVisible(true);
		setBounds(100, 100, 450, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 434, 306);
		contentPane.add(tabbedPane_1);

		JPanel panelClients = new JPanel();
		tabbedPane_1.addTab("Clients", null, panelClients, null);
		panelClients.setLayout(null);

		JPanel panelClientsTable = new JPanel();
		panelClientsTable
				.setBorder(new TitledBorder(null, "Clienti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelClientsTable.setBounds(206, 11, 219, 256);
		panelClients.add(panelClientsTable);
		panelClientsTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 16, 207, 229);
		panelClientsTable.add(scrollPane);

		tableClients = new JTable();
		tableClients.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tableClients);

		JPanel panelAddClients = new JPanel();
		panelAddClients.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Adaugare client", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAddClients.setBounds(28, 17, 167, 113);
		panelClients.add(panelAddClients);
		panelAddClients.setLayout(null);

		JLabel lblNume = new JLabel("Nume:");
		lblNume.setBounds(10, 23, 58, 14);
		panelAddClients.add(lblNume);

		textNumeClient = new JTextField();
		textNumeClient.setBounds(59, 20, 86, 20);
		panelAddClients.add(textNumeClient);
		textNumeClient.setColumns(10);

		btnAddClient = new JButton("Adaugare");
		btnAddClient.setBounds(56, 58, 89, 23);
		panelAddClients.add(btnAddClient);

		JPanel panelDeleteClients = new JPanel();
		panelDeleteClients.setBorder(
				new TitledBorder(null, "Stergere client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDeleteClients.setBounds(28, 141, 167, 113);
		panelClients.add(panelDeleteClients);
		panelDeleteClients.setLayout(null);

		JLabel label_6 = new JLabel("Client:");
		label_6.setBounds(10, 33, 46, 14);
		panelDeleteClients.add(label_6);

		cbDeleteClient = new JComboBox();
		cbDeleteClient.setBounds(59, 30, 86, 22);
		panelDeleteClients.add(cbDeleteClient);

		btnDeleteClient = new JButton("Stergere");
		btnDeleteClient.setBounds(56, 79, 89, 23);
		panelDeleteClients.add(btnDeleteClient);

		JPanel panelProducts = new JPanel();
		tabbedPane_1.addTab("Products", null, panelProducts, null);
		panelProducts.setLayout(null);

		JPanel panelAddProduct = new JPanel();
		panelAddProduct.setBorder(
				new TitledBorder(null, "Adaugare produs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAddProduct.setBounds(4, 12, 196, 137);
		panelProducts.add(panelAddProduct);
		panelAddProduct.setLayout(null);

		JLabel label = new JLabel("Nume:");
		label.setBounds(10, 27, 58, 14);
		panelAddProduct.add(label);

		textNameProduct = new JTextField();
		textNameProduct.setColumns(10);
		textNameProduct.setBounds(88, 24, 86, 20);
		panelAddProduct.add(textNameProduct);

		btnAddProduct = new JButton("Adaugare");
		btnAddProduct.setBounds(44, 103, 89, 23);
		panelAddProduct.add(btnAddProduct);

		JLabel label_1 = new JLabel("Pret:");
		label_1.setBounds(10, 52, 46, 14);
		panelAddProduct.add(label_1);

		textPriceProduct = new JTextField();
		textPriceProduct.setColumns(10);
		textPriceProduct.setBounds(88, 49, 86, 20);
		panelAddProduct.add(textPriceProduct);

		JLabel label_2 = new JLabel("Cantitate:");
		label_2.setBounds(10, 78, 70, 14);
		panelAddProduct.add(label_2);

		textQuantityProduct = new JTextField();
		textQuantityProduct.setColumns(10);
		textQuantityProduct.setBounds(88, 75, 86, 20);
		panelAddProduct.add(textQuantityProduct);

		JPanel panelDeleteProduct = new JPanel();
		panelDeleteProduct.setBorder(
				new TitledBorder(null, "Stergere produs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDeleteProduct.setBounds(4, 160, 196, 118);
		panelProducts.add(panelDeleteProduct);
		panelDeleteProduct.setLayout(null);

		JLabel label_3 = new JLabel("Produs:");
		label_3.setBounds(10, 51, 46, 14);
		panelDeleteProduct.add(label_3);

		cbDeleteProduct = new JComboBox();
		cbDeleteProduct.setBounds(85, 51, 89, 22);
		panelDeleteProduct.add(cbDeleteProduct);

		btnDeleteProduct = new JButton("Stergere");
		btnDeleteProduct.setBounds(49, 84, 89, 23);
		panelDeleteProduct.add(btnDeleteProduct);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Produse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(202, 12, 227, 140);
		panelProducts.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 16, 215, 113);
		panel_3.add(scrollPane_1);

		tableProducts = new JTable();
		tableProducts
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "id", "Nume", "Pret", "Cantitate" }) {
					Class[] columnTypes = new Class[] { Integer.class, String.class, Float.class, Integer.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		scrollPane_1.setViewportView(tableProducts);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Adaugare stoc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(202, 160, 227, 118);
		panelProducts.add(panel_2);
		panel_2.setLayout(null);

		JLabel label_4 = new JLabel("Produs:");
		label_4.setBounds(21, 26, 46, 14);
		panel_2.add(label_4);

		JLabel label_5 = new JLabel("Cantitate:");
		label_5.setBounds(129, 26, 72, 14);
		panel_2.add(label_5);

		cbAddProduct = new JComboBox();
		cbAddProduct.setBounds(21, 51, 77, 22);
		panel_2.add(cbAddProduct);

		spinnerAddQuantity = new JSpinner();
		spinnerAddQuantity.setBounds(129, 51, 77, 22);
		panel_2.add(spinnerAddQuantity);

		btnAddStock = new JButton("Adaugare");
		btnAddStock.setBounds(74, 84, 89, 23);
		panel_2.add(btnAddStock);

		JPanel panelOrders = new JPanel();
		tabbedPane_1.addTab("Orders", null, panelOrders, null);
		panelOrders.setLayout(null);

		JPanel panelCurrentOrders = new JPanel();
		panelCurrentOrders.setLayout(null);
		panelCurrentOrders.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Comenzi",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCurrentOrders.setBounds(200, 11, 219, 256);
		panelOrders.add(panelCurrentOrders);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 22, 199, 223);
		panelCurrentOrders.add(scrollPane_2);

		tableOrders = new JTable();
		tableOrders.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "idOrder", "Client", "Produse", "Cantitate" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableOrders.getColumnModel().getColumn(2).setPreferredWidth(98);
		tabelModel = (DefaultTableModel) tableOrders.getModel();
		scrollPane_2.setViewportView(tableOrders);

		JPanel panelAddOrder = new JPanel();
		panelAddOrder.setLayout(null);
		panelAddOrder.setBorder(
				new TitledBorder(null, "Adaugare comanda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAddOrder.setBounds(22, 17, 167, 133);
		panelOrders.add(panelAddOrder);

		JLabel lblClientFromOrder = new JLabel("Nume:");
		lblClientFromOrder.setBounds(10, 23, 58, 14);
		panelAddOrder.add(lblClientFromOrder);

		btnAddOrder = new JButton("Adaugare");
		btnAddOrder.setBounds(41, 100, 89, 23);
		panelAddOrder.add(btnAddOrder);

		cbClientOrder = new JComboBox();
		cbClientOrder.setBounds(71, 19, 86, 22);
		panelAddOrder.add(cbClientOrder);

		JLabel lblProdusO = new JLabel("Produs:");
		lblProdusO.setBounds(10, 48, 46, 14);
		panelAddOrder.add(lblProdusO);

		JLabel lblQuantityO = new JLabel("Cantitate:");
		lblQuantityO.setBounds(10, 75, 46, 14);
		panelAddOrder.add(lblQuantityO);

		textQuantityOrders = new JTextField();
		textQuantityOrders.setBounds(71, 69, 86, 20);
		panelAddOrder.add(textQuantityOrders);
		textQuantityOrders.setColumns(10);

		cbProductOrder = new JComboBox();
		cbProductOrder.setBounds(71, 44, 86, 22);
		panelAddOrder.add(cbProductOrder);
		// MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
		// this.getTableOrders().setDefaultRenderer(String[].class, renderer);;
		// this.getTableOrders().getColumnModel().getColumn(2).setCellRenderer(renderer);
		// add(new JScrollPane(tableOrders));

		tableOrders = new JTable(tabelModel);
		int lines = 3;
		tableOrders.setRowHeight(getTableOrders().getRowHeight() * lines);

	}

	public JComboBox getCbProductOrder() {
		return cbProductOrder;
	}

	public void setCbProductOrder(JComboBox cbProductOrder) {
		this.cbProductOrder = cbProductOrder;
	}

	public void addCheckBox(String product) {
		JCheckBox cbProduct = new JCheckBox(product);
		// panelChechBProduse.add(cbProduct);
	}

	public JTable getTableClients() {
		return tableClients;
	}

	public void setTableClients(JTable tableClients) {
		this.tableClients = tableClients;
	}

	public JTextField getTextNumeClient() {
		return textNumeClient;
	}

	public void setTextNumeClient(JTextField textNumeClient) {
		this.textNumeClient = textNumeClient;
	}

	public JTable getTableProducts() {
		return tableProducts;
	}

	public void setTableProducts(JTable tableProducts) {
		this.tableProducts = tableProducts;
	}

	public JTextField getTextNameProduct() {
		return textNameProduct;
	}

	public void setTextNameProduct(JTextField textNameProduct) {
		this.textNameProduct = textNameProduct;
	}

	public JTextField getTextPriceProduct() {
		return textPriceProduct;
	}

	public void setTextPriceProduct(JTextField textPriceProduct) {
		this.textPriceProduct = textPriceProduct;
	}

	public JTextField getTextQuantityProduct() {
		return textQuantityProduct;
	}

	public void setTextQuantityProduct(JTextField textQuantityProduct) {
		this.textQuantityProduct = textQuantityProduct;
	}

	public JTextField getTextQuantityOrders() {
		return textQuantityOrders;
	}

	public void setTextQuantityOrders(JTextField textQuantityOrders) {
		this.textQuantityOrders = textQuantityOrders;
	}

	public JTable getTableOrders() {
		return tableOrders;
	}

	public void setTableOrders(JTable tableOrders) {
		this.tableOrders = tableOrders;
	}

	public JButton getBtnAddClient() {
		return btnAddClient;
	}

	public void setBtnAddClient(JButton btnAddClient) {
		this.btnAddClient = btnAddClient;
	}

	public JComboBox getCbClientOrder() {
		return cbClientOrder;
	}

	public void setCbClientOrder(JComboBox cbClientOrder) {
		this.cbClientOrder = cbClientOrder;
	}

	public JButton getBtnAddOrder() {
		return btnAddOrder;
	}

	public void setBtnAddOrder(JButton btnAddOrder) {
		this.btnAddOrder = btnAddOrder;
	}

	public JButton getBtnAddStock() {
		return btnAddStock;
	}

	public void setBtnAddStock(JButton btnAddStock) {
		this.btnAddStock = btnAddStock;
	}

	public JSpinner getSpinnerAddQuantity() {
		return spinnerAddQuantity;
	}

	public void setSpinnerAddQuantity(JSpinner spinnerAddQuantity) {
		this.spinnerAddQuantity = spinnerAddQuantity;
	}

	public JComboBox getCbAddProduct() {
		return cbAddProduct;
	}

	public void setCbAddProduct(JComboBox cbAddProduct) {
		this.cbAddProduct = cbAddProduct;
	}

	public JButton getBtnDeleteProduct() {
		return btnDeleteProduct;
	}

	public void setBtnDeleteProduct(JButton btnDeleteProduct) {
		this.btnDeleteProduct = btnDeleteProduct;
	}

	public JComboBox getCbDeleteProduct() {
		return cbDeleteProduct;
	}

	public void setCbDeleteProduct(JComboBox cbDeleteProduct) {
		this.cbDeleteProduct = cbDeleteProduct;
	}

	public JButton getBtnAddProduct() {
		return btnAddProduct;
	}

	public void setBtnAddProduct(JButton btnAddProduct) {
		this.btnAddProduct = btnAddProduct;
	}

	public JButton getBtnDeleteClient() {
		return btnDeleteClient;
	}

	public void setBtnDeleteClient(JButton btnDeleteClient) {
		this.btnDeleteClient = btnDeleteClient;
	}

	public JComboBox getCbDeleteClient() {
		return cbDeleteClient;
	}

	public void setCbDeleteClient(JComboBox cbDeleteClient) {
		this.cbDeleteClient = cbDeleteClient;
	}

	public void actionAddClient(ActionListener l) {
		this.btnAddClient.addActionListener(l);
	}

	public void actionDeleteClient(ActionListener l) {
		this.btnDeleteClient.addActionListener(l);
	}

	public void actionAddProduct(ActionListener l) {
		this.btnAddProduct.addActionListener(l);
	}

	public void actionDeleteProduct(ActionListener l) {
		this.btnDeleteProduct.addActionListener(l);
	}

	public void actionAddStock(ActionListener l) {
		this.btnAddStock.addActionListener(l);
	}

	public void actionAddOrder(ActionListener l) {
		this.btnAddOrder.addActionListener(l);
	}

}

