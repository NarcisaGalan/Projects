package InregistrareUtilizatorNou;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Main.DateLabelFormatter;
import Main.DefaultDBConnection;
import Main.FirstPage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

public class Register extends JFrame implements ActionListener {

	private DefaultDBConnection connection;
	private JPanel contentPane;
	private JPanel panel;
	private JButton button;
	private JPanel panel_1;
	private JTextField textNume;
	private JTextField textPrenume;
	private JTextField textAdresa;
	private JTextField textTelefon;
	private JTextField textMail;
	private JTextField textContract;
	private JPasswordField passwordField;
	private JTextField textCNP;
	private JComboBox comboBox;
	private JButton btnAnulare;
	private JDatePicker datePicker;
	private JDateChooser dateChooser;

	public Register(DefaultDBConnection connection) {
		this.connection = connection;
		setTitle("Inregistrare utilizator nou");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 276, 300);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel label = new JLabel("CNP");
		label.setBounds(39, 28, 46, 14);
		panel_1.add(label);

		JLabel label_1 = new JLabel("Nume");
		label_1.setBounds(39, 79, 46, 14);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("Prenume");
		label_2.setBounds(39, 104, 46, 14);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("Adresa");
		label_3.setBounds(39, 129, 46, 14);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("Telefon");
		label_4.setBounds(39, 154, 46, 14);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("Email");
		label_5.setBounds(39, 179, 46, 14);
		panel_1.add(label_5);

		JLabel label_6 = new JLabel("Data angajare");
		label_6.setBounds(39, 204, 76, 14);
		panel_1.add(label_6);

		JLabel label_7 = new JLabel("Numar contract");
		label_7.setBounds(39, 229, 76, 14);
		panel_1.add(label_7);

		JLabel label_8 = new JLabel("Functie");
		label_8.setBounds(39, 254, 76, 14);
		panel_1.add(label_8);

		textNume = new JTextField();
		textNume.setColumns(20);
		textNume.setBounds(163, 76, 86, 20);
		panel_1.add(textNume);

		textPrenume = new JTextField();
		textPrenume.setColumns(20);
		textPrenume.setBounds(163, 101, 86, 20);
		panel_1.add(textPrenume);

		textAdresa = new JTextField();
		textAdresa.setColumns(20);
		textAdresa.setBounds(163, 126, 86, 20);
		panel_1.add(textAdresa);

		textTelefon = new JTextField();
		textTelefon.setColumns(20);
		textTelefon.setBounds(163, 151, 86, 20);
		panel_1.add(textTelefon);

		textMail = new JTextField();
		textMail.setColumns(20);
		textMail.setBounds(163, 176, 86, 20);
		panel_1.add(textMail);

		UtilDateModel model=new UtilDateModel();
		Properties p=new Properties();
		p.put("text.today","Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel=new JDatePanelImpl(model,p);
		JDatePickerImpl datePicker=new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePanel.setLayout(null);
		
		panel_1.add(datePicker);
		
		textContract = new JTextField();
		textContract.setColumns(20);
		textContract.setBounds(163, 226, 86, 20);
		panel_1.add(textContract);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Administrator", "Anagajat" }));
		comboBox.setBounds(163, 251, 86, 20);
		panel_1.add(comboBox);

		JLabel label_9 = new JLabel("Parola");
		label_9.setBounds(39, 53, 46, 14);
		panel_1.add(label_9);

		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		passwordField.setBounds(163, 50, 86, 20);
		panel_1.add(passwordField);

		textCNP = new JTextField();
		textCNP.setColumns(20);
		textCNP.setBounds(163, 25, 86, 20);
		panel_1.add(textCNP);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(163, 201, 86, 20);
		panel_1.add(dateChooser);

		panel = new JPanel();
		panel.setBounds(286, 0, 145, 289);
		contentPane.add(panel);
		panel.setLayout(null);

		button = new JButton("Register");
		button.setBounds(21, 81, 103, 50);
		panel.add(button);

		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(21, 173, 103, 50);
		panel.add(btnAnulare);
		button.addActionListener(this);
		btnAnulare.addActionListener(this);

	}

	public String getFunctie() {
		return this.comboBox.getSelectedItem().toString();
	}

	public String getCNP() {
		return this.textCNP.getText();
	}

	public String getName() {
		return this.textNume.getText();
	}

	public String getPrename() {
		return this.textPrenume.getText();
	}

	public String getAdress() {
		return this.textAdresa.getText();
	}

	public String getPhone() {
		return this.textTelefon.getText();
	}



	public String getContract() {
		return this.textContract.getText();
	}

	public String getMail() {
		return this.textMail.getText();
	}

	public String getPassword() {
		return this.passwordField.getText();
	}

	public JButton getRegButton() {
		return this.button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Anulare")) {

			FirstPage page = new FirstPage(this.connection);
			page.setVisible(true);
			this.setVisible(false);
		}

		if (e.getActionCommand().equals("Register")) {

			try {
				ResultSet rs = null;
				ResultSetMetaData rsmd = null;
				Connection regConnection = this.connection.getConnection();
				Statement stm = regConnection.createStatement();
				int ok = 1;
				// Calculam noul ID
				stm.execute("SELECT max(idUtilizator) FROM utilizator");
				rs = stm.getResultSet();
				rsmd = rs.getMetaData();
				rs.next();
				int ID = Integer.parseInt(rs.getString("max(idUtilizator)"));
				ID++;
				System.out.println(ID);
				// Verificam daca CNP-ul e unic
				stm.execute("SELECT CNP FROM utilizator");
				rs = stm.getResultSet();
				rsmd = rs.getMetaData();
				while (rs.next()) {
					if (this.getCNP().equals(rs.getString("CNP"))) {
						ok = 0;
					}
				}
				
				if (ok == 1) {
					SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
					LocalDate date =LocalDate.parse(dt1.format(this.dateChooser.getDate()));
					stm.execute(
							"insert into utilizator(idUtilizator,CNP, Nume, Prenume,Parola, Adresa, Telefon, Email, IBAN, Contract,DataAngajarii, TipUtilizator) "
									+ "VALUES (" + ID + "," + this.getCNP() + ",'" + this.getName() + "','"
									+ this.getPrename() + "','" + this.getPassword() + "','" + this.getAdress() + "',"
									+ this.getPhone() + "," + "'" + this.getMail() + "',0," + this.getContract() + ",'"
									+ date + "','" + this.getFunctie() + "');");

					JOptionPane.showMessageDialog(this, "V-ati inregistrat cu succes!");

					if(this.getFunctie().equals("Anagajat")) {
					AdaugareAngajat angajatFrame = new AdaugareAngajat(this.connection);
					angajatFrame.setId(ID);
					angajatFrame.setVisible(true);
					this.setVisible(false);
					}else {
						FirstPage page=new FirstPage(this.connection);
						page.setVisible(true);
						this.setVisible(false);
					}

					

				} else {
					JOptionPane.showMessageDialog(this, "CNP-ul a fost introdus deja!");
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, "Datele nu au fost introduse corect!");
				e1.printStackTrace();
			}

		}

	}
}
