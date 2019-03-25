package ModulOperational;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import Main.PaginaPrincipala;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdaugareAnaliza extends JFrame implements ActionListener {

	private int ID;
	private DefaultDBConnection connection;

	private JPanel contentPane;
	private JTextField textCNP;
	private JTextField textData;
	private JTextField textOra;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;

	public AdaugareAnaliza(DefaultDBConnection connection, int ID) {
		this.ID = ID;
		this.connection = connection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 144);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblInscriereAnalizeMedicale = new JLabel("Inscriere analize medicale");
		lblInscriereAnalizeMedicale.setBounds(137, 11, 205, 14);
		panel.add(lblInscriereAnalizeMedicale);

		JLabel lblCnpPacient = new JLabel("CNP pacient");
		lblCnpPacient.setBounds(25, 43, 81, 14);
		panel.add(lblCnpPacient);

		textCNP = new JTextField();
		textCNP.setBounds(121, 40, 86, 20);
		panel.add(textCNP);
		textCNP.setColumns(10);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(25, 79, 46, 14);
		panel.add(lblData);

		textData = new JTextField();
		textData.setBounds(121, 76, 86, 20);
		panel.add(textData);
		textData.setColumns(10);

		JLabel lblOra = new JLabel("Ora");
		lblOra.setBounds(25, 119, 46, 14);
		panel.add(lblOra);

		textOra = new JTextField();
		textOra.setBounds(121, 116, 86, 20);
		panel.add(textOra);
		textOra.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setBounds(10, 190, 126, 20);
		contentPane.add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(146, 190, 149, 20);
		contentPane.add(comboBox_1);

		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(305, 190, 119, 20);
		contentPane.add(comboBox_2);

		try {
			System.out.println("Pana aici merge!");
			comboBox.removeAllItems();
			PreparedStatement stm = this.connection.getConnection()
					.prepareStatement("select servicii.Denumire from servicii "
							+ "where servicii.Competenta='asistent medical' ");

			stm.executeQuery();
			System.out.println("Pana aici !");
			ResultSet rs = stm.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			comboBox_1.addItem("null");
			comboBox_2.addItem("null");
			while (rs.next()) {
				comboBox.addItem(rs.getString("servicii.Denumire"));
				comboBox_1.addItem(rs.getString("servicii.Denumire"));
				comboBox_2.addItem(rs.getString("servicii.Denumire"));
			
			}

			contentPane.revalidate();
			contentPane.repaint();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		JLabel lblSelectatiAnalizaanalizeleMedicale = new JLabel("Selectati analiza/analizele medicale:");
		lblSelectatiAnalizaanalizeleMedicale.setBounds(10, 166, 209, 14);
		contentPane.add(lblSelectatiAnalizaanalizeleMedicale);

		JButton btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(10, 227, 126, 23);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		JButton btnAdaugareInscriere = new JButton("Adaugare inscriere");
		btnAdaugareInscriere.setBounds(146, 227, 149, 23);
		contentPane.add(btnAdaugareInscriere);
		btnAdaugareInscriere.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Anulare")) {
			PaginaPrincipala backPage = new PaginaPrincipala(this.connection, ID);
			backPage.setVisible(true);
			this.setVisible(false);
		}
		

		if (arg0.getActionCommand().equals("Adaugare inscriere")) {
			try {
				System.out.println("P");
				PreparedStatement stm = this.connection.getConnection()
						.prepareStatement("call adaugare_programare(?,?,?,?,?,?,?);");
				stm.setString(1, textCNP.getText());
				stm.setInt(2,0);
				stm.setString(3, comboBox.getSelectedItem().toString());
				stm.setString(4, comboBox_1.getSelectedItem().toString());
				stm.setString(5, comboBox_2.getSelectedItem().toString());
				stm.setString(6, textData.getText());
				stm.setString(7, textOra.getText());
				
				
				stm.executeQuery();
				JOptionPane.showMessageDialog(this, "Inscriere a fost adaugata cu succes!");
				PaginaPrincipala backPage = new PaginaPrincipala(connection, ID);
				this.setVisible(false);
				backPage.setVisible(true);
		
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Introduceti date valide!");
				e.printStackTrace();
			}
		}
		
	}
}
