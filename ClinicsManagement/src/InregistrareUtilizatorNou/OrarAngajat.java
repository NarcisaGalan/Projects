package InregistrareUtilizatorNou;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import Main.FirstPage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class OrarAngajat extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JComboBox LuniBox;
	private JComboBox MartiBox;
	private JComboBox MiercuriBox;
	private JComboBox JoiBox;
	private JComboBox VineriBox;
	private JComboBox SambataBox;
	private JComboBox luni_i;
	private JComboBox luni_d;
	private JComboBox marti_i;
	private JComboBox marti_d;
	private JComboBox miercuri_d;
	private JComboBox miercuri_i;
	private JComboBox joi_d;
	private JComboBox joi_i;
	private JComboBox vineri_d;
	private JComboBox vineri_i;
	private JComboBox sambata_d;
	private JComboBox sambata_i;
	private JButton btnAdaugare;
	private JButton btnAnulare;
	private DefaultDBConnection connection;
	private int ID;
	
	

	public OrarAngajat(DefaultDBConnection connection) {
		this.connection=connection;
		setTitle("Orar angajat");
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
		
		JLabel lblLocatii = new JLabel("Locatii");
		lblLocatii.setBounds(65, 23, 30, 14);
		panel.add(lblLocatii);
		
		JLabel lblOrar = new JLabel("Orar");
		lblOrar.setBounds(294, 23, 46, 14);
		panel.add(lblOrar);
		
		JLabel lblLuni = new JLabel("Luni:");
		lblLuni.setBounds(10, 58, 46, 14);
		panel.add(lblLuni);
		
		JLabel lblMarti = new JLabel("Marti:");
		lblMarti.setBounds(10, 86, 46, 14);
		panel.add(lblMarti);
		
		JLabel lblMiercuri = new JLabel("Miercuri:");
		lblMiercuri.setBounds(10, 111, 46, 14);
		panel.add(lblMiercuri);
		
		JLabel lblJoi = new JLabel("Joi:");
		lblJoi.setBounds(10, 136, 46, 14);
		panel.add(lblJoi);
		
		JLabel lblVineri = new JLabel("Vineri:");
		lblVineri.setBounds(10, 161, 46, 14);
		panel.add(lblVineri);
		
		JLabel lblSambata = new JLabel("Sambata:");
		lblSambata.setBounds(10, 186, 46, 14);
		panel.add(lblSambata);
		
		LuniBox = new JComboBox();
		LuniBox.setModel(new DefaultComboBoxModel(new String[] {"Medlife", "Medstar", "Bluelfie"}));
		LuniBox.setBounds(95, 55, 71, 20);
		panel.add(LuniBox);
		
		MartiBox = new JComboBox();
		MartiBox.setModel(new DefaultComboBoxModel(new String[] {"Medlife", "Medstar", "Bluelfie"}));
		MartiBox.setBounds(95, 83, 71, 20);
		panel.add(MartiBox);
		
		MiercuriBox = new JComboBox();
		MiercuriBox.setModel(new DefaultComboBoxModel(new String[] {"Medlife", "Medstar", "Bluelfie"}));
		MiercuriBox.setBounds(95, 108, 71, 20);
		panel.add(MiercuriBox);
		
		JoiBox = new JComboBox();
		JoiBox.setModel(new DefaultComboBoxModel(new String[] {"Medlife", "Medstar", "Bluelfie"}));
		JoiBox.setBounds(95, 133, 71, 20);
		panel.add(JoiBox);
		
		VineriBox = new JComboBox();
		VineriBox.setModel(new DefaultComboBoxModel(new String[] {"Medlife", "Medstar", "Bluelfie"}));
		VineriBox.setBounds(95, 158, 71, 20);
		panel.add(VineriBox);
		
		SambataBox = new JComboBox();
		SambataBox.setModel(new DefaultComboBoxModel(new String[] {"Medlife", "Medstar", "Bluelfie"}));
		SambataBox.setBounds(95, 183, 71, 20);
		panel.add(SambataBox);
		
		luni_d= new JComboBox();
		luni_d.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		luni_d.setBounds(245, 55, 59, 20);
		panel.add(luni_d);
		
		luni_i = new JComboBox();
		luni_i.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		luni_i.setBounds(327, 55, 59, 20);
		panel.add(luni_i);
		
		marti_d = new JComboBox();
		marti_d.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		marti_d.setBounds(245, 83, 59, 20);
		panel.add(marti_d);
		
		marti_i = new JComboBox();
		marti_i.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		marti_i.setBounds(327, 83, 59, 20);
		panel.add(marti_i);
		
		miercuri_d = new JComboBox();
		miercuri_d.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		miercuri_d.setBounds(245, 108, 59, 20);
		panel.add(miercuri_d);
		
		miercuri_i = new JComboBox();
		miercuri_i.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		miercuri_i.setBounds(327, 108, 59, 20);
		panel.add(miercuri_i);
		
		joi_d = new JComboBox();
		joi_d.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		joi_d.setBounds(245, 133, 59, 20);
		panel.add(joi_d);
		
		joi_i = new JComboBox();
		joi_i.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		joi_i.setBounds(327, 133, 59, 20);
		panel.add(joi_i);
		
		vineri_d = new JComboBox();
		vineri_d.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		vineri_d.setBounds(245, 158, 59, 20);
		panel.add(vineri_d);
		
		vineri_i = new JComboBox();
		vineri_i.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		vineri_i.setBounds(327, 158, 59, 20);
		panel.add(vineri_i);
		
		sambata_d = new JComboBox();
		sambata_d.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		sambata_d.setBounds(245, 183, 59, 20);
		panel.add(sambata_d);
		
		sambata_i = new JComboBox();
		sambata_i.setModel(new DefaultComboBoxModel(new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00"}));
		sambata_i.setBounds(327, 183, 59, 20);
		panel.add(sambata_i);
		
		btnAdaugare = new JButton("Adaugare orar");
		btnAdaugare.setBounds(65, 214, 118, 36);
		panel.add(btnAdaugare);
		btnAdaugare.addActionListener( this);
		
		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(255, 214, 118, 36);
		panel.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
	}


	public void setId(int ID) {
		this.ID=ID;
	}
	
	public String getLocation(JComboBox x) {
		return x.getSelectedItem().toString();
	}
	
	public String getTime(JComboBox x) {
		return x.getSelectedItem().toString();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getActionCommand().equals("Adaugare orar")){
			try {
				Connection scheduleConnection = this.connection.getConnection();
				PreparedStatement stm = scheduleConnection.prepareStatement("call adaugare_orar_angajat(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				System.out.println("ID-ul este:" + this.ID);
				stm.setInt(1, this.ID);
				stm.setString(2, getLocation(LuniBox));
				stm.setString(3, getLocation(MartiBox));
				stm.setString(4, getLocation(MiercuriBox));
				stm.setString(5, getLocation(JoiBox));
				stm.setString(6, getLocation(VineriBox));
				stm.setString(7, getLocation(SambataBox));
				stm.setString(8, getTime(luni_d));
				stm.setString(9, getTime(luni_i));
				stm.setString(10, getTime(marti_d));
				stm.setString(11, getTime(marti_i));
				stm.setString(12, getTime(miercuri_d));
				stm.setString(13, getTime(miercuri_i));
				stm.setString(14, getTime(joi_d));
				stm.setString(15, getTime(joi_i));
				stm.setString(16, getTime(vineri_d));
				stm.setString(17, getTime(vineri_i));
				stm.setString(18, getTime(sambata_d));
				stm.setString(19, getTime(sambata_i));

				stm.executeQuery();
				
				JOptionPane.showMessageDialog(this, "Orarul a fost adaugat cu succes!");
				FirstPage firstPage=new FirstPage(this.connection);
				firstPage.setVisible(true);
				this.setVisible(false);
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
			AdaugareAngajat frame=new AdaugareAngajat(this.connection);
			frame.setVisible(true);
		}
		
	}
}
