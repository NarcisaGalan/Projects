package ModulOperational;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AdaugareServiciu extends JFrame implements ActionListener{

	private JPanel contentPane;
	private int ID;
	private DefaultDBConnection connection;
	private JTextField textDenumire;
	private JTextField textSpecialitate;
	private JTextField textCompetenta;
	private JTextField textPret;
	private JTextField textDurata;
	private JButton btnAnulare;
	private JButton btnAdaugareServiciu;
	
	public AdaugareServiciu(DefaultDBConnection connection, int id) {
		setTitle("Adaugare serviciu");
		this.ID=id;
		this.connection=connection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDenumire = new JLabel("Denumire");
		lblDenumire.setBounds(47, 38, 46, 14);
		contentPane.add(lblDenumire);
		
		JLabel lblSpecialitateaDeCare = new JLabel("Specialitatea de care apartine");
		lblSpecialitateaDeCare.setBounds(47, 63, 151, 14);
		contentPane.add(lblSpecialitateaDeCare);
		
		JLabel lblNewLabel = new JLabel("Competenta necesara");
		lblNewLabel.setBounds(47, 88, 106, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPret = new JLabel("Pret");
		lblPret.setBounds(47, 113, 46, 14);
		contentPane.add(lblPret);
		
		JLabel lblDurataServiciului = new JLabel("Durata serviciului");
		lblDurataServiciului.setBounds(47, 138, 88, 14);
		contentPane.add(lblDurataServiciului);
		
		textDenumire = new JTextField();
		textDenumire.setBounds(208, 35, 86, 20);
		contentPane.add(textDenumire);
		textDenumire.setColumns(10);
		
		textSpecialitate = new JTextField();
		textSpecialitate.setBounds(208, 60, 86, 20);
		contentPane.add(textSpecialitate);
		textSpecialitate.setColumns(10);
		
		textCompetenta = new JTextField();
		textCompetenta.setBounds(208, 85, 86, 20);
		contentPane.add(textCompetenta);
		textCompetenta.setColumns(10);
		
		textPret = new JTextField();
		textPret.setText("");
		textPret.setBounds(208, 110, 86, 20);
		contentPane.add(textPret);
		textPret.setColumns(10);
		
		textDurata = new JTextField();
		textDurata.setBounds(208, 135, 86, 20);
		contentPane.add(textDurata);
		textDurata.setColumns(10);
		
		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(251, 196, 119, 40);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		btnAdaugareServiciu = new JButton("Adaugare serviciu");
		btnAdaugareServiciu.setBounds(79, 196, 119, 40);
		contentPane.add(btnAdaugareServiciu);
		btnAdaugareServiciu.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("Anulare")) {
			//PaginaPrincipala backPage=new PaginaPrincipala(connection,ID);
			this.setVisible(false);
			
		}
		
		if(arg0.getActionCommand().equals("Adaugare serviciu")) {
			System.out.println("DQW34");
			try {
				PreparedStatement stm=this.connection.getConnection().prepareStatement("call adaugare_serviciu(?,?,?,?,?,?);");
				stm.setString(1, textDenumire.getText());
				stm.setString(2, textSpecialitate.getText());
				stm.setString(3, textCompetenta.getText());
				stm.setString(4, textPret.getText());
				stm.setString(5, textDurata.getText());
				stm.setInt(6, this.ID);
				
				System.out.println(stm.toString());
				stm.executeQuery();
				
				JOptionPane.showMessageDialog(this, "Serviciul a fost adaugat cu succes!");
				//PaginaPrincipala backPage=new PaginaPrincipala(connection,ID);
				this.setVisible(false);
				
				//backPage.setVisible(true);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Datele au fost introduse incorect");
				e.printStackTrace();
			}
		}
	}
}
