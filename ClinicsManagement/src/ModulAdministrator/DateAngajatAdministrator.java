package ModulAdministrator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Main.DefaultDBConnection;
import TabelProgramariMedicZiCurenta.ListaPacienti;
import TabelProgramariMedicZiCurenta.MyTableModel;
import TabelProgramariMedicZiCurenta.Pacient;
import TabelProgramariMedicZiCurenta.RaportMedicalNou;
import Models.ButtonColumn;
import ModulContabilitate.CautareMedic;
import ModulContabilitate.ContabilitatePoliclinica;
import ModulContabilitate.ProfitMedic;
import ModulContabilitate.ProfitSpecialitate;
import ModulContabilitate.ProfitUnitatiMedicale;
import ModulContabilitate.SalariuAngajat;
import ModulContabilitate.SalariuMedic;
import ModulOperational.AdaugarePacient;
import ModulOperational.AdaugareProgramare;
import ModulOperational.AdaugareServiciu;
import ModulOperational.CautarePlatiPacient;
import ModulOperational.StergereServiciu;
import ResurseUmane.CautareAngajatPentruInspector;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;

public class DateAngajatAdministrator extends JFrame implements ActionListener {

	private DefaultDBConnection connection;
	private JPanel contentPane;
	private JTextField textCNP;
	private JTextField textNume;
	private JTextField textPrenume;
	private JTextField textAdresa;
	private JTextField textTelefon;
	private JTextField textMail;
	private JTextField textIBAN;
	private JTextField textContract;
	private JTextField textFunctie;
	private JPanel panelMedici;
	private JButton btnDeautentificare;
	private int ID;
	private JButton btnSalarMedic;
	private JTextField textParafa;
	private JLabel lblCodParafa;
	private JTextField textGrad;
	private JLabel lblGradMedic;
	private JTextField textTitlu;
	private JLabel lblTitluStiintific;
	private JTextField textDidactic;
	private JLabel lblPostDidactic;
	private JTextField textProcent;
	private JLabel lblProcentSalar;
	private JButton btnInapoi;
	private JButton btnUpdate;
	private JTextField textSalariu;
	private JTextField textOre;
	private JTextField textDepartament;
	private JTextField textUnitate;
	private JButton btnStergeUtilizator;
	private JPanel panelAngajat;
	private JDateChooser dateChooser;

