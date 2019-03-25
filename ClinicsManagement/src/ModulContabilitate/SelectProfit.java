package ModulContabilitate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class SelectProfit extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnAnulare;

	
	public SelectProfit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(162, 193, 89, 23);
		contentPane.add(btnAnulare);
		btnAnulare.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("Anulare")) {
			this.setVisible(false);
		}
	}

}
