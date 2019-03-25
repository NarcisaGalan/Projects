package Main;

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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import ModulContabilitate.VizualizareOrar;
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

public class PaginaPrincipala extends JFrame implements ActionListener {

	private DefaultDBConnection connection;
	private JPanel contentPane;
	private JTextField textCNP;
	private JTextField textNume;
	private JTextField textPrenume;
	private JTextField textAdresa;
	private JTextField textTelefon;
	private JTextField textMail;
	private JTextField textIBAN;
	private JTextField textData;
	private JTextField textContract;
	private JTextField textFunctie;
	private JPanel panelOperatii;
	private JPanel panelMedici;
	private JPanel panelReceptioner;
	private JPanel panelAsistent;
	private JPanel panelInspector;
	private JButton btnDeautentificare;
	private JButton btnAdaugareServiciu;
	private JButton btnStergereServiciu;
	private JButton btnAdaugarePacient;
	private JButton btnEmitereBonFiscal;
	private JButton btnOrar;
	private JButton btnProgramareNoua;
	private JButton btnAnalize;
	private int ID;
	private JButton btnOperatiiInspector;
	private JButton btnProfit;
	private JPanel panelContabilitate;
	private JButton btnSalar;
	private JButton btnSalarMedic;
	private JButton btnProfitSpecialitati;
	private JButton btnProfitUnitatiMedicale;
	private JButton btnProfitMedic;
	private JButton btnOrarP;
	private JButton btnConcediuP;
	private JButton btnOrarA;
	private JButton btnConcediuA;

	public PaginaPrincipala(DefaultDBConnection connection, int ID) {
		this.ID = ID;
		System.out.println(ID);
		this.connection = connection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, -1, 434, 322);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("CNP");
		label.setBounds(45, 11, 46, 14);
		panel.add(label);