	public DateAngajatAdministrator(DefaultDBConnection connection, int ID) {
		this.ID = ID;

		this.connection = connection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelAngajat = new JPanel();
		panelAngajat.setBounds(0, 234, 436, 146);
		contentPane.add(panelAngajat);
		panelAngajat.setLayout(null);

		JLabel lblOreluna = new JLabel("Ore/luna");
		lblOreluna.setBounds(46, 93, 46, 14);
		panelAngajat.add(lblOreluna);

		textOre = new JTextField();
		textOre.setBounds(170, 90, 149, 20);
		panelAngajat.add(textOre);
		textOre.setColumns(10);

		JLabel lblUnitateMedicala = new JLabel("Unitate medicala");
		lblUnitateMedicala.setBounds(46, 41, 106, 14);
		panelAngajat.add(lblUnitateMedicala);

		textUnitate = new JTextField();
		textUnitate.setBounds(170, 38, 149, 20);
		panelAngajat.add(textUnitate);
		textUnitate.setColumns(10);

		JLabel lblNewLabel = new JLabel("Departament");
		lblNewLabel.setBounds(46, 14, 76, 14);
		panelAngajat.add(lblNewLabel);

		textDepartament = new JTextField();
		textDepartament.setBounds(170, 11, 149, 20);
		panelAngajat.add(textDepartament);
		textDepartament.setColumns(10);

		textSalariu = new JTextField();
		textSalariu.setBounds(170, 121, 149, 20);
		panelAngajat.add(textSalariu);
		textSalariu.setColumns(10);

		JLabel lblSalariu = new JLabel("Salariu");
		lblSalariu.setBounds(46, 118, 46, 14);
		panelAngajat.add(lblSalariu);

		textFunctie = new JTextField();
		textFunctie.setBounds(170, 65, 149, 20);
		panelAngajat.add(textFunctie);
		textFunctie.setColumns(20);

		JLabel label_8 = new JLabel("Functie");
		label_8.setBounds(46, 68, 76, 14);
		panelAngajat.add(label_8);

		JPanel panelUtilizator = new JPanel();
		panelUtilizator.setBounds(0, -1, 434, 233);
		contentPane.add(panelUtilizator);
		panelUtilizator.setLayout(null);

		JLabel label = new JLabel("CNP");
		label.setBounds(45, 11, 46, 14);
		panelUtilizator.add(label);

		JLabel label_1 = new JLabel("Nume");
		label_1.setBounds(45, 36, 46, 14);
		panelUtilizator.add(label_1);

		JLabel label_2 = new JLabel("Prenume");
		label_2.setBounds(45, 61, 46, 14);
		panelUtilizator.add(label_2);

		JLabel label_3 = new JLabel("Adresa");
		label_3.setBounds(45, 86, 46, 14);
		panelUtilizator.add(label_3);

		JLabel label_4 = new JLabel("Telefon");
		label_4.setBounds(45, 111, 46, 14);
		panelUtilizator.add(label_4);

		JLabel label_5 = new JLabel("Email");
		label_5.setBounds(45, 136, 46, 14);
		panelUtilizator.add(label_5);

		JLabel label_6 = new JLabel("Data angajare");
		label_6.setBounds(45, 187, 76, 14);
		panelUtilizator.add(label_6);

		JLabel label_7 = new JLabel("Numar contract");
		label_7.setBounds(45, 212, 76, 14);
		panelUtilizator.add(label_7);

		JLabel lblIban = new JLabel("IBAN");
		lblIban.setBounds(45, 162, 46, 14);
		panelUtilizator.add(lblIban);

		textCNP = new JTextField();
		textCNP.setBounds(169, 8, 149, 20);
		panelUtilizator.add(textCNP);
		textCNP.setColumns(20);

		textNume = new JTextField();
		textNume.setColumns(20);
		textNume.setBounds(169, 33, 149, 20);
		panelUtilizator.add(textNume);

		textPrenume = new JTextField();
		textPrenume.setColumns(20);
		textPrenume.setBounds(169, 58, 149, 20);
		panelUtilizator.add(textPrenume);

		textAdresa = new JTextField();
		textAdresa.setColumns(20);
		textAdresa.setBounds(169, 83, 149, 20);
		panelUtilizator.add(textAdresa);

		textTelefon = new JTextField();
		textTelefon.setColumns(20);
		textTelefon.setBounds(169, 108, 149, 20);
		panelUtilizator.add(textTelefon);

		textMail = new JTextField();
		textMail.setColumns(20);
		textMail.setBounds(169, 133, 149, 20);
		panelUtilizator.add(textMail);

		textIBAN = new JTextField();
		textIBAN.setColumns(20);
		textIBAN.setBounds(169, 159, 149, 20);
		panelUtilizator.add(textIBAN);

		textContract = new JTextField();
		textContract.setColumns(20);
		textContract.setBounds(169, 209, 149, 20);
		panelUtilizator.add(textContract);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(169, 185, 149, 20);
		panelUtilizator.add(dateChooser);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 549, 434, 56);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(302, 0, 110, 36);
		panel_1.add(btnInapoi);
		btnInapoi.addActionListener(this);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(31, 0, 110, 36);
		panel_1.add(btnUpdate);
		btnUpdate.addActionListener(this);

		btnStergeUtilizator = new JButton("Stergere utilizator");
		btnStergeUtilizator.setBounds(166, 0, 110, 36);
		panel_1.add(btnStergeUtilizator);
		btnStergeUtilizator.addActionListener(this);

		panelMedici = new JPanel();
		panelMedici.setBounds(0, 378, 434, 160);
		contentPane.add(panelMedici);
		panelMedici.setLayout(null);

		textParafa = new JTextField();
		textParafa.setColumns(20);
		textParafa.setBounds(170, 11, 149, 20);
		panelMedici.add(textParafa);

		lblCodParafa = new JLabel("Cod parafa");
		lblCodParafa.setBounds(46, 14, 99, 14);
		panelMedici.add(lblCodParafa);

		textGrad = new JTextField();
		textGrad.setColumns(20);
		textGrad.setBounds(170, 42, 149, 20);
		panelMedici.add(textGrad);

		lblGradMedic = new JLabel("Grad medic");
		lblGradMedic.setBounds(46, 45, 76, 14);
		panelMedici.add(lblGradMedic);

		textTitlu = new JTextField();
		textTitlu.setColumns(20);
		textTitlu.setBounds(170, 73, 149, 20);
		panelMedici.add(textTitlu);

		lblTitluStiintific = new JLabel("Titlu stiintific");
		lblTitluStiintific.setBounds(46, 76, 76, 14);
		panelMedici.add(lblTitluStiintific);

