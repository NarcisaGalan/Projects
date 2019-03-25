package ModulAdministrator;

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

public class CautareUtilizator extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textCNP;
	private JButton btnAnulare;
	private JButton btnCautare;
	private DefaultDBConnection connection;
	private int id;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefaultDBConnection connection = new DefaultDBConnection("jdbc:mysql://localhost/policlinici?user=");
					
					CautareUtilizator frame = new CautareUtilizator(connection, 1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CautareUtilizator(DefaultDBConnection connection, int id) {
		this.connection = connection;
		this.id = id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCnpPacient = new JLabel("CNP angajat:");
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
				stm.execute("select idUtilizator from utilizator where CNP=" + this.textCNP.getText() + ";");
				ResultSet rs = stm.getResultSet();
				ResultSetMetaData rsmd = rs.getMetaData();
				rs.next();
				DateAngajatAdministrator page = new DateAngajatAdministrator(connection, rs.getInt("idUtilizator"));
				page.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
}
