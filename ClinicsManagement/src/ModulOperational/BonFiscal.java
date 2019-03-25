package ModulOperational;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class BonFiscal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textNume;
	private JTextField textData;
	private JTextField textField;
	private DefaultDBConnection connection;
	private int idPacient;
	private int idRaport;
	private JButton btnEmitere;
	private int id;

	
	public BonFiscal(DefaultDBConnection connection, int idPacient, int idRaport,int id) {
		this.connection = connection;
		this.idPacient = idPacient;
		this.idRaport = idRaport;
		this.id=id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNumePacient = new JLabel("Nume pacient");
		lblNumePacient.setBounds(43, 53, 65, 14);
		contentPane.add(lblNumePacient);

		JLabel lblBonFiscal = new JLabel("Bon fiscal");
		lblBonFiscal.setBounds(114, 11, 46, 14);
		contentPane.add(lblBonFiscal);

		textNume = new JTextField();
		textNume.setBounds(140, 50, 109, 20);
		contentPane.add(textNume);
		textNume.setColumns(10);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(43, 78, 46, 14);
		contentPane.add(lblData);

		textData = new JTextField();
		textData.setBounds(140, 75, 109, 20);
		contentPane.add(textData);
		textData.setColumns(10);

		JLabel lblServiciiEfectuate = new JLabel("Servicii efectuate");
		lblServiciiEfectuate.setBounds(43, 127, 83, 14);
		contentPane.add(lblServiciiEfectuate);

		JPanel panel = new JPanel();
		panel.setBounds(140, 132, 109, 46);
		contentPane.add(panel);
		panel.setLayout(null);

		JList list = new JList();
		list.setBounds(0, 0, 109, 46);
		panel.add(list);

		JLabel lblSuma = new JLabel("Suma ");
		lblSuma.setBounds(43, 102, 46, 14);
		contentPane.add(lblSuma);

		textField = new JTextField();
		textField.setBounds(140, 103, 109, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		btnEmitere = new JButton("Emitere");
		btnEmitere.setBounds(84, 215, 109, 35);
		btnEmitere.addActionListener(this);
		contentPane.add(btnEmitere);

		try {
			Connection con = this.connection.getConnection();
			Statement stm = con.createStatement();
			stm.execute("select Nume,Prenume from pacienti where idPacient=" + this.idPacient + ";");
			ResultSet rs = stm.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			rs.next();
			this.textNume.setText(rs.getString("Nume") + " " + rs.getString("Prenume"));
			
			LocalDate currentDate = LocalDate.now();
			this.textData.setText(currentDate.toString());

			stm.execute("select sum(servicii.Pret) from programari_servicii,servicii,raport_medical "
					+ "where raport_medical.idRaport=programari_servicii.idProgramare and "
					+ "programari_servicii.idServiciu=servicii.idServiciu and raport_medical.idRaport=" + this.idRaport
					+ ";");
			rs = stm.getResultSet();
			rsmd = rs.getMetaData();
			rs.next();
			this.textField.setText(rs.getString("sum(servicii.Pret)"));
			
			stm.execute("select servicii.Denumire from programari_servicii,servicii,raport_medical \r\n" + 
					"where raport_medical.idRaport=programari_servicii.idProgramare and \r\n" + 
					"programari_servicii.idServiciu=servicii.idServiciu and raport_medical.idRaport="+this.idRaport+";");
			rs = stm.getResultSet();
			rsmd = rs.getMetaData();
			DefaultListModel model = new DefaultListModel();
			while(rs.next()) {
				model.addElement(rs.getString("servicii.Denumire"));
		
			}
			list.setModel(model);
		
			PreparedStatement stm1 = connection.getConnection().prepareStatement(
					"update raport_medical " + "set Plata_efectuata=true where idRaport=?;");
			stm1.setInt(1, this.idRaport);
			stm1.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(this, "Bonul a fost eliberat!");
		CautarePlatiPacient page=new CautarePlatiPacient(this.connection,id);
		this.setVisible(false);
		page.setVisible(true);

	}
}
