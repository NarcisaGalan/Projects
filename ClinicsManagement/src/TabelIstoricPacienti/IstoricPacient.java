package TabelIstoricPacienti;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import TabelProgramariMedicZiCurenta.MyTableModel;

public class IstoricPacient extends JFrame implements ActionListener{

	private JPanel contentPane;
	private DefaultDBConnection connection;
	private int id;
	private JTable table = new JTable();
	private JButton btnAnulare;

	
	public IstoricPacient(DefaultDBConnection con,int id) {
		this.connection=con;
		this.id=id;
		this.setTitle("Istoric pacient");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);table.setBounds(45, 40, 600, 100);
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

	public void setTableModel(SecondTableModel myTableModel) {
		table.setModel(myTableModel);
	}

	public void addNewButtonActionListener(ActionListener al) {
		// button.addActionListener(al);
	}

	public JTable getTable() {
		return this.table;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}
		
	}
}
