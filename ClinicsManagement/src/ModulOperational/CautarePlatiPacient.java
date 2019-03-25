package ModulOperational;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import Models.ButtonColumn;
import TabelIstoricPacienti.IstoricPacient;
import TabelIstoricPacienti.ListaConsultatiiPacient;
import TabelIstoricPacienti.RaportInitial;
import TabelIstoricPacienti.RaportMedical;
import TabelProgramariMedicZiCurenta.ListaPacienti;
import TabelRapoarteBonFiscal.RapoarteBonFiscal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CautarePlatiPacient extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textCNP;
	private JButton btnAnulare;
	private JButton btnCautare;
	private DefaultDBConnection connection;
	private int id;
	ListaConsultatiiPacient consultatiiPage = null;
	private List<Integer> listaButoane = new ArrayList();
	private int idPacient;

	public CautarePlatiPacient(DefaultDBConnection connection, int id) {
		this.connection = connection;
		this.id = id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCnpPacient = new JLabel("CNP Pacient:");
		lblCnpPacient.setBounds(107, 90, 99, 14);
		contentPane.add(lblCnpPacient);

		textCNP = new JTextField();
		textCNP.setBounds(182, 87, 134, 20);
		contentPane.add(textCNP);
		textCNP.setColumns(10);

		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(256, 175, 117, 44);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);

		btnCautare = new JButton("Cautare");
		btnCautare.setBounds(72, 175, 117, 45);
		contentPane.add(btnCautare);
		btnCautare.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}

		if (e.getActionCommand().equals("Cautare")) {
			try {
				Connection con = this.connection.getConnection();
				Statement stm = con.createStatement();
				stm.execute("select idPacient from pacienti where CNP=" + this.textCNP.getText() + ";");
				ResultSet rs = stm.getResultSet();
				ResultSetMetaData rsmd = rs.getMetaData();
				rs.next();

				List<RapoarteBonFiscal> raports = new ArrayList();

				TabelRapoarteBonFiscal.ThirdTableModel myTableModel = new TabelRapoarteBonFiscal.ThirdTableModel();

				try {
					PreparedStatement stm1 = this.connection.getConnection().prepareStatement(
							"select raport_medical.idRaport,raport_medical.Data,raport_medical.idMedic,raport_medical.idPacient,"
									+ "raport_medical.Plata_efectuata "
									+ "from raport_medical where raport_medical.idPacient=?;");
					stm1.setString(1, rs.getString("idPacient"));

					this.idPacient = rs.getInt("idPacient");
					
					stm1.execute();
					ResultSet rs1 = stm1.getResultSet();
					ResultSetMetaData rsmd1 = rs1.getMetaData();
					raports.removeAll(raports);

					RapoarteBonFiscal raport = null;

					while (rs1.next()) {
						listaButoane.add(rs1.getInt("raport_medical.Plata_efectuata"));
						raport = new RapoarteBonFiscal(rs1.getString("raport_medical.Data"),
								rs1.getInt("raport_medical.idRaport"), rs1.getInt("raport_medical.idPacient"),
								rs1.getInt("raport_medical.idMedic"), rs1.getInt("raport_medical.Plata_Efectuata"));
						PreparedStatement stm2 = this.connection.getConnection()
								.prepareStatement("select servicii.denumire " + "from raport_medical,pacienti, "
										+ "utilizator,servicii,programari_servicii where "
										+ "raport_medical.idMedic=utilizator.idUtilizator "
										+ "and raport_medical.idPacient=pacienti.idPacient "
										+ "and programari_servicii.idProgramare=raport_medical.idRaport "
										+ "and programari_servicii.idServiciu=servicii.idServiciu "
										+ "and raport_medical.idRaport=?;");
						stm2.setInt(1, raport.getId());
						stm2.execute();
						ResultSet rs2 = stm2.getResultSet();
						ResultSetMetaData rsmd2 = rs2.getMetaData();

						while (rs2.next()) {

							raport.addServiciu(rs2.getString("servicii.denumire"));
						}
						System.out.println(raport.getDate());
						raports.add(raport);
					}

					myTableModel.setRaports(raports);
					consultatiiPage = new ListaConsultatiiPacient(this.connection, this.idPacient);

					Action vizualizeazaRaport = new AbstractAction("Emitere bon fiscal") {

						@Override
						public void actionPerformed(ActionEvent e) {

							JTable table = (JTable) e.getSource();
							int modelRow = Integer.valueOf(e.getActionCommand());
							BonFiscal page=new BonFiscal(connection, CautarePlatiPacient.this.idPacient, raports.get(modelRow).getId(),
									CautarePlatiPacient.this.id);
							page.setVisible(true);
							
							
							CautarePlatiPacient.this.consultatiiPage.validate();
							CautarePlatiPacient.this.consultatiiPage.repaint();

						}
					};

					consultatiiPage.setTableModel(myTableModel);
					ButtonColumn vizualizareRaport = new ButtonColumn(consultatiiPage.getTable(), vizualizeazaRaport,
							3);
					consultatiiPage.setVisible(true);
					int i = 0;
					while (i < listaButoane.size()) {
						if (listaButoane.get(i).equals(1)) {
							vizualizareRaport.getButton1().setEnabled(false);
							vizualizareRaport.getButton().setEnabled(false);
						}
						i++;
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		}

	}
}
