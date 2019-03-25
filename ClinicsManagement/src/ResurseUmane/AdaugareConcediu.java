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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Main.DefaultDBConnection;

import java.awt.Canvas;
import javax.swing.JButton;

public class AdaugareConcediu extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textNume;
	private JButton btnAdaugareConcediu;
	private JButton btnAnulare;
	private DefaultDBConnection connection;
	private String Nume;
	private String Prenume;
	private String Functie;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	

	public AdaugareConcediu(DefaultDBConnection connection, int iD, String Nume, String Prenume, String Functie) {
		setTitle("Adaugare concediu");
		this.connection=connection;
		this.Nume=Nume;
		this.Prenume=Prenume;
		this.Functie=Functie;
		setBounds(100, 100, 335, 220);
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
		
		JLabel lblDataInceput = new JLabel("Data inceput");
		lblDataInceput.setBounds(10, 58, 77, 14);
		contentPane.add(lblDataInceput);
		
		JLabel lblDataFinal = new JLabel("Data final");
		lblDataFinal.setBounds(10, 83, 77, 14);
		contentPane.add(lblDataFinal);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(109, 58, 157, 20);
		contentPane.add(dateChooser);
		
		 dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(109, 83, 157, 20);
		contentPane.add(dateChooser_1);
		
		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(178, 147, 127, 23);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		 btnAdaugareConcediu = new JButton("Adaugare concediu");
		btnAdaugareConcediu.setBounds(25, 147, 143, 23);
		btnAdaugareConcediu.addActionListener(this);
		contentPane.add(btnAdaugareConcediu);
		
		this.textNume.setText(this.Nume+" "+this.Prenume);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}
		if(e.getActionCommand().equals("Adaugare concediu")) {
			try {
				Connection con=this.connection.getConnection();
				PreparedStatement stm=con.prepareStatement("call adaugare_concediu(?,?,?,?,?);");
				stm.setString(1, this.Nume);
				stm.setString(2, this.Prenume);
				stm.setString(3, this.Functie);
				
				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
				LocalDate date =LocalDate.parse(dt1.format(this.dateChooser.getDate()));
				LocalDate date1=LocalDate.parse(dt1.format(this.dateChooser_1.getDate()));
				stm.setString(4, date.toString());
				stm.setString(5, date1.toString());
				
				stm.execute();
				
				JOptionPane.showMessageDialog(this, "Adaugare efectuata!");
				
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, "Nu exista angajatul!");
				e1.printStackTrace();
			}
		}
	}
}
