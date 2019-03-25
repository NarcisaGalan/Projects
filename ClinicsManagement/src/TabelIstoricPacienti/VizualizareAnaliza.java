package TabelIstoricPacienti;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Main.DefaultDBConnection;

public class VizualizareAnaliza extends JFrame implements ActionListener {

	private JPanel contentPane;
	private DefaultDBConnection connection;
	private int idPacient;
	private int idAsistent;
	private int idProgramare;

	private Object textPrenumePacient;
	private Object textNumeMedic;
	private JTextField textNumePacient;
	private JTextField textNumeAsistent;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblAnaliza1;
	private JLabel lblAnaliza2;
	private JLabel lblAnaliza3;
	private JButton btnAnulare;
	private JTextField textData;

	public VizualizareAnaliza(DefaultDBConnection con, int idAsistent, Integer idProgramare, Integer idPacient) {
		this.connection=con;
		this.idProgramare=idProgramare;
		this.idPacient=idPacient;
		this.idAsistent=idAsistent;
		setBounds(100, 100, 450, 300);
		this.setTitle("Adaugare raport analiza");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumePacient = new JLabel("Nume pacient");
		lblNumePacient.setBounds(24, 27, 73, 14);
		contentPane.add(lblNumePacient);
		
		textNumePacient = new JTextField();
		textNumePacient.setBounds(122, 24, 155, 20);
		contentPane.add(textNumePacient);
		textNumePacient.setColumns(10);
		
		JLabel lblNumeAssistent = new JLabel("Nume assistent");
		lblNumeAssistent.setBounds(24, 52, 86, 14);
		contentPane.add(lblNumeAssistent);
		
		textNumeAsistent = new JTextField();
		textNumeAsistent.setBounds(122, 49, 155, 20);
		contentPane.add(textNumeAsistent);
		textNumeAsistent.setColumns(10);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(24, 77, 46, 14);
		contentPane.add(lblData);
		
		 lblAnaliza1 = new JLabel("New label");
		lblAnaliza1.setBounds(24, 128, 108, 14);
		contentPane.add(lblAnaliza1);
		
		textField = new JTextField();
		textField.setBounds(24, 153, 108, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		 lblAnaliza2 = new JLabel("New label");
		lblAnaliza2.setBounds(147, 128, 108, 14);
		contentPane.add(lblAnaliza2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(147, 153, 108, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		 lblAnaliza3 = new JLabel("New label");
		lblAnaliza3.setBounds(285, 128, 108, 14);
		contentPane.add(lblAnaliza3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(285, 153, 108, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		 btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(147, 227, 123, 23);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		textData = new JTextField();
		textData.setBounds(122, 74, 155, 20);
		contentPane.add(textData);
		textData.setColumns(10);
		
		this.lblAnaliza1.setVisible(true);
		this.lblAnaliza2.setVisible(false);
		this.lblAnaliza3.setVisible(false);
		this.textField_1.setVisible(false);
		this.textField_2.setVisible(false);
		try {
			Connection con1 = this.connection.getConnection();
			PreparedStatement pacienti = con1.prepareStatement("select Nume,Prenume from pacienti where idPacient=?;");
			pacienti.setInt(1, this.idPacient);
			pacienti.executeQuery();
			ResultSet rs = pacienti.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			rs.next();
			this.textNumePacient.setText(rs.getString("Nume")+" "+rs.getString("Prenume"));
	
			PreparedStatement medic = con1
					.prepareStatement("select Nume,Prenume from utilizator where idUtilizator=?;");
			medic.setInt(1, this.idAsistent);
			medic.executeQuery();
			rs = medic.getResultSet();
			rsmd = rs.getMetaData();
			rs.next();
			this.textNumeAsistent.setText(rs.getString("Nume")+" "+rs.getString("Prenume"));
			
			
			PreparedStatement programari=con1.prepareStatement("select servicii.denumire,raport_analize.data, raport_analize.rezultat\r\n" + 
					"from raport_analize,pacienti, \r\n" + 
					"utilizator,servicii,programari_servicii where \r\n" + 
					"raport_analize.idAsistent=utilizator.idUtilizator \r\n" + 
					"and raport_analize.idPacient=pacienti.idPacient \r\n" + 
					"and programari_servicii.idProgramare=raport_analize.idProgramare  \r\n" + 
					"and programari_servicii.idServiciu=servicii.idServiciu \r\n" + 
					"	and raport_analize.idRaport=?;");
			System.out.println(this.idProgramare);
			System.out.println(this.idProgramare);
			System.out.println(this.idProgramare);
			programari.setInt(1, this.idProgramare);
			programari.execute();
			rs=programari.getResultSet();
			rsmd=rs.getMetaData();
			
		
			if(rs.next()) {
			System.out.println(rs.getString("servicii.denumire"));
			this.lblAnaliza1.setText(rs.getString("servicii.denumire"));
			this.textData.setText(rs.getString("raport_analize.data"));
			this.textField.setText(rs.getString("raport_analize.rezultat"));
			this.setVisible(true);
			
			if(rs.next()) {
				this.lblAnaliza2.setText(rs.getString("servicii.denumire"));
				this.textField_1.setVisible(true);
				this.lblAnaliza2.setVisible(true);
				this.textField_1.setText(rs.getString("raport_analize.rezultat"));
			}
			if(rs.next()) {
				this.lblAnaliza3.setText(rs.getString("servicii.denumire"));
				this.lblAnaliza3.setVisible(true);
				this.textField_2.setVisible(true);
				this.textField_2.setText(rs.getString("raport_analize.rezultat"));
			}
			}else {
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "Nu exista analiza!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}
		
	}
}
