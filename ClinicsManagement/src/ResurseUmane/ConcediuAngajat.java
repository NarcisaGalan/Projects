
package ResurseUmane;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConcediuAngajat extends JFrame implements ActionListener {

	private DefaultDBConnection connection;
	private int ID;
	private JPanel contentPane;
	private JTextField dataInceput;
	private JTextField dataFinal;
	private JTextField durataConcediu;
	private String nume;
	private String prenume;
	private String functie;

	CautareAngajatPentruInspector auxMetodeGet = new CautareAngajatPentruInspector(connection, ID);

	public ConcediuAngajat(DefaultDBConnection connection, int id, String nume, String prenume, String functie) {
		this.nume = nume;
		this.prenume = prenume;
		this.functie = functie;
		this.connection = connection;
		this.ID = id;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelConcediu = new JPanel();
		panelConcediu.setBackground(new Color(153, 204, 255));
		contentPane.add(panelConcediu, BorderLayout.CENTER);
		panelConcediu.setLayout(null);

		JLabel lblConcediuAngajat = new JLabel("Concediu Angajat");
		lblConcediuAngajat.setBounds(140, 11, 108, 14);
		panelConcediu.add(lblConcediuAngajat);

		dataInceput = new JTextField();
		dataInceput.setBounds(140, 49, 156, 20);
		panelConcediu.add(dataInceput);
		dataInceput.setColumns(10);

		JLabel lblDataInceput = new JLabel("Data inceput");
		lblDataInceput.setBounds(44, 52, 86, 14);
		panelConcediu.add(lblDataInceput);

		JLabel lblDataFinal = new JLabel("Data final");
		lblDataFinal.setBounds(44, 91, 86, 14);
		panelConcediu.add(lblDataFinal);

		dataFinal = new JTextField();
		dataFinal.setBounds(140, 88, 156, 20);
		panelConcediu.add(dataFinal);
		dataFinal.setColumns(10);

		JLabel lblDurata = new JLabel("Durata");
		lblDurata.setBounds(44, 132, 86, 14);
		panelConcediu.add(lblDurata);

		durataConcediu = new JTextField();
		durataConcediu.setBounds(140, 129, 86, 20);
		panelConcediu.add(durataConcediu);
		durataConcediu.setColumns(10);

		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setBackground(Color.WHITE);
		btnInapoi.setForeground(new Color(0, 0, 153));
		btnInapoi.setFont(new Font("Arial", Font.BOLD, 11));
		btnInapoi.setBounds(137, 185, 89, 23);
		panelConcediu.add(btnInapoi);
		btnInapoi.addActionListener(this);
		
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		// AM NEVOIE DE NUME , PRENUME SI FUNCTIE DIN CAUTARE ANGAJAT PT INSPECTOR
		try {
			Connection mainPageConnection = this.connection.getConnection();
			PreparedStatement stm = mainPageConnection.prepareStatement("call gasire_concediu(?,?,?);");
			System.out.println(this.nume + " - " + this.prenume + " - " + this.functie);
			stm.setString(1, this.nume);
			stm.setString(2, this.prenume);
			stm.setString(3, this.functie);

			stm.executeQuery();
			rs = stm.getResultSet();
			rsmd = rs.getMetaData();

			while (rs.next()) {
				this.dataInceput.setText(rs.getString("data_inceput"));
				this.dataFinal.setText(rs.getString("data_final"));
				this.durataConcediu.setText(rs.getString("durata"));

			}
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