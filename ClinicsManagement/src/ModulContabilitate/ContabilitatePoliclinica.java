package ModulContabilitate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DefaultDBConnection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ContabilitatePoliclinica extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textCheltuieli;
	private DefaultDBConnection connection;
	private int id;
	private JButton btnAnulare;
	private JButton btnCalculareCheltuieli;
	private int sum;
	private JTextField textVenituri;
	private JLabel lblVenituri;
	private int sumVenit;
	private JLabel lblProfit;
	private JTextField textProfit;
	private int sumProfit;
	private JButton btnTipprofit;
	
	public ContabilitatePoliclinica(DefaultDBConnection connection,int id) {
		this.connection=connection;
		this.id=id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCheltuieli = new JLabel("Cheltuieli");
		lblCheltuieli.setBounds(55, 98, 56, 16);
		contentPane.add(lblCheltuieli);
		
		textCheltuieli = new JTextField();
		textCheltuieli.setEditable(false);
		textCheltuieli.setBounds(139, 92, 116, 22);
		contentPane.add(textCheltuieli);
		textCheltuieli.setColumns(10);
		
		btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(55, 192, 127, 25);
		contentPane.add(btnAnulare);
		

		btnCalculareCheltuieli = new JButton("Calculare cheltuieli");

		btnCalculareCheltuieli.addActionListener(this);
		btnCalculareCheltuieli.setBounds(217, 192, 139, 25);
		contentPane.add(btnCalculareCheltuieli);
		
		textVenituri = new JTextField();
		textVenituri.setEditable(false);
		textVenituri.setBounds(139, 49, 116, 22);
		contentPane.add(textVenituri);
		textVenituri.setColumns(10);
		
		lblVenituri = new JLabel("Venituri");
		lblVenituri.setBounds(55, 52, 56, 16);
		contentPane.add(lblVenituri);
		
		lblProfit = new JLabel("Profit");
		lblProfit.setBounds(55, 143, 56, 16);
		contentPane.add(lblProfit);
		
		textProfit = new JTextField();
		textProfit.setEditable(false);
		textProfit.setBounds(139, 140, 116, 22);
		contentPane.add(textProfit);
		textProfit.setColumns(10);
		
		btnTipprofit = new JButton("Tip Profit");
		
		btnTipprofit.setBounds(150, 265, 97, 25);
		contentPane.add(btnTipprofit);
		btnTipprofit.addActionListener(this);
		btnAnulare.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Anulare")) {
			
			this.setVisible(false);

		}
		
		if(e.getActionCommand().equals("Tip Profit")) {
			SelectProfit profitPage=new SelectProfit();
			profitPage.setVisible(true);
		}
		
		if(e.getActionCommand().equals("Calculare cheltuieli")) {
			try {
				Connection con = this.connection.getConnection();
				Statement stm = con.createStatement();
				stm.execute("select idUnitateMedicala from unitati_medicale;");
				ResultSet rs = stm.getResultSet();
				ResultSetMetaData rsmd = rs.getMetaData();
				
				List<Integer> ListaId = new ArrayList();
				while (rs.next()) {
					ListaId.add(rs.getInt("idUnitateMedicala"));
				}
				System.out.println(ListaId);
				int i=0;
				
				while(i<ListaId.size()) {
					PreparedStatement stm1 = con.prepareStatement("select sum(Salariu) from angajat where idUnitate=?;");
					stm1.setInt(1, ListaId.get(i));
					stm1.execute();
					ResultSet rs1 = stm1.getResultSet();
					ResultSetMetaData rsmd1 = rs1.getMetaData();
						rs1.next();
						sum = sum + rs1.getInt("sum(Salariu)");
					i++;
				}
				this.textCheltuieli.setText(Integer.toString(sum));
					ListaId.removeAll(ListaId);
				stm.execute("select idProgramare from programari;");
				rs = stm.getResultSet();
				rsmd = rs.getMetaData();
				
				while (rs.next()) {
					ListaId.add(rs.getInt("idProgramare"));
				}
				System.out.println(ListaId);
				
				while(i<ListaId.size()) {
					PreparedStatement stm2 = con.prepareStatement("select sum(servicii.pret) from programari,programari_servicii,servicii, raport_medical where  \r\n" + 
							"programari.idProgramare = programari_servicii.idProgramare and programari_servicii.idServiciu = servicii.idServiciu and raport_medical.idRaport is not null\r\n" + 
							"and programari.idProgramare = ?;");
					stm2.setInt(1, ListaId.get(i));
					stm2.execute();
					i++;
					ResultSet rs2 = stm2.getResultSet();
					ResultSetMetaData rsmd2 = rs2.getMetaData();
					rs2.next();
					sumVenit = sumVenit + rs2.getInt("sum(servicii.pret)");
				}
				this.textVenituri.setText(Integer.toString(sumVenit));
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			sumProfit = sumVenit - sum;
			this.textProfit.setText(Integer.toString(sumProfit));
		}
		
	}
	
	
}

