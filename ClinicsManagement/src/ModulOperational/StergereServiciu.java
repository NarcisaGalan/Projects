package ModulOperational;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JButton;

public class StergereServiciu extends JFrame implements ActionListener{

	private JPanel contentPane;
	private int ID;
	private DefaultDBConnection connection;
	private JButton btnAnulare;
	private JButton btnStergetiServiciul;
	private JComboBox comboBox;
	
	public StergereServiciu(DefaultDBConnection connection,int id) {
		this.connection=connection;
		this.ID=id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAlegetiServiciul = new JLabel("Alegeti serviciul");
		lblAlegetiServiciul.setBounds(65, 68, 74, 14);
		contentPane.add(lblAlegetiServiciul);
		
		comboBox = new JComboBox();
		comboBox.setBounds(174, 65, 161, 20);
		contentPane.add(comboBox);
		comboBox.removeAllItems();
		
		try {
			
			PreparedStatement stm = this.connection.getConnection()
					.prepareStatement("select Denumire from servicii;");
			
			stm.executeQuery();

			ResultSet rs=stm.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData(); 	
			
			while(rs.next()) {
				comboBox.addItem(rs.getString("Denumire"));
			}
			
			
		} catch (SQLException e) {
			e
			.printStackTrace();
		}
		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(220, 180, 132, 37);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		btnStergetiServiciul = new JButton("Stergeti serviciul");
		btnStergetiServiciul.setBounds(61, 180, 132, 37);
		contentPane.add(btnStergetiServiciul);
		btnStergetiServiciul.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}
		
		if(arg0.getActionCommand().equals("Stergeti serviciul")) {
			try {
				
				PreparedStatement stm = this.connection.getConnection()
						.prepareStatement("delete from servicii where Denumire=?;");
				stm.setString(1,comboBox.getSelectedItem().toString());
				System.out.println(stm.toString());
				stm.execute();
				comboBox.removeItem(comboBox.getSelectedItem().toString());
				this.repaint();
				SwingUtilities.updateComponentTreeUI(this);
				contentPane.revalidate();
				contentPane.repaint();

				JOptionPane.showMessageDialog(this, "Serviciul a fost sters cu succes!");

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Serviciul a fost nu a fost sters!");

				e.printStackTrace();
			}
		}
		
	}

}
