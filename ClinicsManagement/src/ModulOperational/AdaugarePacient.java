package ModulOperational;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import Main.PaginaPrincipala;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AdaugarePacient extends JFrame implements ActionListener{

	private JPanel contentPane;
	private DefaultDBConnection connection;
	private int ID;
	private JTextField textDataInscriere;
	private JTextField textNume;
	private JTextField textPrenume;
	private JTextField textDataNasterii;
	private JTextField textAdresa;
	private JTextField textTelefon;
	private JTextField textCNP;
	private JButton btnAnulare;
	private String current_date;
	private JButton btnAdaugarePacient;
	
	public AdaugarePacient(DefaultDBConnection connection,int id) {
		this.connection=connection;
		this.ID=id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDataInscriere = new JLabel("Data inscriere");
		lblDataInscriere.setBounds(109, 17, 82, 14);
		panel.add(lblDataInscriere);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setBounds(109, 38, 46, 14);
		panel.add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setBounds(109, 63, 46, 14);
		panel.add(lblPrenume);
		
		JLabel lblNewLabel = new JLabel("Data nasterii");
		lblNewLabel.setBounds(109, 88, 71, 14);
		panel.add(lblNewLabel);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setBounds(109, 113, 46, 14);
		panel.add(lblAdresa);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(109, 138, 46, 14);
		panel.add(lblTelefon);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(109, 163, 46, 14);
		panel.add(lblCnp);
		
		textDataInscriere = new JTextField();
		textDataInscriere.setEditable(false);
		textDataInscriere.setBounds(201, 11, 86, 20);
		panel.add(textDataInscriere);
		textDataInscriere.setColumns(10);
		
		Statement st;
		try {
			st = this.connection.getConnection().createStatement();
			st.executeQuery("select current_date");
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			rs=st.getResultSet();
			rsmd=rs.getMetaData();
			rs.next();
			current_date=rs.getString("current_date");
			this.textDataInscriere.setText(current_date);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		textNume = new JTextField();
		textNume.setBounds(201, 35, 86, 20);
		panel.add(textNume);
		textNume.setColumns(10);
		
		textPrenume = new JTextField();
		textPrenume.setBounds(201, 60, 86, 20);
		panel.add(textPrenume);
		textPrenume.setColumns(10);
		
		textDataNasterii = new JTextField();
		textDataNasterii.setBounds(201, 85, 86, 20);
		panel.add(textDataNasterii);
		textDataNasterii.setColumns(10);
		
		textAdresa = new JTextField();
		textAdresa.setBounds(201, 110, 86, 20);
		panel.add(textAdresa);
		textAdresa.setColumns(10);
		
		textTelefon = new JTextField();
		textTelefon.setBounds(201, 135, 86, 20);
		panel.add(textTelefon);
		textTelefon.setColumns(10);
		
		textCNP = new JTextField();
		textCNP.setBounds(201, 160, 86, 20);
		panel.add(textCNP);
		textCNP.setColumns(10);
		
		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(234, 208, 125, 42);
		panel.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		btnAdaugarePacient = new JButton("Adaugare pacient");
		btnAdaugarePacient.setBounds(79, 208, 125, 42);
		panel.add(btnAdaugarePacient);
		btnAdaugarePacient.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		
		if(action.getActionCommand().equals("Anulare")) {
			PaginaPrincipala backPage=new PaginaPrincipala(connection,ID);
			this.setVisible(false);
			backPage.setVisible(true);
		}
		
		if(action.getActionCommand().equals("Adaugare pacient")) {
			try {
				Connection con=this.connection.getConnection();
				PreparedStatement stm=con.prepareStatement("call adaugare_pacient(?,?,?,?,?,?,?)");
				stm.setString(1, current_date);
				stm.setString(2,textNume.getText());
				stm.setString(3,textPrenume.getText());
				stm.setString(4,textDataNasterii.getText());
				stm.setString(5,textAdresa.getText());
				stm.setString(6,textTelefon.getText());
				stm.setString(7,textCNP.getText());
				
				stm.executeQuery();
				JOptionPane.showMessageDialog(this, "Pacientul a fost adaugat cu succes!");
				PaginaPrincipala backPage=new PaginaPrincipala(connection,ID);
				this.setVisible(false);
				backPage.setVisible(true);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
