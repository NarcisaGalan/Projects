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

public class AdaugareAsistent extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textGrad;
	private JTextField textTip;
	private JButton btnAnulare;
	private JButton btnNewButton;
	private DefaultDBConnection connection;
	private int id;
	
	public AdaugareAsistent(DefaultDBConnection connection, int id) {
		this.id=id;
		setTitle("Adaugare asistent medical");
		this.connection=connection;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipAsistent = new JLabel("Tip asistent:");
		lblTipAsistent.setBounds(91, 50, 80, 14);
		contentPane.add(lblTipAsistent);
		
		JLabel lblGrad = new JLabel("Grad asistent:");
		lblGrad.setBounds(91, 86, 80, 14);
		contentPane.add(lblGrad);
		
		textGrad = new JTextField();
		textGrad.setBounds(197, 83, 86, 20);
		contentPane.add(textGrad);
		textGrad.setColumns(10);
		
		textTip = new JTextField();
		textTip.setBounds(197, 44, 86, 20);
		contentPane.add(textTip);
		textTip.setColumns(10);
		
		 btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(245, 166, 126, 36);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
		
		 btnNewButton = new JButton("Adaugare asistent");
		btnNewButton.setBounds(65, 166, 126, 36);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("Anulare")) {
			AdaugareAngajat angajat = new AdaugareAngajat(this.connection);
			angajat.setVisible(true);
			this.setVisible(false);
		}
		if(arg0.getActionCommand().equals("Adaugare asistent")) {
			try {
				Connection con=this.connection.getConnection();
				PreparedStatement stm=con.prepareStatement("call adaugare_asistent(@_id, @_tip, @_grad);");
				stm.setInt(1, this.id);
				stm.setString(2,this.textTip.getText());
				stm.setString(3, this.textGrad.getText());
				stm.execute();
				
				JOptionPane.showMessageDialog(this, "Asistentul a fost adaugat!");
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
