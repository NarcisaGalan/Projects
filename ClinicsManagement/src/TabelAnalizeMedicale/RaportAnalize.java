package TabelAnalizeMedicale;

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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.Button;
import javax.swing.JButton;

public class RaportAnalize extends JFrame implements ActionListener {

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
	private JButton btnInregistrareRaport;
	private JDateChooser dateChooser;

	public RaportAnalize(DefaultDBConnection con, int idAsistent, Integer idProgramare, Integer idPacient) {
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
		
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(122, 75, 155, 20);
		contentPane.add(dateChooser);
		
		 btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(251, 216, 123, 23);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		 btnInregistrareRaport = new JButton("Inregistrare raport");
		btnInregistrareRaport.setBounds(57, 216, 143, 23);
		contentPane.add(btnInregistrareRaport);
		btnInregistrareRaport.addActionListener(this);
		
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
			
			
			PreparedStatement programari=con1.prepareStatement("select distinct Denumire from servicii,programari_servicii,programari\r\n" + 
					" where programari.idProgramare=programari_servicii.idProgramare \r\n" + 
					"	and servicii.idServiciu=programari_servicii.idServiciu and \r\n" + 
					"	programari.idProgramare=?;");
		
			programari.setInt(1, this.idProgramare);
			programari.executeQuery();
			rs=programari.getResultSet();
			rsmd=rs.getMetaData();
			rs.next();
			this.lblAnaliza1.setText(rs.getString("Denumire"));
			if(rs.next()) {
				this.lblAnaliza2.setText(rs.getString("Denumire"));
				this.textField_1.setVisible(true);
				this.lblAnaliza2.setVisible(true);
			}
			if(rs.next()) {
				this.lblAnaliza3.setText(rs.getString("Denumire"));
				this.lblAnaliza3.setVisible(true);
				this.textField_2.setVisible(true);
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
		if(e.getActionCommand().equals("Inregistrare raport")) {
			
			try {
				Connection con=this.connection.getConnection();
				PreparedStatement stm=con.prepareStatement("call policlinici.raport_analize(?, ?,\r\n" + 
						" ?, ?, ?, ?, ?);");
				System.out.println(this.idProgramare);
				stm.setInt(1, this.idProgramare);
				System.out.println(this.idAsistent);
				stm.setInt(2, this.idAsistent);
				System.out.println(this.idPacient);
				stm.setInt(3, this.idPacient);
				
				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
				LocalDate date =LocalDate.parse(dt1.format(this.dateChooser.getDate()));
				stm.setString(4, date.toString());
				stm.setString(5, this.textField.getText());
				if( this.textField_1.getText().equals("")) {
					stm.setString(6, null);
				}else {
				stm.setString(6, this.textField_1.getText());
				}
				if( this.textField_2.getText().equals("")) {
					stm.setString(7, null);
				}else {
				stm.setString(7, this.textField_2.getText());
				}
				stm.execute();
				
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, "Eroare!");
				e1.printStackTrace();
			}
			
		}
	}
	
}