		textDidactic = new JTextField();
		textDidactic.setColumns(20);
		textDidactic.setBounds(170, 104, 149, 20);
		panelMedici.add(textDidactic);

		lblPostDidactic = new JLabel("Post didactic");
		lblPostDidactic.setBounds(46, 107, 76, 14);
		panelMedici.add(lblPostDidactic);

		textProcent = new JTextField();
		textProcent.setColumns(20);
		textProcent.setBounds(170, 135, 149, 20);
		panelMedici.add(textProcent);

		lblProcentSalar = new JLabel("Procent salar");
		lblProcentSalar.setBounds(46, 138, 76, 14);
		panelMedici.add(lblProcentSalar);
		panelMedici.setVisible(false);

		ResultSet rs = null;
		ResultSetMetaData rsmd = null;

		try {
			Connection mainPageConnection = this.connection.getConnection();
			Statement stm = mainPageConnection.createStatement();

			stm.execute("select utilizator.TipUtilizator from utilizator where idUtilizator=" + ID + ";");
			rs = stm.getResultSet();
			rsmd = rs.getMetaData();
			rs.next();

			if (rs.getString("utilizator.TipUtilizator").equals("Anagajat")) {

				try {

					stm.execute("call date_angajat(" + ID + ");");
					rs = stm.getResultSet();
					rsmd = rs.getMetaData();

					SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");

					while (rs.next()) {
						this.textCNP.setText(rs.getString("CNP"));
						this.textPrenume.setText(rs.getString("Prenume"));
						this.textIBAN.setText(rs.getString("IBAN"));
						this.textNume.setText(rs.getString("Nume"));
						this.textAdresa.setText(rs.getString("Adresa"));
						this.textContract.setText(rs.getString("Contract"));
						try {
							this.dateChooser.setDate((dt1.parse((rs.getString("DataAngajarii")))));
						} catch (ParseException e) {
							e.printStackTrace();
						}

						this.textFunctie.setText(rs.getString("angajat.functie"));
						this.textTelefon.setText(rs.getString("Telefon"));
						this.textMail.setText(rs.getString("Email"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				stm.execute("select angajat.Salariu,angajat.NumarOrePerLuna,"
						+ " departament.Nume,unitati_medicale.Denumire " + "from angajat,unitati_medicale,departament "
						+ "where angajat.Departament=departament.idDepartament"
						+ " and angajat.idUnitate=unitati_medicale.idUnitateMedicala and angajat.idAngajat=" + ID
						+ ";");

				rs = stm.getResultSet();
				rsmd = rs.getMetaData();

				while (rs.next()) {
					this.textSalariu.setText(rs.getString("angajat.Salariu"));
					this.textOre.setText(rs.getString("angajat.NumarOrePerLuna"));
					this.textDepartament.setText(rs.getString("departament.Nume"));
					this.textUnitate.setText(rs.getString("unitati_medicale.Denumire"));
				}

				if (this.textFunctie.getText().equals("medic")) {
					this.panelMedici.setVisible(true);
					try {
						Connection mainPageConnection1 = this.connection.getConnection();
						Statement stm1 = mainPageConnection1.createStatement();
						stm1.execute(
								"select Grad,CodParafa,TitluStiintific,PostDidactic,Procent from medic where idMedic="
										+ ID + ";");
						rs = stm1.getResultSet();
						rsmd = rs.getMetaData();

						while (rs.next()) {
							this.textProcent.setText(rs.getString("Procent"));
							this.textParafa.setText(rs.getString("CodParafa"));
							this.textGrad.setText(rs.getString("Grad"));
							this.textTitlu.setText(rs.getString("TitluStiintific"));
							this.textDidactic.setText(rs.getString("PostDidactic"));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			} else {
				stm.execute("select CNP,Nume,Prenume,Adresa,Telefon,\r\n" + "IBAN,DataAngajarii,Contract,Email\r\n"
						+ "from utilizator\r\n" + " where utilizator.idUtilizator=36  ;");

				rs = stm.getResultSet();
				rsmd = rs.getMetaData();

				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");

				while (rs.next()) {
					this.textCNP.setText(rs.getString("CNP"));
					this.textPrenume.setText(rs.getString("Prenume"));
					this.textIBAN.setText(rs.getString("IBAN"));
					this.textNume.setText(rs.getString("Nume"));
					this.textContract.setText(rs.getString("Contract"));
					this.textTelefon.setText(rs.getString("Telefon"));
					this.textMail.setText(rs.getString("Email"));
					try {
						this.dateChooser.setDate((dt1.parse((rs.getString("DataAngajarii")))));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				this.panelMedici.setVisible(false);
				this.panelAngajat.setVisible(false);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DefaultDBConnection getConnection() {
		return this.connection;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Stergere utilizator")) {
			try {
				if (JOptionPane.showConfirmDialog(this, "Sunteti sigur ca vreti sa stergeti utilizatorul?") == 0) {
					Connection con = this.connection.getConnection();
					PreparedStatement stm = con.prepareStatement("delete from utilizator where idUtilizator = ?");
					stm.setInt(1, this.ID);
					stm.execute();

					JOptionPane.showMessageDialog(this, "Stergere efectuata");

					this.setVisible(false);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if (arg0.getActionCommand().equals("Update")) {
			if (JOptionPane.showConfirmDialog(this, "Sunteti sigur ca vreti sa modificati datele?") == 0) {
				try {
					SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
					Connection con = this.connection.getConnection();
					PreparedStatement stm = con.prepareStatement("call policlinici.update_angajat(?,"
							+ " ?, ?, ?, ?, ?," + " ?, ?, ?, ?," + " ?, ?, ?, ?, ?, ?," + " ?, ?, ?, ?);");
					stm.setString(1, Integer.toString(ID));

					if (this.textFunctie.getText().equals("medic")) {
						System.out.println("ok!");
						stm.setString(2, this.textGrad.getText());
						stm.setString(3, this.textParafa.getText());
						stm.setString(4, this.textTitlu.getText());
						stm.setString(5, this.textDidactic.getText());
						stm.setString(6, this.textProcent.getText());

					} else {
						System.out.println("nu");
						stm.setString(2, "null");
						stm.setString(3, "0");
						stm.setString(4, "null");
						stm.setString(5, "null");
						stm.setString(6, "0");
					}
					if (this.panelAngajat.isVisible() == true) {
						stm.setString(7, this.textSalariu.getText());
						stm.setString(8, this.textOre.getText());
						stm.setString(9, this.textFunctie.getText());
						stm.setString(10, this.textDepartament.getText());
						stm.setString(11, this.textUnitate.getText());
					} else {
						System.out.println("administrator");
						stm.setString(7, "0");
						stm.setString(8, "0");
						stm.setString(9, "null");
						stm.setString(10, "null");
						stm.setString(11, "null");
						PreparedStatement stm1 = this.connection.getConnection()
								.prepareStatement("" + "update utilizator set CNP=?,Nume=?,Prenume=?,\r\n"
										+ "Adresa=?,Telefon=?,Email=?,IBAN=?,Contract=?,\r\n"
										+ "DataAngajarii=? where idUtilizator=?;");

						
						System.out.println(this.textNume.getText());
						System.out.println(this.textPrenume.getText());
						System.out.println(this.textAdresa.getText());
						System.out.println(this.textTelefon.getText());
						System.out.println(this.textMail.getText());
						System.out.println(this.textIBAN.getText());
						System.out.println(this.textContract.getText());
						System.out.println(dt1.format(this.dateChooser.getDate()));
						stm1.setString(1, this.textCNP.getText());
						stm1.setString(2, this.textNume.getText());
						stm1.setString(3, this.textPrenume.getText());
						stm1.setString(4, this.textAdresa.getText());
						stm1.setString(5, this.textTelefon.getText());
						stm1.setString(6, this.textMail.getText());
						stm1.setString(7, this.textIBAN.getText());
						stm1.setString(8, this.textContract.getText());
						stm1.setString(9, dt1.format(this.dateChooser.getDate()));
						stm1.setInt(10, this.ID);
						stm1.executeUpdate();
					}

					stm.setString(12, this.textCNP.getText());
					stm.setString(13, this.textNume.getText());
					stm.setString(14, this.textPrenume.getText());
					stm.setString(15, this.textAdresa.getText());
					stm.setString(16, this.textTelefon.getText());
					stm.setString(17, this.textMail.getText());
					stm.setString(18, this.textIBAN.getText());
					stm.setString(19, this.textContract.getText());
					stm.setString(20, dt1.format(this.dateChooser.getDate()));

					stm.execute();

					JOptionPane.showMessageDialog(this, "Modificare efectuata");

					DateAngajatAdministrator page = new DateAngajatAdministrator(this.connection, this.ID);
					this.setVisible(false);
					page.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
