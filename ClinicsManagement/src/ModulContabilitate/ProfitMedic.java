package ModulContabilitate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ProfitMedic extends JFrame {

	private JPanel contentPane;
	private JTextField textNume;
	private JTextField textProfit;
	private DefaultDBConnection connection;
	private int id;
	private JButton btnInapoi;

	public ProfitMedic(DefaultDBConnection connection,int id) {
		this.connection=connection;
		this.id=id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setBounds(112, 59, 46, 14);
		contentPane.add(lblNume);
		
		JLabel lblProfit = new JLabel("Profit");
		lblProfit.setBounds(112, 95, 46, 14);
		contentPane.add(lblProfit);
		
		textNume = new JTextField();
		textNume.setBounds(168, 56, 148, 20);
		contentPane.add(textNume);
		textNume.setColumns(10);
		
		textProfit = new JTextField();
		textProfit.setBounds(168, 92, 148, 20);
		contentPane.add(textProfit);
		textProfit.setColumns(10);
		
		 btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(168, 187, 103, 40);
		contentPane.add(btnInapoi);
		btnInapoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ProfitMedic.this.setVisible(false);
			}
			
		});
		
		
		try {
			Statement stm1=this.connection.getConnection().createStatement();
			stm1.executeQuery("select Nume,Prenume from utilizator where idUtilizator="+this.id+";");
			ResultSet rs=stm1.getResultSet();
			rs.next();
			this.textNume.setText(rs.getString("Nume")+" "+rs.getString("Prenume"));
			
			PreparedStatement stm=this.connection.getConnection().prepareStatement(
					"select policlinici.profit_medic(?) as salar from dual");
			stm.setInt(1, this.id);
			stm.execute();
			rs=stm.getResultSet();
			rs.next();
			this.textProfit.setText(rs.getString("salar"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
