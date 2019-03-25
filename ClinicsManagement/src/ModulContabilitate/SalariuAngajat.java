package ModulContabilitate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class SalariuAngajat extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table = new JTable();
	private DefaultDBConnection connection;
	private int id;
	private int month;
	private JButton btnInapoi;
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


	public SalariuAngajat(DefaultDBConnection connection, int id) {
		this.connection = connection;
		this.id = id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(149, 212, 89, 23);
		contentPane.add(btnInapoi);
		btnInapoi.addActionListener(this);
		List<salariuAngajatModel> salarii = new ArrayList();

		try {

			int salar = 0;
			int i = 0;
			while (i < 6) {
				PreparedStatement stm = this.connection.getConnection()
						.prepareStatement("select policlinici.calculare_angajat(?,?) as salar from dual;");

				stm.setInt(1, this.id);
				stm.setInt(2, i);
				ResultSet rs = stm.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				rs.next();
				LocalDate localDate = LocalDate.now();

				if ((localDate.getMonthValue() - i) <= 0) {
					this.month = 12 + (localDate.getMonthValue() - i);
				} else {
					this.month = (localDate.getMonthValue() - i);
				}
				salariuAngajatModel salariu = new salariuAngajatModel(this.month, rs.getInt("salar"));
				salarii.add(salariu);

				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SalariuAngajatTableModel tableModel = new SalariuAngajatTableModel();
		tableModel.setSalarii(salarii);

		table.setModel(tableModel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 37, 368, 126);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(table);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Inapoi")) {
			this.setVisible(false);
		}
		
	}
}
