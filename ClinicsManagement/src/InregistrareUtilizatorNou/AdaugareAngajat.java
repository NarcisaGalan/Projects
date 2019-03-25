package InregistrareUtilizatorNou;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class AdaugareAngajat extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textSalar;
	private JTextField textOre;
	private JComboBox textFunctie;
	private JComboBox textDepartament;
	private JComboBox textUnitate;
	private JButton btnContinuaInregistrarea;
	private JButton btnAnulare;
	private int ID;
	private DefaultDBConnection connection;

	public AdaugareAngajat(DefaultDBConnection connection) {
		setTitle("Adaugare angajat nou");
		this.connection = connection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 5, 429, 256);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSalar = new JLabel("Salar");
		lblSalar.setBounds(43, 27, 46, 14);
		panel.add(lblSalar);

		JLabel lblOre = new JLabel("Ore de lucru pe saptamana");
		lblOre.setBounds(43, 52, 130, 14);
		panel.add(lblOre);

		JLabel lblFunctie = new JLabel("Functie");
		lblFunctie.setBounds(43, 80, 46, 14);
		panel.add(lblFunctie);

		JLabel lblDepartament = new JLabel("Departament");
		lblDepartament.setBounds(43, 105, 63, 14);
		panel.add(lblDepartament);

		JLabel lblUnitateMedicala = new JLabel("Unitate medicala");
		lblUnitateMedicala.setBounds(43, 130, 79, 14);
		panel.add(lblUnitateMedicala);

		textSalar = new JTextField();
		textSalar.setBounds(208, 24, 130, 20);
		panel.add(textSalar);
		textSalar.setColumns(10);

		textOre = new JTextField();
		textOre.setBounds(208, 49, 130, 20);
		panel.add(textOre);
		textOre.setColumns(10);

		textFunctie = new JComboBox();
		textFunctie.setModel(new DefaultComboBoxModel(
				new String[] { "Medic", "Asistent medical", "Receptioner", "Inspector resurse umane", "Contabil" }));
		textFunctie.setBounds(208, 77, 130, 20);
		panel.add(textFunctie);

		textDepartament = new JComboBox();
		textDepartament
				.setModel(new DefaultComboBoxModel(new String[] { "Resurse umane", "Financiar-contabil", "Medical" }));
		textDepartament.setBounds(208, 102, 130, 20);
		panel.add(textDepartament);

		textUnitate = new JComboBox();
		textUnitate.setModel(new DefaultComboBoxModel(new String[] { "Medstar", "Bluelife", "Medlife" }));
		textUnitate.setBounds(208, 127, 130, 19);
		panel.add(textUnitate);

		btnContinuaInregistrarea = new JButton("Continua inregistrarea");
		btnContinuaInregistrarea.setBounds(43, 190, 139, 33);
		panel.add(btnContinuaInregistrarea);
		btnContinuaInregistrarea.addActionListener(this);

		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(219, 190, 139, 33);
		panel.add(btnAnulare);
		btnAnulare.addActionListener(this);

	}

	public String getFunctie() {
		return this.textFunctie.getSelectedItem().toString();
	}

	public int getDepartment() {
		if (this.textDepartament.getSelectedItem().toString().equals("Resurse umane")) {
			return 1;
		} else {
			if (this.textDepartament.getSelectedItem().toString().equals("Financiar-contabil")) {
				return 2;
			} else {
				return 3;
			}
		}
	}

	public String getBuilding() {
		return this.textUnitate.getSelectedItem().toString();
	}

	public void setId(int ID) {
		this.ID = ID;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Continua inregistrarea")) {
			try {
				Connection employeeConnection = this.connection.getConnection();
				PreparedStatement stm = employeeConnection.prepareStatement("call adaugare_angajat(?,?,?,?,?,?)");
				System.out.println("ID-ul este:" + this.ID);
				stm.setInt(1, this.ID);
				stm.setInt(2, Integer.parseInt(this.textSalar.getText()));
				stm.setInt(3, Integer.parseInt(this.textOre.getText()));
				stm.setString(4, this.getFunctie());
				stm.setInt(5, this.getDepartment());
				stm.setString(6, this.getBuilding());

				stm.executeQuery();
				if (this.getFunctie().equals("Medic")) {
					AdaugareMedic medic = new AdaugareMedic(this.connection, this.ID);
					medic.setVisible(true);
					this.setVisible(false);
				} else {
					if (this.getFunctie().equals("Asistent medical")) {
						AdaugareAsistent asistent=new AdaugareAsistent(this.connection,this.ID);
						asistent.setVisible(true);
						asistent.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(this, "Angajatul a fost adaugat!");
						OrarAngajat orarFrame = new OrarAngajat(this.connection);
						orarFrame.setId(this.ID);
						orarFrame.setVisible(true);
						this.setVisible(false);
					}
				}

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		}

		if (e.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
			Register frame = new Register(this.connection);
		}

	}
}
