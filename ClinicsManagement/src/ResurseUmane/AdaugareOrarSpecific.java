package ResurseUmane;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Main.DefaultDBConnection;
import javax.swing.JComboBox;

public class AdaugareOrarSpecific extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textNume;
	private JButton btnAdaugareConcediu;
	private JButton btnAnulare;
	private DefaultDBConnection connection;
	private String Nume;
	private String Prenume;
	private String Functie;
	private JDateChooser dateChooser;
	private JTextField textLocatie;
	private JTextField textOraInceput;
	private JTextField textOraFinal;
	

	public AdaugareOrarSpecific(DefaultDBConnection connection, int iD, String Nume, String Prenume, String Functie) {
		setTitle("Adaugare orar specific");
		this.connection=connection;
		this.Nume=Nume;
		this.Prenume=Prenume;
		this.Functie=Functie;
		setBounds(100, 100, 335, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumeAngajat = new JLabel("Nume angajat");
		lblNumeAngajat.setBounds(10, 33, 89, 14);
		contentPane.add(lblNumeAngajat);
		
		textNume = new JTextField();
		textNume.setBounds(109, 30, 157, 20);
		contentPane.add(textNume);
		textNume.setColumns(10);
		
		JLabel lblDataInceput = new JLabel("Data");
		lblDataInceput.setBounds(10, 58, 77, 14);
		contentPane.add(lblDataInceput);
		
		JLabel lblDataFinal = new JLabel("Locatie");
		lblDataFinal.setBounds(10, 83, 77, 14);
		contentPane.add(lblDataFinal);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(109, 58, 157, 20);
		contentPane.add(dateChooser);
		
		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(182, 174, 127, 23);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		btnAdaugareConcediu = new JButton("Adaugare concediu");
		btnAdaugareConcediu.setBounds(22, 174, 143, 23);
		btnAdaugareConcediu.addActionListener(this);
		contentPane.add(btnAdaugareConcediu);
		
		this.textNume.setText(this.Nume+" "+this.Prenume);
		
		JLabel lblOraInceput = new JLabel("Ora inceput");
		lblOraInceput.setBounds(10, 108, 89, 14);
		contentPane.add(lblOraInceput);
		
		JLabel lblOraFinal = new JLabel("Ora final");
		lblOraFinal.setBounds(10, 133, 46, 14);
		contentPane.add(lblOraFinal);
		
		textLocatie = new JTextField();
		textLocatie.setBounds(109, 80, 157, 20);
		contentPane.add(textLocatie);
		textLocatie.setColumns(10);
		
		textOraInceput = new JTextField();
		textOraInceput.setBounds(109, 105, 157, 20);
		contentPane.add(textOraInceput);
		textOraInceput.setColumns(10);
		
		textOraFinal = new JTextField();
		textOraFinal.setBounds(109, 130, 157, 20);
		contentPane.add(textOraFinal);
		textOraFinal.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}
		if(e.getActionCommand().equals("Adaugare concediu")) {
			try {
				Connection con=this.connection.getConnection();
				PreparedStatement stm=con.prepareStatement("call adaugare_orar_specific(?,?,?,?,?,?,?);");
				stm.setString(1, this.Nume);
				stm.setString(2, this.Prenume);
				stm.setString(3, this.Functie);
				
				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
				LocalDate date =LocalDate.parse(dt1.format(this.dateChooser.getDate()));
				
				stm.setString(4, date.toString());
				stm.setString(5, this.textLocatie.getText());
				stm.setString(6, this.textOraInceput.getText());
				stm.setString(7,this.textOraFinal.getText());
				
				stm.execute();
				
				JOptionPane.showMessageDialog(this, "Adaugare efectuata!");
				
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, "Nu exista angajatul!");
				e1.printStackTrace();
			}
		}
	}
}
