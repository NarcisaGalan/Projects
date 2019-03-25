package ResurseUmane;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;
import Main.PaginaPrincipala;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CautareAngajatPentruInspector extends JFrame implements ActionListener {

	private DefaultDBConnection connection;
	private int ID;
	private JPanel contentPane;
	private JTextField nume;
	private JTextField prenume;
	private JTextField functie;
	private JButton btnVizualizareOrar;
	private JButton  btnVizualizareConccediu;
	private String textNume;
	private String textPrenume;
	private String textFunctie;
	private JButton btnAdaugareOrar;
	private JButton btnAdaugareConcediu;

	public CautareAngajatPentruInspector(DefaultDBConnection connection,int id) {
		
		this.connection=connection;
		this.ID=id;
		
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel furnizareInfrmatii = new JPanel();
		furnizareInfrmatii.setBounds(5, 6, 424, 250);
		furnizareInfrmatii.setBackground(new Color(173, 216, 230));
		furnizareInfrmatii.setForeground(new Color(0, 0, 102));
		contentPane.add(furnizareInfrmatii);
		furnizareInfrmatii.setLayout(null);
		
		nume = new JTextField();
		nume.setBounds(162, 60, 113, 20);
		furnizareInfrmatii.add(nume);
		nume.setColumns(10);
		
		prenume = new JTextField();
		prenume.setBounds(162, 91, 113, 20);
		furnizareInfrmatii.add(prenume);
		prenume.setColumns(10);
		
		functie = new JTextField();
		functie.setBounds(162, 122, 113, 20);
		furnizareInfrmatii.add(functie);
		functie.setColumns(10);
		
		JLabel lblNume = new JLabel("Nume:");
		lblNume.setBounds(38, 63, 85, 14);
		furnizareInfrmatii.add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume:");
		lblPrenume.setBounds(38, 94, 85, 14);
		furnizareInfrmatii.add(lblPrenume);
		
		JLabel lblFunctie = new JLabel("Functie:");
		lblFunctie.setBounds(38, 125, 85, 14);
		furnizareInfrmatii.add(lblFunctie);
		
		JLabel lblIntroducetiUrmatoareleInformatii = new JLabel("Introduceti urmatoarele informatii pentru angajatul cautat:");
		lblIntroducetiUrmatoareleInformatii.setFont(new Font("Arial", Font.BOLD, 11));
		lblIntroducetiUrmatoareleInformatii.setBounds(38, 21, 327, 14);
		furnizareInfrmatii.add(lblIntroducetiUrmatoareleInformatii);
		
		JButton btnVizualizareOrar = new JButton("Vizualizare orar");
		btnVizualizareOrar.setBackground(Color.WHITE);
		btnVizualizareOrar.setForeground(new Color(0, 0, 153));
		btnVizualizareOrar.setFont(new Font("Arial", Font.BOLD, 11));
		btnVizualizareOrar.setBounds(38, 183, 173, 23);
		furnizareInfrmatii.add(btnVizualizareOrar);
		btnVizualizareOrar.addActionListener(this);
		
		JButton btnVizualizareConcediu = new JButton("Vizualizare concediu");
		btnVizualizareConcediu.setBackground(Color.WHITE);
		btnVizualizareConcediu.setForeground(new Color(0, 0, 153));
		btnVizualizareConcediu.setFont(new Font("Arial", Font.BOLD, 11));
		btnVizualizareConcediu.setBounds(231, 153, 162, 23);
		furnizareInfrmatii.add(btnVizualizareConcediu);
		btnVizualizareConcediu.addActionListener(this);
		
		JButton btnAnulare = new JButton("Anulare");
		btnAnulare.setFont(new Font("Arial", Font.BOLD, 11));
		btnAnulare.setBackground(Color.WHITE);
		btnAnulare.setForeground(new Color(0, 0, 153));
		btnAnulare.setBounds(231, 183, 162, 23);
		furnizareInfrmatii.add(btnAnulare);
		
		JButton btnVizualizareOrarSpecific = new JButton("Vizualizare orar specific");
		btnVizualizareOrarSpecific.setBackground(Color.WHITE);
		 btnVizualizareOrarSpecific.addActionListener(this);
		btnVizualizareOrarSpecific.setFont(new Font("Arial", Font.BOLD, 11));
		btnVizualizareOrarSpecific.setForeground(new Color(0, 0, 153));
		btnVizualizareOrarSpecific.setBounds(38, 153, 173, 23);
		furnizareInfrmatii.add(btnVizualizareOrarSpecific);
		
		 btnAdaugareOrar = new JButton("Adaugare orar specific");
		btnAdaugareOrar.setBounds(38, 216, 173, 23);
		furnizareInfrmatii.add(btnAdaugareOrar);
		btnAdaugareOrar.addActionListener(this);
		
		 btnAdaugareConcediu = new JButton("Adaugare concediu");
		btnAdaugareConcediu.setBounds(231, 216, 162, 23);
		furnizareInfrmatii.add(btnAdaugareConcediu);
		btnAdaugareConcediu.addActionListener(this);
		btnAnulare.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		
		
		if(action.getActionCommand().equals("Anulare")) {
			PaginaPrincipala backPage=new PaginaPrincipala(connection,ID);
			this.setVisible(false);
			backPage.setVisible(true);
		}
		
		if(action.getActionCommand().equals("Vizualizare concediu")) {
			try {
				Connection con=this.connection.getConnection();
				this.textNume=nume.getText();
				this.textPrenume=prenume.getText();
				this.textFunctie=functie.getText();
				
				ConcediuAngajat concediuPage=new ConcediuAngajat(this.connection, this.ID,this.textNume,this.textPrenume,this.textFunctie);
				concediuPage.setVisible(true);
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(action.getActionCommand().equals("Vizualizare orar")) {
			this.textNume=nume.getText();
			this.textPrenume=prenume.getText();
			this.textFunctie=functie.getText();
			OrarAngajatResurse page=new OrarAngajatResurse(this.connection,this.ID,this.textNume,this.textPrenume,this.textFunctie);
			page.setVisible(true);
		}
		
		if(action.getActionCommand().equals("Vizualizare orar specific")) {
			this.textNume=nume.getText();
			this.textPrenume=prenume.getText();
			this.textFunctie=functie.getText();
			OrarSpecificAngajat page=new OrarSpecificAngajat(this.connection,this.ID,this.textNume,this.textPrenume,this.textFunctie);
			page.setVisible(true);
		}
		
		if(action.getActionCommand().equals("Adaugare concediu")) {
			this.textNume=nume.getText();
			this.textPrenume=prenume.getText();
			this.textFunctie=functie.getText();
			AdaugareConcediu concediu=new AdaugareConcediu(this.connection,this.ID,this.textNume,this.textPrenume,this.textFunctie);
			concediu.setVisible(true);
		}

		if(action.getActionCommand().equals("Adaugare orar specific")) {
			this.textNume=nume.getText();
			this.textPrenume=prenume.getText();
			this.textFunctie=functie.getText();
			AdaugareOrarSpecific orarSpecific=new AdaugareOrarSpecific(this.connection,
					this.ID,this.textNume,this.textPrenume,this.textFunctie);
			orarSpecific.setVisible(true);
		}
		
		
		
		
	}

	public JTextField getNume() {
		return nume;
	}

	public JTextField getPrenume() {
		return prenume;
	}

	public JTextField getFunctie() {
		return functie;
	}
}












