package InregistrareUtilizatorNou;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AdaugareMedic extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textSpecializare1;
	private JTextField textSpecilizare2;
	private JTextField textSpecializare3;
	private JTextField textGrad;
	private JTextField textParafa;
	private JTextField textTitlu;
	private JTextField textPost;
	private JTextField textProcent;
	private JTextField textCompetenta;
	private JTextField textCompetanta2;
	private JTextField textCompetenta3;
	private JButton btnAnulare;
	private JButton btnAdaugareMedic;
	private DefaultDBConnection connection;
	private int id;

	
	public AdaugareMedic(DefaultDBConnection connection,int id) {
		this.connection=connection;
		this.id=id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSpacializare1 = new JLabel("Specializare:");
		lblSpacializare1.setBounds(109, 26, 67, 14);
		contentPane.add(lblSpacializare1);
		
		JLabel lblSpecializare2 = new JLabel("Specializare:");
		lblSpecializare2.setBounds(109, 51, 67, 14);
		contentPane.add(lblSpecializare2);
		
		JLabel lblSpecializare3 = new JLabel("Specializare:");
		lblSpecializare3.setBounds(109, 76, 67, 14);
		contentPane.add(lblSpecializare3);
		
		JLabel lblGrad = new JLabel("Grad:");
		lblGrad.setBounds(109, 101, 67, 14);
		contentPane.add(lblGrad);
		
		JLabel lblParafa = new JLabel("Parafa:");
		lblParafa.setBounds(109, 126, 46, 14);
		contentPane.add(lblParafa);
		
		JLabel lblTitluStiintific = new JLabel("Titlu stiintific:");
		lblTitluStiintific.setBounds(109, 151, 67, 14);
		contentPane.add(lblTitluStiintific);
		
		JLabel lblPostDidactic = new JLabel("Post didactic:");
		lblPostDidactic.setBounds(109, 176, 67, 14);
		contentPane.add(lblPostDidactic);
		
		JLabel lblCompetenta = new JLabel("Competenta:");
		lblCompetenta.setBounds(109, 226, 67, 14);
		contentPane.add(lblCompetenta);
		
		JLabel lblProcent = new JLabel("Procent:");
		lblProcent.setBounds(109, 201, 67, 14);
		contentPane.add(lblProcent);
		
		JLabel lblCompetenta_1 = new JLabel("Competenta:");
		lblCompetenta_1.setBounds(109, 251, 80, 14);
		contentPane.add(lblCompetenta_1);
		
		JLabel lblComepenta = new JLabel("Comepenta");
		lblComepenta.setBounds(109, 276, 67, 14);
		contentPane.add(lblComepenta);
		
		textSpecializare1 = new JTextField();
		textSpecializare1.setBounds(186, 23, 121, 20);
		contentPane.add(textSpecializare1);
		textSpecializare1.setColumns(10);
		
		textSpecilizare2 = new JTextField();
		textSpecilizare2.setBounds(186, 48, 121, 20);
		contentPane.add(textSpecilizare2);
		textSpecilizare2.setColumns(10);
		
		textSpecializare3 = new JTextField();
		textSpecializare3.setText("");
		textSpecializare3.setBounds(186, 73, 121, 20);
		contentPane.add(textSpecializare3);
		textSpecializare3.setColumns(10);
		
		textGrad = new JTextField();
		textGrad.setBounds(186, 98, 121, 20);
		contentPane.add(textGrad);
		textGrad.setColumns(10);
		
		textParafa = new JTextField();
		textParafa.setText("");
		textParafa.setBounds(186, 123, 121, 20);
		contentPane.add(textParafa);
		textParafa.setColumns(10);
		
		textTitlu = new JTextField();
		textTitlu.setBounds(186, 148, 121, 20);
		contentPane.add(textTitlu);
		textTitlu.setColumns(10);
		
		textPost = new JTextField();
		textPost.setBounds(186, 173, 121, 20);
		contentPane.add(textPost);
		textPost.setColumns(10);
		
		textProcent = new JTextField();
		textProcent.setText("");
		textProcent.setBounds(186, 198, 121, 20);
		contentPane.add(textProcent);
		textProcent.setColumns(10);
		
		textCompetenta = new JTextField();
		textCompetenta.setBounds(186, 223, 121, 20);
		contentPane.add(textCompetenta);
		textCompetenta.setColumns(10);
		
		textCompetanta2 = new JTextField();
		textCompetanta2.setText("");
		textCompetanta2.setBounds(186, 248, 121, 20);
		contentPane.add(textCompetanta2);
		textCompetanta2.setColumns(10);
		
		textCompetenta3 = new JTextField();
		textCompetenta3.setText("");
		textCompetenta3.setBounds(186, 273, 121, 20);
		contentPane.add(textCompetenta3);
		textCompetenta3.setColumns(10);
		
		 btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(271, 332, 109, 35);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		 btnAdaugareMedic = new JButton("Adaugare medic");
		btnAdaugareMedic.setBounds(67, 332, 109, 35);
		contentPane.add(btnAdaugareMedic);
		btnAdaugareMedic.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Anulare")) {
			AdaugareAngajat angajat = new AdaugareAngajat(this.connection);
			this.setVisible(false);
			angajat.setVisible(true);
		}
		if(arg0.getActionCommand().equals("Adaugare medic")) {
			try {
				Connection con=this.connection.getConnection();
				PreparedStatement stm=con.prepareStatement("call policlinici.adaugare_medic(?, ?, ?, ?,"
						+ " ?, ?, ?, ?, ?, ?, ?);");
				stm.setInt(1, this.id);
				stm.setString(2, this.textSpecializare1.getText());
				stm.setString(3,this.textSpecilizare2.getText());
				stm.setString(4, this.textGrad.getText());
				stm.setString(5, this.textParafa.getText());
				stm.setString(6, this.textTitlu.getText());
				stm.setString(7, this.textPost.getText());
				stm.setString(8, this.textProcent.getText());
				stm.setString(9, this.textCompetenta.getText());
				stm.setString(10, this.textCompetanta2.getText());
				stm.setString(11, this.textCompetenta3.getText());
				
				stm.execute();
				JOptionPane.showMessageDialog(this, "Medicul a fost adaugat!");
				OrarAngajat orarFrame=new OrarAngajat(this.connection);
				orarFrame.setId(this.id);
				orarFrame.setVisible(true);
				this.setVisible(false);

			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
}