		JLabel label_1 = new JLabel("Nume");
		label_1.setBounds(45, 33, 46, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Prenume");
		label_2.setBounds(45, 58, 46, 14);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Adresa");
		label_3.setBounds(45, 83, 46, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("Telefon");
		label_4.setBounds(45, 108, 46, 14);
		panel.add(label_4);

		JLabel label_5 = new JLabel("Email");
		label_5.setBounds(45, 133, 46, 14);
		panel.add(label_5);

		JLabel label_6 = new JLabel("Data angajare");
		label_6.setBounds(45, 187, 76, 14);
		panel.add(label_6);

		JLabel label_7 = new JLabel("Numar contract");
		label_7.setBounds(45, 212, 76, 14);
		panel.add(label_7);

		JLabel label_8 = new JLabel("Functie");
		label_8.setBounds(45, 237, 76, 14);
		panel.add(label_8);

		JLabel lblIban = new JLabel("IBAN");
		lblIban.setBounds(45, 162, 46, 14);
		panel.add(lblIban);

		textCNP = new JTextField();
		textCNP.setEditable(false);
		textCNP.setBounds(169, 8, 149, 20);
		panel.add(textCNP);
		textCNP.setColumns(20);

		textNume = new JTextField();
		textNume.setEditable(false);
		textNume.setColumns(20);
		textNume.setBounds(169, 30, 149, 20);
		panel.add(textNume);

		textPrenume = new JTextField();
		textPrenume.setEditable(false);
		textPrenume.setColumns(20);
		textPrenume.setBounds(169, 55, 149, 20);
		panel.add(textPrenume);

		textAdresa = new JTextField();
		textAdresa.setEditable(false);
		textAdresa.setColumns(20);
		textAdresa.setBounds(169, 80, 149, 20);
		panel.add(textAdresa);

		textTelefon = new JTextField();
		textTelefon.setEditable(false);
		textTelefon.setColumns(20);
		textTelefon.setBounds(169, 105, 149, 20);
		panel.add(textTelefon);

		textMail = new JTextField();
		textMail.setEditable(false);
		textMail.setColumns(20);
		textMail.setBounds(169, 130, 149, 20);
		panel.add(textMail);

		textIBAN = new JTextField();
		textIBAN.setEditable(false);
		textIBAN.setColumns(20);
		textIBAN.setBounds(169, 159, 149, 20);
		panel.add(textIBAN);

		textData = new JTextField();
		textData.setEditable(false);
		textData.setColumns(20);
		textData.setBounds(169, 184, 149, 20);
		panel.add(textData);

		textContract = new JTextField();
		textContract.setEditable(false);
		textContract.setColumns(20);
		textContract.setBounds(169, 209, 149, 20);
		panel.add(textContract);

		textFunctie = new JTextField();
		textFunctie.setEditable(false);
		textFunctie.setColumns(20);
		textFunctie.setBounds(169, 234, 149, 20);
		panel.add(textFunctie);

		btnOrarP = new JButton("Vizualizare orar personal");
		btnOrarP.setBounds(10, 288, 149, 23);
		panel.add(btnOrarP);

		btnConcediuP = new JButton("Vizualizare concediu personal");
		btnConcediuP.setBounds(195, 288, 171, 23);
		panel.add(btnConcediuP);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 429, 434, 32);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnDeautentificare = new JButton("Deautentificare");
		btnDeautentificare.setBounds(167, 0, 107, 23);
		panel_1.add(btnDeautentificare);
		btnDeautentificare.addActionListener(this);

		panelOperatii = new JPanel();
		panelOperatii.setBounds(0, 321, 434, 92);
		contentPane.add(panelOperatii);
		panelOperatii.setLayout(null);

		ResultSet rs = null;
		ResultSetMetaData rsmd = null;

		try {
			Connection mainPageConnection = this.connection.getConnection();
			Statement stm = mainPageConnection.createStatement();
			stm.execute("call date_angajat(" + ID + ");");
			rs = stm.getResultSet();
			rsmd = rs.getMetaData();

			while (rs.next()) {
				this.textCNP.setText(rs.getString("CNP"));
				this.textPrenume.setText(rs.getString("Prenume"));
				this.textIBAN.setText(rs.getString("IBAN"));
				this.textNume.setText(rs.getString("Nume"));
				this.textAdresa.setText(rs.getString("Adresa"));
				this.textContract.setText(rs.getString("Contract"));
				this.textData.setText(rs.getString("DataAngajarii"));
				this.textFunctie.setText(rs.getString("angajat.functie"));
				this.textTelefon.setText(rs.getString("Telefon"));
				this.textContract.setText(rs.getString("Contract"));
				this.textMail.setText(rs.getString("Email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		btnSalar = new JButton("Istoric Salar");
		btnSalar.setBounds(68, 34, 137, 23);
		panelOperatii.add(btnSalar);
		btnSalar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (PaginaPrincipala.this.textFunctie.getText().equals("medic")) {
					SalariuMedic salaryPage = new SalariuMedic(connection, ID);
					salaryPage.setVisible(true);
				} else {
					SalariuAngajat salaryPage = new SalariuAngajat(connection, ID);
					salaryPage.setVisible(true);
				}
			}
		});

		panelMedici = new JPanel();
		panelMedici.setBounds(-10, 0, 444, 92);
		panelMedici.setLayout(null);
		panelMedici.setVisible(false);

		btnOrar = new JButton("Vizualizare orar");
		btnOrar.setBounds(78, 0, 137, 23);
		panelMedici.add(btnOrar);
		btnOrar.addActionListener(this);

		btnAdaugareServiciu = new JButton("Adaugare serviciu");
		btnAdaugareServiciu.setBounds(248, 0, 137, 23);
		panelMedici.add(btnAdaugareServiciu);
		btnAdaugareServiciu.addActionListener(this);

		btnStergereServiciu = new JButton("Stergere serviciu");
		btnStergereServiciu.setBounds(248, 34, 137, 23);
		panelMedici.add(btnStergereServiciu);
		btnStergereServiciu.addActionListener(this);

		panelContabilitate = new JPanel();
		panelContabilitate.setBounds(0, 0, 434, 92);
		panelOperatii.add(panelContabilitate);
		panelContabilitate.setLayout(null);

		btnConcediuA = new JButton("Concediu Angajati");
		btnConcediuA.setBounds(66, 69, 137, 23);
		panelContabilitate.add(btnConcediuA);
		btnConcediuA.addActionListener(this);

		btnOrarA = new JButton("Orar Angajati");
		btnOrarA.setBounds(10, 0, 113, 23);
		panelContabilitate.add(btnOrarA);
		btnOrarA.addActionListener(this);

		btnProfit = new JButton("Profit policlinici");
		btnProfit.setBounds(210, 34, 137, 23);
		panelContabilitate.add(btnProfit);
		btnProfit.addActionListener(this);

		btnProfitMedic = new JButton("Profit medic");
		btnProfitMedic.setBounds(270, 0, 137, 23);
		btnProfitMedic.addActionListener(this);
		panelContabilitate.add(btnProfitMedic);

		btnProfitSpecialitati = new JButton("Profit specialitati");
		btnProfitSpecialitati.setBounds(133, 0, 137, 23);
		btnProfitSpecialitati.addActionListener(this);
		panelContabilitate.add(btnProfitSpecialitati);

		btnProfitUnitatiMedicale = new JButton("Profit unitati medicale");
		btnProfitUnitatiMedicale.setBounds(210, 68, 137, 24);
		panelContabilitate.add(btnProfitUnitatiMedicale);
		btnProfitUnitatiMedicale.addActionListener(this);

		panelAsistent = new JPanel();
		panelAsistent.setBounds(0, 0, 434, 92);
		panelOperatii.add(panelAsistent);
		panelAsistent.setLayout(null);
		panelAsistent.setVisible(false);

		btnAnalize = new JButton("Raport analize medicale");
		btnAnalize.setBounds(238, 34, 137, 23);
		panelAsistent.add(btnAnalize);

		panelReceptioner = new JPanel();
		panelReceptioner.setBounds(0, 0, 424, 92);
		panelOperatii.add(panelReceptioner);
		panelReceptioner.setLayout(null);
		panelReceptioner.setVisible(false);

		btnProgramareNoua = new JButton("Programare noua");
		btnProgramareNoua.setBounds(68, 0, 137, 23);
		panelReceptioner.add(btnProgramareNoua);
		btnProgramareNoua.addActionListener(this);

		btnAdaugarePacient = new JButton("Adaugare pacient");
		btnAdaugarePacient.setBounds(228, 0, 128, 23);
		panelReceptioner.add(btnAdaugarePacient);
		btnAdaugarePacient.addActionListener(this);

		btnEmitereBonFiscal = new JButton("Emitere bon fiscal");
		btnEmitereBonFiscal.setBounds(228, 34, 128, 23);
		panelReceptioner.add(btnEmitereBonFiscal);
		btnEmitereBonFiscal.addActionListener(this);

		panelOperatii.add(panelMedici);

		panelInspector = new JPanel();
		panelInspector.setBounds(0, 0, 434, 92);
		panelOperatii.add(panelInspector);
		panelInspector.setLayout(null);

		btnOperatiiInspector = new JButton("Cautare angajat");
		btnOperatiiInspector.setBounds(240, 34, 137, 23);
		panelInspector.add(btnOperatiiInspector);
		btnOperatiiInspector.addActionListener(this);

		setEmployeeType();

	}

	public DefaultDBConnection getConnection() {
		return this.connection;
	}

	public void setEmployeeType() {
		if (textFunctie.getText().equals("medic")) {
			this.panelInspector.setVisible(false);
			this.panelMedici.setVisible(true);
			this.panelAsistent.setVisible(false);
			this.panelReceptioner.setVisible(false);
			this.panelContabilitate.setVisible(false);
		} else {
			if (textFunctie.getText().equals("asistent medical")) {
				this.panelInspector.setVisible(false);
				this.panelAsistent.setVisible(true);
				this.panelReceptioner.setVisible(false);
				this.panelMedici.setVisible(false);
				this.panelContabilitate.setVisible(false);
			} else {
				if (textFunctie.getText().equals("receptioner")) {
					this.panelInspector.setVisible(false);
					this.panelMedici.setVisible(false);
					this.panelAsistent.setVisible(false);
					this.panelReceptioner.setVisible(true);
					this.panelContabilitate.setVisible(false);
				} else {
					if (textFunctie.getText().equals("inspector resurse umane")) {

						this.panelMedici.setVisible(false);
						this.panelAsistent.setVisible(false);
						this.panelReceptioner.setVisible(false);
						this.panelInspector.setVisible(true);
						this.panelContabilitate.setVisible(false);
					} else {
						if (textFunctie.getText().equals("contabil")) {
							this.panelMedici.setVisible(false);
							this.panelAsistent.setVisible(false);
							this.panelReceptioner.setVisible(false);
							this.panelInspector.setVisible(false);
							this.panelContabilitate.setVisible(true);
						}
					}
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Deautentificare")) {
			FirstPage backPage = new FirstPage(this.connection);
			this.setVisible(false);
			backPage.setVisible(true);
		}

		// Pentru receptioneri
		if (arg0.getActionCommand().equals("Adaugare pacient")) {
			this.setVisible(false);
			AdaugarePacient pacientPage = new AdaugarePacient(this.connection, this.ID);
			pacientPage.setVisible(true);
		}

		if (arg0.getActionCommand().equals("Programare noua")) {

			AdaugareProgramare pacientPage = new AdaugareProgramare(this.connection, this.ID);
			pacientPage.setVisible(true);
		}

		// Pentru medici

		if (arg0.getActionCommand().equals("Adaugare serviciu")) {
			AdaugareServiciu serviciuPagina = new AdaugareServiciu(this.connection, this.ID);
			serviciuPagina.setVisible(true);
		}

		if (arg0.getActionCommand().equals("Stergere serviciu")) {
			StergereServiciu serviciuPagina = new StergereServiciu(this.connection, this.ID);
			serviciuPagina.setVisible(true);
		}

		if (arg0.getActionCommand().equals("Emitere bon fiscal")) {
			CautarePlatiPacient platiPacient = new CautarePlatiPacient(this.connection, this.ID);
			platiPacient.setVisible(true);
		}

		if (arg0.getActionCommand().equals("Profit policlinici")) {
			ContabilitatePoliclinica contabilitatePage = new ContabilitatePoliclinica(this.connection, this.ID);
			contabilitatePage.setVisible(true);
		}

		if (arg0.getActionCommand().equals("Profit specialitati")) {
			ProfitSpecialitate contabilitatePage = new ProfitSpecialitate(this.connection, this.ID);
			contabilitatePage.setVisible(true);
		}

		if (arg0.getActionCommand().equals("Profit unitati medicale")) {
			ProfitUnitatiMedicale contabilitatePage = new ProfitUnitatiMedicale(this.connection, this.ID);
			contabilitatePage.setVisible(true);
		}

		if (arg0.getActionCommand().equals("Profit medic")) {
			CautareMedic contabilitatePage = new CautareMedic(this.connection, this.ID);
			contabilitatePage.setVisible(true);
		}

		if (arg0.getActionCommand().equals("Cautare angajat")) {
			CautareAngajatPentruInspector inspectorPage = new CautareAngajatPentruInspector(connection, ID);
			inspectorPage.setVisible(true);
		}

		if(arg0.getActionCommand().equals("Orar Angajati")) {
			VizualizareOrar orar=new VizualizareOrar(connection, ID);
			orar.setVisible(true);
		}
		if (arg0.getActionCommand().equals("Vizualizare orar")) {

			ListaPacienti pacientsPage = null;
			List<Pacient> pacienti = new ArrayList();

			TabelProgramariMedicZiCurenta.MyTableModel myTableModel = new TabelProgramariMedicZiCurenta.MyTableModel();

			try {
				PreparedStatement stm = this.connection.getConnection().prepareStatement(
						"select distinct programari.idProgramare,pacienti.idPacient,Nume,Prenume,OraProgramare "
								+ "from raport_medical,programari,pacienti,servicii,programari_servicii "
								+ "where programari.idPacient=pacienti.idPacient and servicii.idServiciu=programari_servicii.idServiciu and "
								+ "programari_servicii.idProgramare=programari.idProgramare and "
								+ "programari.idMedic=?;");
				stm.setInt(1, this.ID);

				stm.execute();
				ResultSet rs = stm.getResultSet();
				ResultSetMetaData rsmd = rs.getMetaData();
				List<Integer> listaIdProgramari = new ArrayList();
				List<Integer> listaIdPacienti = new ArrayList();
				List<Integer> listaIdMedicRec = new ArrayList();

				while (rs.next()) {

					PreparedStatement stm2 = this.connection.getConnection().prepareStatement(
							"select distinct Denumire from servicii,programari_servicii,programari,raport_medical "
									+ "where programari.idProgramare=programari_servicii.idProgramare "
									+ "and servicii.idServiciu=programari_servicii.idServiciu " + "and "
									+ "programari.idProgramare=?;");

					stm2.setString(1, rs.getString("programari.idProgramare"));
					listaIdProgramari.add(rs.getInt("programari.idProgramare"));
					System.out.println("Pacientii cu id: " + rs.getInt("pacienti.idPacient"));
					listaIdPacienti.add(rs.getInt("pacienti.idPacient"));

					stm2.execute();
					ResultSet rs2 = stm2.getResultSet();
					ResultSetMetaData rsmd2 = rs2.getMetaData();

					Pacient pacient = new Pacient(rs.getString("Nume") + " " + rs.getString("Prenume"),
							rs.getInt("idPacient"), rs.getString("OraProgramare"));

					while (rs2.next()) {
						pacient.addServiciu(rs2.getString("Denumire"));
					}
					pacienti.add(pacient);

				}

				myTableModel.setPacients(pacienti);
				pacientsPage = new ListaPacienti(this.connection, this.ID, listaIdPacienti);

				Action raportMedicalNou = new AbstractAction("Raport medical") {

					@Override
					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						int modelRow = Integer.valueOf(e.getActionCommand());
						DefaultDBConnection con = PaginaPrincipala.this.connection;
						RaportMedicalNou raportPage = new RaportMedicalNou(con, PaginaPrincipala.this.ID,
								listaIdProgramari.get(modelRow), listaIdPacienti.get(modelRow));
						// JOptionPane.showMessageDialog(contentPane,
						// "Du-te dracu! Ai accesat programarea " + listaIdProgramari.get(modelRow)+"
						// "+listaIdPacienti.get(modelRow));
						raportPage.setVisible(true);
					}
				};

				pacientsPage.setTableModel(myTableModel);
				ButtonColumn raport = new ButtonColumn(pacientsPage.getTable(), raportMedicalNou, 3);
				pacientsPage.setVisible(true);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
