package ModulOperational;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import Main.PaginaPrincipala;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;

public class AdaugareProgramare extends JFrame implements ActionListener, ItemListener {

	private JPanel contentPane;
	private DefaultDBConnection connection;
	private int doctorCNP;
	private JTextField textCNP;
	private JTextField textOra;
	private JComboBox comboBox_Medici;
	private JComboBox comboBox_Servicii;
	private JButton btnAnulare;
	private JButton btnAdaugareProgramare;
	private int ID;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JButton btnAdaugareServicii;
	private JDateChooser dateChooser;

	public AdaugareProgramare(DefaultDBConnection connection, int ID) {
		this.ID = ID;
		this.connection = connection;
		setBounds(100, 100, 450, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCnpPacient = new JLabel("CNP Pacient");
		lblCnpPacient.setBounds(55, 36, 70, 14);
		contentPane.add(lblCnpPacient);

		JLabel lblMedic = new JLabel("Medic");
		lblMedic.setBounds(55, 61, 46, 14);
		contentPane.add(lblMedic);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(55, 86, 46, 14);
		contentPane.add(lblData);

		JLabel lblOra = new JLabel("Ora");
		lblOra.setBounds(55, 111, 46, 14);
		contentPane.add(lblOra);

		JLabel lblServiciu = new JLabel("Serviciu");
		lblServiciu.setBounds(55, 136, 46, 14);
		contentPane.add(lblServiciu);

		textCNP = new JTextField();
		textCNP.setBounds(130, 33, 110, 20);
		contentPane.add(textCNP);
		textCNP.setColumns(10);

		textOra = new JTextField();
		textOra.setBounds(130, 108, 110, 20);
		contentPane.add(textOra);
		textOra.setColumns(10);
		

		comboBox = new JComboBox();
		comboBox.setBounds(130, 158, 110, 20);
		contentPane.add(comboBox);
		
		comboBox.addItem("null");
		comboBox.setVisible(false);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(130, 183, 110, 20);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("null");
	
		comboBox_1.setVisible(false);

		comboBox_Servicii = new JComboBox();
	
		comboBox_Servicii.setBounds(130, 133, 110, 20);
		contentPane.add(comboBox_Servicii);
		comboBox_Servicii.removeAllItems();


		comboBox_Medici = new JComboBox();
		comboBox_Medici.setBounds(130, 58, 110, 20);
		contentPane.add(comboBox_Medici);
		comboBox_Medici.addItemListener(this);
		comboBox_Medici.removeAllItems();

		try {
			Statement stm = this.connection.getConnection().createStatement();
			stm.executeQuery(
					"select nume,prenume from utilizator,angajat where angajat.Functie='medic'  and angajat.idAngajat=utilizator.idUtilizator;");
			ResultSet rs = stm.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			String Nume, Prenume;
			while (rs.next()) {
				Nume = rs.getString("nume");
				Prenume = rs.getString("prenume");
				comboBox_Medici.addItem(Nume + " " + Prenume);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			System.out.println("Pana aici merge!");
			comboBox_Servicii.removeAllItems();
			PreparedStatement stm = this.connection.getConnection()
					.prepareStatement("select competente.Denumire from medic,medici_competente,competente "
							+ "where medic.idMedic=medici_competente.idMedic and "
							+ "medici_competente.idCompetenta=competente.idCompetenta " + "and medic.idMedic=?;");

			String[] nume = comboBox_Medici.getSelectedItem().toString().split(" ");

			stm.setInt(1, getDoctorsId(nume[0], nume[1]));

			stm.executeQuery();

			ResultSet rs = stm.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				comboBox_Servicii.addItem(rs.getString("competente.Denumire"));
				comboBox.addItem(rs.getString("competente.Denumire"));
				comboBox_1.addItem(rs.getString("competente.Denumire"));
			}

			contentPane.revalidate();
			contentPane.repaint();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(240, 238, 139, 30);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);

		btnAdaugareProgramare = new JButton("Adaugare programare");
		btnAdaugareProgramare.setBounds(55, 238, 139, 30);
		contentPane.add(btnAdaugareProgramare);

		btnAdaugareServicii = new JButton("Adaugare servicii");
		btnAdaugareServicii.setBounds(296, 132, 128, 23);
		contentPane.add(btnAdaugareServicii);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(130, 83, 110, 20);
		contentPane.add(dateChooser);
		
		
		
		btnAdaugareProgramare.addActionListener(this);
		btnAdaugareServicii.addActionListener(this);
	}

	public int getDoctorsId(String nume, String prenume) {
		try {
			PreparedStatement prstm = this.connection.getConnection()
					.prepareStatement("select idUtilizator,CNP from utilizator where Nume=? and Prenume=?;");
			// prstm.setString(1, nume);
			prstm.setString(2, prenume);
			prstm.setString(1, nume);
			prstm.executeQuery();
			ResultSet rs = prstm.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			rs.next();
			this.doctorCNP = rs.getInt("CNP");
			return rs.getInt("idUtilizator");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Anulare")) {
			PaginaPrincipala backPage = new PaginaPrincipala(this.connection, ID);
			backPage.setVisible(true);
			this.setVisible(false);
		}

		if (arg0.getActionCommand().equals("Adaugare servicii")) {
			if (this.comboBox_1.isVisible()) {
				JOptionPane.showMessageDialog(this, "Nu mai pot fi adaugate alte servicii!");
			} else {
				if (this.comboBox.isVisible()) {
					this.comboBox_1.setVisible(true);
				}
				this.comboBox.setVisible(true);
			}
		}
		if (arg0.getActionCommand().equals("Adaugare programare")) {
			try {
				LocalDate currentDate = LocalDate.now();
				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
				LocalDate date =LocalDate.parse(dt1.format(this.dateChooser.getDate()));
				if (currentDate.isBefore(date)) {
					PreparedStatement stm = this.connection.getConnection()
							.prepareStatement("call adaugare_programare(?,?,?,?,?,?,?);");
					stm.setString(1, textCNP.getText());
					stm.setInt(2, this.doctorCNP);
					stm.setString(3, comboBox_Servicii.getSelectedItem().toString());
					stm.setString(4, comboBox.getSelectedItem().toString());
					stm.setString(5, comboBox.getSelectedItem().toString());
					stm.setString(6, dt1.format(this.dateChooser.getDate()));
					stm.setString(7, textOra.getText());

					stm.executeQuery();
					JOptionPane.showMessageDialog(this, "Programarea a fost adaugata cu succes!");
					PaginaPrincipala backPage = new PaginaPrincipala(connection, ID);
					this.setVisible(false);
					backPage.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Introduceti o data ulterioara datei curente");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			
		}

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {

		try {
			System.out.println("Pana aici merge!");
			comboBox_Servicii.removeAllItems();
			comboBox.removeAllItems();
			comboBox.addItem("null");
			comboBox_1.removeAllItems();
			comboBox_1.addItem("null");
			PreparedStatement stm = this.connection.getConnection()
					.prepareStatement("select competente.Denumire from medic,medici_competente,competente "
							+ "where medic.idMedic=medici_competente.idMedic and "
							+ "medici_competente.idCompetenta=competente.idCompetenta " + "and medic.idMedic=?;");

			String[] nume = comboBox_Medici.getSelectedItem().toString().split(" ");
			System.out.println(nume[0]+" "+nume[1]);
			stm.setInt(1, getDoctorsId(nume[0], nume[1]));

			stm.executeQuery();

			ResultSet rs = stm.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				comboBox_Servicii.addItem(rs.getString("competente.Denumire"));
				comboBox_1.addItem(rs.getString("competente.Denumire"));
				comboBox.addItem(rs.getString("competente.Denumire"));
			}

			contentPane.revalidate();
			contentPane.repaint();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
