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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

public class RaportMedical extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textNumePacient;
	private JTextField textPrenumePacient;
	private JTextField textNumeMedic;
	private JTextField textPrenumeMedic;
	private JTextField textNumeAsistent;
	private JTextField textPrenumeAsistent;
	private JTextField textData;
	private JTextField textMedicRecomandare;
	private JTextArea textRecomandari;
	private JTextArea textDiagnostic;
	private JTextArea textIstoric;
	private JTextArea textInvestigatii;
	private JButton btnAnulare;
	private DefaultDBConnection connection;
	private int idMedic;
	private int idPacient;
	private int idRaport;
	private JTextArea textArea;

	public RaportMedical(DefaultDBConnection con, int idRaport,int idPacient,int idMedic) {
		this.connection = con;
		this.idRaport = idRaport;
		this.idPacient=idPacient;
		this.idMedic=idMedic;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNumePacient = new JLabel("Nume pacient*");
		lblNumePacient.setBounds(29, 11, 84, 14);
		contentPane.add(lblNumePacient);

		JLabel lblPrenumePacient = new JLabel("Prenume pacient*");
		lblPrenumePacient.setBounds(29, 32, 103, 14);
		contentPane.add(lblPrenumePacient);

		JLabel lblNumeMedic = new JLabel("Nume medic*");
		lblNumeMedic.setBounds(29, 57, 84, 14);
		contentPane.add(lblNumeMedic);

		JLabel lblPrenumeMedic = new JLabel("Prenume medic*");
		lblPrenumeMedic.setBounds(29, 82, 84, 14);
		contentPane.add(lblPrenumeMedic);

		JLabel lblMedicRecomandare = new JLabel("Medic recomandare");
		lblMedicRecomandare.setBounds(29, 107, 103, 14);
		contentPane.add(lblMedicRecomandare);

		JLabel lblNumeAsistent = new JLabel("Nume asistent");
		lblNumeAsistent.setBounds(28, 132, 85, 14);
		contentPane.add(lblNumeAsistent);

		JLabel lblPrenumeAsistent = new JLabel("Prenume asistent");
		lblPrenumeAsistent.setBounds(29, 157, 84, 14);
		contentPane.add(lblPrenumeAsistent);

		JLabel lblDataConsultatie = new JLabel("Data consultatie");
		lblDataConsultatie.setBounds(29, 182, 103, 14);
		contentPane.add(lblDataConsultatie);

		JLabel lblIstoric = new JLabel("Istoric");
		lblIstoric.setBounds(29, 207, 46, 14);
		contentPane.add(lblIstoric);

		JLabel lblInvestigatii = new JLabel("Investigatii");
		lblInvestigatii.setBounds(29, 322, 71, 14);
		contentPane.add(lblInvestigatii);

		JLabel lblRecomandari = new JLabel("Recomandari");
		lblRecomandari.setBounds(29, 440, 84, 14);
		contentPane.add(lblRecomandari);

		JLabel lblDiagnostic = new JLabel("Diagnostic");
		lblDiagnostic.setBounds(29, 387, 71, 14);
		contentPane.add(lblDiagnostic);

		textNumePacient = new JTextField();
		textNumePacient.setBounds(155, 8, 198, 20);
		contentPane.add(textNumePacient);
		textNumePacient.setColumns(10);

		textPrenumePacient = new JTextField();
		textPrenumePacient.setText("");
		textPrenumePacient.setBounds(155, 29, 198, 20);
		contentPane.add(textPrenumePacient);
		textPrenumePacient.setColumns(10);

		textNumeMedic = new JTextField();
		textNumeMedic.setBounds(155, 54, 198, 20);
		contentPane.add(textNumeMedic);
		textNumeMedic.setColumns(10);

		textPrenumeMedic = new JTextField();
		textPrenumeMedic.setBounds(155, 79, 198, 20);
		contentPane.add(textPrenumeMedic);
		textPrenumeMedic.setColumns(10);

		textNumeAsistent = new JTextField();
		textNumeAsistent.setText("");
		textNumeAsistent.setBounds(155, 129, 198, 20);
		contentPane.add(textNumeAsistent);
		textNumeAsistent.setColumns(10);

		textPrenumeAsistent = new JTextField();
		textPrenumeAsistent.setBounds(155, 154, 198, 20);
		contentPane.add(textPrenumeAsistent);
		textPrenumeAsistent.setColumns(10);

		textData = new JTextField();
		textData.setBounds(155, 179, 198, 20);
		contentPane.add(textData);
		textData.setColumns(10);

		textIstoric = new JTextArea();
		textIstoric.setLineWrap(true);
		textIstoric.setColumns(30);
		textIstoric.setBounds(155, 207, 200, 39);
		contentPane.add(textIstoric);

		textInvestigatii = new JTextArea();
		textInvestigatii.setBounds(155, 307, 198, 49);
		contentPane.add(textInvestigatii);

		textDiagnostic = new JTextArea();
		textDiagnostic.setBounds(155, 367, 198, 49);
		contentPane.add(textDiagnostic);

		textRecomandari = new JTextArea();
		textRecomandari.setBounds(155, 425, 198, 49);
		contentPane.add(textRecomandari);

		textMedicRecomandare = new JTextField();
		textMedicRecomandare.setBounds(155, 104, 198, 20);
		contentPane.add(textMedicRecomandare);
		textMedicRecomandare.setColumns(10);

		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(155, 505, 89, 23);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		JLabel lblSimptome = new JLabel("Simptome");
		lblSimptome.setBounds(29, 270, 46, 14);
		contentPane.add(lblSimptome);
		
		textArea = new JTextArea();
		textArea.setBounds(155, 257, 198, 39);
		contentPane.add(textArea);
		
		
		try {
			Connection con1 = this.connection.getConnection();
			PreparedStatement pacienti=con1.prepareStatement("select Nume,Prenume from pacienti where idPacient=?;");
			pacienti.setInt(1, this.idPacient);
			pacienti.executeQuery();
			ResultSet rs=pacienti.getResultSet();
			ResultSetMetaData rsmd=rs.getMetaData();
			rs.next();
			this.textNumePacient.setText(rs.getString("Nume"));
			this.textPrenumePacient.setText(rs.getString("Prenume"));
			PreparedStatement medic=con1.prepareStatement("select Nume,Prenume from utilizator where idUtilizator=?;");
			medic.setInt(1,this.idMedic);
			medic.executeQuery();
			rs=medic.getResultSet();
			rsmd=rs.getMetaData();
			rs.next();
			this.textNumeMedic.setText(rs.getString("Nume"));
			this.textPrenumeMedic.setText(rs.getString("Prenume"));
			PreparedStatement raport=con1.prepareStatement(
					"select Data,Istoric,Simptome,Investigatii,Recomandari,diagnostice.Diagnostic "
					+ "from raport_medical join(diagnostice) "
					+ "on diagnostice.idDiagnostic=raport_medical.Diagnostic "
					+ "where raport_medical.idRaport=?;");
			raport.setInt(1, this.idRaport);
			raport.executeQuery();
			rs=raport.getResultSet();
			rsmd=rs.getMetaData();
			rs.next();
			this.textData.setText(rs.getString("Data"));
			this.textIstoric.setText(rs.getString("Istoric"));
			this.textArea.setText(rs.getString("Simptome"));
			this.textInvestigatii.setText(rs.getString("Investigatii"));
			this.textRecomandari.setText(rs.getString("Recomandari"));
			this.textDiagnostic.setText(rs.getString("diagnostice.Diagnostic"));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}

		

	}
}
