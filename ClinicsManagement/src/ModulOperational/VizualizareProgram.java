package ModulOperational;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Main.DefaultDBConnection;

public class VizualizareProgram extends JFrame implements ActionListener {

	private JPanel contentPane;
	private DefaultDBConnection connection;
	private int id;
	private JTable table;
	
	public VizualizareProgram(DefaultDBConnection con,int id) {
		this.connection=con;
		this.id=id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nume pacient", "Serviciu"
			}
		));
		table.setBounds(109, 55, 1, 1);
		contentPane.add(table);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
