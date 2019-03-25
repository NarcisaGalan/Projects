package TabelProgramariMedicZiCurenta;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import Main.PaginaPrincipala;
import Models.ButtonColumn;
import TabelIstoricPacienti.IstoricPacient;
import TabelIstoricPacienti.RaportInitial;
import TabelIstoricPacienti.RaportMedical;
import TabelIstoricPacienti.VizualizareAnaliza;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class ListaPacienti extends JFrame implements ActionListener {

	private JPanel contentPane;
	private static DefaultDBConnection connection;
	private int id;
	private JTable table = new JTable();
	private JButton btnAnulare;
	private List<Integer> pacients;

	public ListaPacienti(DefaultDBConnection connection, int id, List<Integer> pacients) {
		setTitle("Programari ziua curenta");
		this.connection = connection;
		this.id = id;
		this.pacients = pacients;
		this.setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		table.setBounds(45, 40, 600, 100);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(45, 40, 600, 168);
		jScrollPane.setViewportView(table);
		contentPane.add(jScrollPane);

		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(291, 227, 89, 23);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);

		table.addMouseListener(new MouseAdapter() {
			DefaultDBConnection connection = ListaPacienti.this.connection;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.rowAtPoint(arg0.getPoint());
				int col = table.columnAtPoint(arg0.getPoint());
				if (row >= 0 && col == 0) {

					IstoricPacient istoricPage = null;
					List<RaportInitial> raports = new ArrayList();

					TabelIstoricPacienti.SecondTableModel myTableModel = new TabelIstoricPacienti.SecondTableModel();

					try {
						PreparedStatement stm = this.connection.getConnection().prepareStatement(
								"select raport_medical.idRaport,raport_medical.Data,raport_medical.idMedic,raport_medical.idPacient "
								+ "from raport_medical where raport_medical.idPacient=?;");
						stm.setInt(1, ListaPacienti.this.pacients.get(row));
					
						stm.execute();
						ResultSet rs = stm.getResultSet();
						ResultSetMetaData rsmd = rs.getMetaData();
						raports.removeAll(raports);
						
						RaportInitial raport=null;

						while (rs.next()) {
							
							raport=new RaportInitial(rs.getString("raport_medical.Data"),rs.getInt("raport_medical.idRaport"),
									rs.getInt("raport_medical.idPacient"),rs.getInt("raport_medical.idMedic"));
							PreparedStatement stm2=this.connection.getConnection().prepareStatement("select servicii.denumire "
									+ "from raport_medical,pacienti, " 
									+ "utilizator,servicii,programari_servicii where "
									+ "raport_medical.idMedic=utilizator.idUtilizator " 
									+ "and raport_medical.idPacient=pacienti.idPacient "
									+ "and programari_servicii.idProgramare=raport_medical.idRaport " 
									+ "and programari_servicii.idServiciu=servicii.idServiciu "
									+ "and raport_medical.idRaport=?;");
							stm2.setInt(1, raport.getId());
							stm2.execute();
							ResultSet rs2=stm2.getResultSet();
							ResultSetMetaData rsmd2=rs2.getMetaData();
							
							while(rs2.next()) {
								
								raport.addServiciu(rs2.getString("servicii.denumire"));
							}
							System.out.println(raport.getDate());
							raports.add(raport);
						}
							//////////////////////////////////////////////
							 stm = this.connection.getConnection().prepareStatement(
									"select raport_analize.idRaport,raport_analize.data,raport_analize.idAsistent,raport_analize.idProgramare,\r\n" + 
									"raport_analize.idPacient\r\n" + 
									"from raport_analize where raport_analize.idPacient=?;");
							stm.setInt(1, ListaPacienti.this.pacients.get(row));
							stm.execute();
							 rs = stm.getResultSet();
							 rsmd = rs.getMetaData();
						
							 raport=null;

							while (rs.next()) {
								
								raport=new RaportInitial(rs.getString("raport_analize.data"),rs.getInt("raport_analize.idRaport"),
										rs.getInt("raport_analize.idPacient"),rs.getInt("raport_analize.idAsistent"));
								 PreparedStatement stm2 = this.connection.getConnection().prepareStatement("select servicii.denumire \r\n" + 
								 		"from raport_analize,pacienti, \r\n" + 
								 		"utilizator,servicii,programari_servicii where \r\n" + 
								 		"raport_analize.idAsistent=utilizator.idUtilizator \r\n" + 
								 		"and raport_analize.idPacient=pacienti.idPacient \r\n" + 
								 		"and programari_servicii.idProgramare=raport_analize.idProgramare  \r\n" + 
								 		"and programari_servicii.idServiciu=servicii.idServiciu \r\n" + 
								 		"	and raport_analize.idRaport=?;");
								stm2.setInt(1, raport.getId());
								stm2.execute();
								 ResultSet rs2 = stm2.getResultSet();
								 ResultSetMetaData rsmd2 = rs2.getMetaData();
								
								while(rs2.next()) {
									
									raport.addServiciu(rs2.getString("servicii.denumire"));
								}
								System.out.println(raport.getDate());
								raports.add(raport);
							}
							/////////////////////////////////////////
							
						
						
						myTableModel.setRaports(raports);
						istoricPage = new IstoricPacient(this.connection,ListaPacienti.this.pacients.get(row));
						

						Action vizualizeazaRaport = new AbstractAction("Raport medical") {

							@Override
							public void actionPerformed(ActionEvent e) {
								JTable table = (JTable) e.getSource();
								int modelRow = Integer.valueOf(e.getActionCommand());
								DefaultDBConnection con = ListaPacienti.this.connection;
								RaportMedical ra = new RaportMedical(con, raports.get(modelRow).getId(),
										raports.get(modelRow).getIdPacient(), raports.get(modelRow).getIdMedic());
								
								ra.setVisible(true);
							}
						};
						
						Action vizualizeazaAnaliza = new AbstractAction("Raport analiza") {

							@Override
							public void actionPerformed(ActionEvent e) {
								JTable table = (JTable) e.getSource();
								int modelRow = Integer.valueOf(e.getActionCommand());
								DefaultDBConnection con = ListaPacienti.this.connection;
								VizualizareAnaliza ra = new VizualizareAnaliza(con, 
										raports.get(modelRow).getIdMedic(), raports.get(modelRow).getId(),
										raports.get(modelRow).getIdPacient());
								
								
							}
						};
						istoricPage.setTableModel(myTableModel);
						ButtonColumn vizualizareRaport = new ButtonColumn(istoricPage.getTable(), vizualizeazaRaport, 2);
						ButtonColumn vizualizareAnaliza = new ButtonColumn(istoricPage.getTable(), vizualizeazaAnaliza, 3);
						istoricPage.setVisible(true);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

	}

	public void setTableModel(MyTableModel myTableModel) {
		table.setModel(myTableModel);
	}

	public void addNewButtonActionListener(ActionListener al) {
		// button.addActionListener(al);
	}

	public JTable getTable() {
		return this.table;
	}

	public DefaultDBConnection getConnection() {
		return this.connection;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}

	}
}
