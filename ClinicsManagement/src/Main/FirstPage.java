package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import InregistrareUtilizatorNou.Register;

import org.jdatepicker.graphics.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class FirstPage extends JFrame implements ActionListener {

	private DefaultDBConnection connection;
	private JPanel contentPane;
	private JTextField textUser;
	private JButton btnRegister;
	private JButton btnLogin;
	private JPasswordField textPass;

	public FirstPage(DefaultDBConnection connection) {
		this.connection = connection;
		setTitle("Policlinica Narcisa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(133, 58, 80, 14);
		panel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(133, 99, 80, 14);
		panel.add(lblPassword);

		textUser = new JTextField();
		textUser.setBounds(201, 55, 86, 20);
		panel.add(textUser);
		textUser.setColumns(20);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(41, 194, 140, 45);
		panel.add(btnRegister);
		btnRegister.addActionListener(this);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(226, 194, 140, 45);
		panel.add(btnLogin);
		
		textPass = new JPasswordField();
		textPass.setColumns(20);
		textPass.setBounds(201, 96, 86, 20);
		panel.add(textPass);
		btnLogin.addActionListener(this);
		
		UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        Properties p=new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

	}

	public String getUsername() {
		return this.textUser.getText();
	}

	public String getPassword() {
		return this.textPass.getText();
	}

	public JButton getRegButton() {
		return this.btnRegister;
	}

	public JButton getLoginButton() {
		return this.btnLogin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	
		if (e.getActionCommand().equals("Register")) {
			Register reg = new Register(this.connection);
			reg.setVisible(true);
			this.setVisible(false);
		}

		if (e.getActionCommand().equals("Login")) {
			try {
				ResultSet rs = null;
				ResultSetMetaData rsmd = null;
				Connection mainConnection = this.connection.getConnection();
				Statement stm = mainConnection.createStatement();
				stm.execute("SELECT CNP,Parola FROM utilizator");
				rs = stm.getResultSet();
				rsmd = rs.getMetaData();
				int ok = 0;
				while (rs.next()) {
					if (this.getUsername().equals(rs.getString("CNP"))
							&& this.getPassword().equals(rs.getString("Parola"))) {
						ok = 1;
					}
				}
				if(ok==1) {
					stm.execute("SELECT idUtilizator from utilizator where CNP="+this.getUsername()+";");
					rs = stm.getResultSet();
					rsmd = rs.getMetaData();
					rs.next();
					System.out.println("Totul e bine in lume "+rs.getInt("idUtilizator"));
					PaginaPrincipala frame=new PaginaPrincipala(connection,rs.getInt("idUtilizator"));
					this.setVisible(false);
					frame.setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(this, "CNP sau parola gresita!");
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}

	}
}
