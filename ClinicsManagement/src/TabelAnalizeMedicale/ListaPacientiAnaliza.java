package TabelAnalizeMedicale;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import Main.PaginaPrincipala;
import Models.ButtonColumn;
import TabelIstoricPacienti.IstoricPacient;
import TabelIstoricPacienti.RaportInitial;
import TabelIstoricPacienti.RaportMedical;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class ListaPacientiAnaliza extends JFrame implements ActionListener {

	private JPanel contentPane;
	private static DefaultDBConnection connection;
	private int id;
	private JTable table = new JTable();
	private JButton btnAnulare;
	private List<Integer> pacients;

	public ListaPacientiAnaliza(DefaultDBConnection connection, int id, List<Integer> pacients) {
		setTitle("Programari ziua curenta");
		this.connection = connection;
		this.id = id;
		this.pacients = pacients;
		this.setSize(500, 500);


		table.setBounds(45, 40, 600, 100);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(45, 40, 600, 168);
		jScrollPane.setViewportView(table);
		contentPane.add(jScrollPane);

		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(291, 227, 89, 23);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);

		

	}

	public void setTableModel(FourthTableModel myTableModel) {
		table.setModel(myTableModel);
	}

	public void addNewButtonActionListener(ActionListener al) {
		// button.addActionListener(al);
	}

	public JTable getTable() {
		return this.table;
	}

	public DefaultDBConnection getConnection() {
		return this.connection;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}

	}
}
