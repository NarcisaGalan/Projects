


package ResurseUmane;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class OrarAngajatResurse extends JFrame implements ActionListener {

	private DefaultDBConnection connection;
	private int ID;
	private JPanel contentPane;
	private JTextField oraLuni;
	private JTextField oraMari;
	private JTextField oraMiercuri;
	private JTextField oraJoi;
	private JTextField oraVineri;
	private JTextField locLuni;
	private JTextField locMarti;
	private JTextField locMiercuri;
	private JTextField locJoi;
	private JTextField locVineri;
	private String nume;
	private String prenume;
	private String functie;
	
	CautareAngajatPentruInspector auxMetodeGet = new CautareAngajatPentruInspector(connection, ID);

	public OrarAngajatResurse(DefaultDBConnection connection,int id,String nume,String prenume,String functie) {
		this.nume = nume;
		this.prenume = prenume;
		this.functie = functie;
		this.connection=connection;
		this.ID=id;
		
		setBounds(100, 100, 458, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 434, 301);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblZi = new JLabel("ZI");
		lblZi.setBounds(52, 36, 46, 14);
		panel.add(lblZi);
		
		JLabel lblOra = new JLabel("Ora ");
		lblOra.setBounds(153, 36, 46, 14);
		panel.add(lblOra);
		
		JLabel lblLocatie = new JLabel("Locatie");
		lblLocatie.setBounds(287, 36, 46, 14);
		panel.add(lblLocatie);
		
		JLabel lblLuni = new JLabel("Luni");
		lblLuni.setBounds(26, 61, 72, 14);
		panel.add(lblLuni);
		
		JLabel lblMarti = new JLabel("Marti");
		lblMarti.setBounds(26, 86, 72, 14);
		panel.add(lblMarti);
		
		JLabel lblMiercuri = new JLabel("Miercuri");
		lblMiercuri.setBounds(26, 111, 72, 14);
		panel.add(lblMiercuri);
		
		JLabel lblJoi = new JLabel("Joi");
		lblJoi.setBounds(26, 136, 72, 14);
		panel.add(lblJoi);
		
		JLabel lblVineri = new JLabel("Vineri");
		lblVineri.setBounds(26, 161, 72, 14);
		panel.add(lblVineri);
		
		oraLuni = new JTextField();
		oraLuni.setBounds(113, 58, 126, 20);
		panel.add(oraLuni);
		oraLuni.setColumns(10);
		
		oraMari = new JTextField();
		oraMari.setBounds(113, 83, 126, 20);
		panel.add(oraMari);
		oraMari.setColumns(10);
		
		oraMiercuri = new JTextField();
		oraMiercuri.setBounds(113, 108, 126, 20);
		panel.add(oraMiercuri);
		oraMiercuri.setColumns(10);
		
		oraJoi = new JTextField();
		oraJoi.setBounds(113, 133, 126, 20);
		panel.add(oraJoi);
		oraJoi.setColumns(10);
		
		oraVineri = new JTextField();
		oraVineri.setBounds(113, 158, 126, 20);
		panel.add(oraVineri);
		oraVineri.setColumns(10);
		
		locLuni = new JTextField();
		locLuni.setBounds(261, 58, 86, 20);
		panel.add(locLuni);
		locLuni.setColumns(10);
		
		locMarti = new JTextField();
		locMarti.setBounds(261, 83, 86, 20);
		panel.add(locMarti);
		locMarti.setColumns(10);
		
		locMiercuri = new JTextField();
		locMiercuri.setBounds(261, 108, 86, 20);
		panel.add(locMiercuri);
		locMiercuri.setColumns(10);
		
		locJoi = new JTextField();
		locJoi.setBounds(261, 133, 86, 20);
		panel.add(locJoi);
		locJoi.setColumns(10);
		
		locVineri = new JTextField();
		locVineri.setBounds(261, 158, 86, 20);
		panel.add(locVineri);
		locVineri.setColumns(10);
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setBackground(Color.WHITE);
		btnInapoi.setForeground(new Color(0, 0, 153));
		btnInapoi.setFont(new Font("Arial", Font.BOLD, 11));
		btnInapoi.setBounds(181, 219, 89, 23);
		panel.add(btnInapoi);
		btnInapoi.addActionListener(this);
		
		
		
		// AM NEVOIE DE NUME , PRENUME SI FUNCTIE DIN CAUTARE ANGAJAT PT INSPECTOR
		List<String> listaZile=new ArrayList();
		listaZile.add("Luni");
		listaZile.add("Marti");
		listaZile.add("Miercuri");
		listaZile.add("Joi");
		listaZile.add("Vineri");
	
	
		try {
			Connection mainPageConnection = this.connection.getConnection();
			
			PreparedStatement stm=mainPageConnection.prepareStatement("call gasire_orar_angajat(?,?,?,?)");
			
					stm.setString(1,nume);
					stm.setString(2,prenume);
					stm.setString(3,functie);
					stm.setString(4,"luni");
					
					stm.execute();
					
					ResultSet rs = stm.getResultSet();
					ResultSetMetaData rsmd = rs.getMetaData();
					
				
					rs.next();
					this.oraLuni.setText(rs.getString("Ora_inceput")+"-"+rs.getString("Ora_inchidere"));
					this.locLuni.setText(rs.getString("Locatie"));
					
					stm.setString(4,"marti");
					
					stm.executeQuery();
					
					rs=stm.getResultSet();
					rsmd=rs.getMetaData();
					rs.next();
					this.oraMari.setText(rs.getString("Ora_inceput")+"-"+rs.getString("Ora_inchidere"));
					this.locMarti.setText(rs.getString("Locatie"));  
					
					
					stm.setString(4,"miercuri");
					
					stm.executeQuery();
					
					rs=stm.getResultSet();
					rsmd=rs.getMetaData();
					rs.next();
					this.oraMiercuri.setText(rs.getString("Ora_inceput")+"-"+rs.getString("Ora_inchidere"));
					this.locMiercuri.setText(rs.getString("Locatie"));  
					
					stm.executeQuery();
					
					stm.setString(4,"joi");
					
					stm.executeQuery();
					
					rs=stm.getResultSet();
					rsmd=rs.getMetaData();
					rs.next();
					this.oraJoi.setText(rs.getString("Ora_inceput")+"-"+rs.getString("Ora_inchidere"));
					this.locJoi.setText(rs.getString("Locatie"));  
					
					stm.executeQuery();
					
					stm.setString(4,"vineri");
					
					stm.executeQuery();
					
					rs=stm.getResultSet();
					rsmd=rs.getMetaData();
					rs.next();
					this.oraVineri.setText(rs.getString("Ora_inceput")+"-"+rs.getString("Ora_inchidere"));
					this.locVineri.setText(rs.getString("Locatie"));  
					
					JLabel lblOrarGenericAngajat = new JLabel("Orar generic  angajat");
					lblOrarGenericAngajat.setBounds(113, 11, 192, 14);
					panel.add(lblOrarGenericAngajat);
		
   
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if (action.getActionCommand().equals("Inapoi")) {

			this.setVisible(false);

		}

		
	}
}