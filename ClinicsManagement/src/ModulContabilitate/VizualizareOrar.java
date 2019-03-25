package ModulContabilitate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Main.DefaultDBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;

public class VizualizareOrar extends JFrame implements ActionListener{

	private DefaultDBConnection connection;
	private int id;

	private JPanel contentPane;
	private JTable table;

	
	public VizualizareOrar(DefaultDBConnection connection,int id) {
		this.connection=connection;
		this.id=id;
		
		
	
		setBounds(100, 100, 800, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(22, 22, 741, 204);
		contentPane.add(scrollPane);
		
		table = new JTable();
	
		
		for(int i=0;i<=table.getColumnCount()-1;i++) {
			TableColumn column=table.getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}
		scrollPane.setViewportView(table);
		
		JButton btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(191, 243, 89, 23);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);

		try {
			String query="call policlinici.vizualizare_orar_angajat();";
			PreparedStatement stm=connection.getConnection().prepareStatement(query);
			ResultSet rs=stm.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}
		
	}
}
