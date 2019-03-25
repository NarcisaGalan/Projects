package ModulContabilitate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ProfitSpecialitate extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultDBConnection connection;
	private int id;
	private JButton btnInapoi;

	
	public ProfitSpecialitate(DefaultDBConnection connection, int id) {
		this.connection = connection;
		this.id = id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 40, 358, 115);
		contentPane.add(scrollPane);

		table = new JTable();

		try {
			Statement stm = this.connection.getConnection().createStatement();
			stm.execute("select sum(servicii.pret),specialitate.Specialitate from  " + 
					" programari,programari_servicii,servicii, raport_medical,angajat,medic,specialitate,medici_specialitati" + 
					"  where " + 
					"programari.idProgramare = programari_servicii.idProgramare and" + 
					" programari_servicii.idServiciu = servicii.idServiciu and raport_medical.idRaport is not null " + 
					" and angajat.idAngajat=programari.idMedic and programari.idMedic=medic.idMedic" + 
					" and medic.idMedic=medici_specialitati.idMedic and medici_specialitati.idSpecialitate=specialitate.idSpecialitate" + 
					" group by specialitate.idSpecialitate;");
			ResultSet rs = stm.getResultSet();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		scrollPane.setViewportView(table);

		btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(163, 208, 89, 23);
		contentPane.add(btnInapoi);
		btnInapoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ProfitSpecialitate.this.setVisible(false);

			}

		});
	}

}
