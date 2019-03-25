package ResurseUmane;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class OrarSpecificAngajat extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textData1;
	private JTextField textOra1;
	private JTextField textLoc1;
	private JTextField textData2;
	private JTextField textData3;
	private JTextField textData4;
	private JTextField textOra2;
	private JTextField textOra3;
	private JTextField textOra4;
	private JTextField textLoc2;
	private JTextField textLoc3;
	private JTextField textLoc4;
	private DefaultDBConnection connection;
	private int ID;
	private String nume;
	private String prenume;
	private String functie;
	

	public OrarSpecificAngajat(DefaultDBConnection connection,int id,String nume,String prenume,String functie) {
		
		this.nume = nume;
		this.prenume = prenume;
		this.functie = functie;
		this.connection=connection;
		this.ID=id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblOrarSpecificAngajat = new JLabel("Orar specific angajat");
		lblOrarSpecificAngajat.setFont(new Font("Arial", Font.BOLD, 11));
		lblOrarSpecificAngajat.setBounds(145, 11, 137, 14);
		panel.add(lblOrarSpecificAngajat);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Arial", Font.BOLD, 11));
		lblData.setBounds(53, 36, 46, 14);
		panel.add(lblData);
		
		JLabel lblOra = new JLabel("Ora");
		lblOra.setFont(new Font("Arial", Font.BOLD, 11));
		lblOra.setBounds(201, 36, 46, 14);
		panel.add(lblOra);
		
		JLabel lblLocatie = new JLabel("Locatie");
		lblLocatie.setFont(new Font("Arial", Font.BOLD, 11));
		lblLocatie.setBounds(346, 36, 46, 14);
		panel.add(lblLocatie);
		
		textData1 = new JTextField();
		textData1.setBounds(10, 64, 126, 20);
		panel.add(textData1);
		textData1.setColumns(10);
		
		textOra1 = new JTextField();
		textOra1.setBounds(165, 64, 117, 20);
		panel.add(textOra1);
		textOra1.setColumns(10);
		
		textLoc1 = new JTextField();
		textLoc1.setBounds(322, 64, 92, 20);
		panel.add(textLoc1);
		textLoc1.setColumns(10);
		
		textData2 = new JTextField();
		textData2.setBounds(10, 95, 126, 20);
		panel.add(textData2);
		textData2.setColumns(10);
		
		textData3 = new JTextField();
		textData3.setBounds(10, 130, 126, 20);
		panel.add(textData3);
		textData3.setColumns(10);
		
		textData4 = new JTextField();
		textData4.setBounds(10, 161, 126, 20);
		panel.add(textData4);
		textData4.setColumns(10);
		
		textOra2 = new JTextField();
		textOra2.setBounds(165, 99, 117, 20);
		panel.add(textOra2);
		textOra2.setColumns(10);
		
		textOra3 = new JTextField();
		textOra3.setBounds(165, 130, 117, 20);
		panel.add(textOra3);
		textOra3.setColumns(10);
		
		textOra4 = new JTextField();
		textOra4.setBounds(165, 161, 117, 20);
		panel.add(textOra4);
		textOra4.setColumns(10);
		
		textLoc2 = new JTextField();
		textLoc2.setBounds(322, 95, 92, 20);
		panel.add(textLoc2);
		textLoc2.setColumns(10);
		
		textLoc3 = new JTextField();
		textLoc3.setBounds(322, 130, 92, 20);
		panel.add(textLoc3);
		textLoc3.setColumns(10);
		
		textLoc4 = new JTextField();
		textLoc4.setBounds(322, 161, 92, 20);
		panel.add(textLoc4);
		textLoc4.setColumns(10);
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.addActionListener(this);
			
		btnInapoi.setFont(new Font("Arial", Font.BOLD, 11));
		btnInapoi.setForeground(new Color(0, 0, 153));
		btnInapoi.setBounds(181, 202, 89, 23);
		panel.add(btnInapoi);
		
		
		try {
			Connection mainPageConnection = this.connection.getConnection();
			
			PreparedStatement stm=mainPageConnection.prepareStatement("call orar_specific(?,?,?)");
			
			stm.setString(1,nume);
			stm.setString(2,prenume);
			stm.setString(3,functie);
			
			stm.execute();
			
			ResultSet rs = stm.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			
		
			rs.next();
			this.textData1.setText(rs.getString("data_specifica"));
			this.textOra1.setText(rs.getString("Ora_inceput")+"-"+rs.getString("Ora_inchidere"));
			this.textLoc1.setText(rs.getString("Locatie"));
			
			
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent action) {
		if (action.getActionCommand().equals("Inapoi")) {

			this.setVisible(false);

		}

		
	}
}
