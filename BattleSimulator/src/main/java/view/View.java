package view;

import model.Character;
import model.Planet;
import model.ReadJSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View extends JFrame {

	private JFrame frame;
	private JTextField textFieldVillainLife;
	private JTextField textFieldHeroLife1;
	private JTextField textFieldHeroLife2;
	private JTextField textFieldHeroLife3;
	private JTextField textHero1Name;
	private JTextField textHero2Name;
	private JTextField textHero3Name;
	private JTextField textVillianName;
	private JButton btnStartBattle;
	private JButton btnNewBattle;
	private JButton btnCreateTeam;
	private JComboBox comboBoxHero;
	private JComboBox comboBoxVillain;
	private JComboBox comboBoxPlanet;
	private JTextArea textArea;
	private ReadJSON json=new ReadJSON();
	private JPanel panel_1;
	private JPanel panel_2;

	public View() {

		initialize();
	}
	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 232);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblHero = new JLabel("Hero:");
		lblHero.setBounds(10, 11, 46, 14);
		panel.add(lblHero);

		JLabel lblVillain = new JLabel("Villain:");
		lblVillain.setBounds(86, 11, 46, 14);
		panel.add(lblVillain);

		JLabel lblPlanet = new JLabel("Planet:");
		lblPlanet.setBounds(164, 11, 46, 14);
		panel.add(lblPlanet);

		comboBoxHero = new JComboBox();
		DefaultComboBoxModel cbm = new DefaultComboBoxModel(this.getHeros(this.json.readCharacters()).toArray());
		comboBoxHero.setModel(cbm);
		comboBoxHero.setBounds(10, 28, 68, 20);
		panel.add(comboBoxHero);

		comboBoxVillain = new JComboBox();
		DefaultComboBoxModel cbmV = new DefaultComboBoxModel(this.getVillains(this.json.readCharacters()).toArray());
		comboBoxVillain.setModel(cbmV);
		comboBoxVillain.setBounds(86, 28, 68, 20);
		panel.add(comboBoxVillain);

		comboBoxPlanet = new JComboBox();
		DefaultComboBoxModel cbmP = new DefaultComboBoxModel(json.readPlantes().toArray());
		comboBoxPlanet.setModel(cbmP);
		comboBoxPlanet.setBounds(164, 28, 68, 20);
		panel.add(comboBoxPlanet);

		btnStartBattle = new JButton("Start Battle");
		btnStartBattle.setBackground(Color.LIGHT_GRAY);
		btnStartBattle.setBounds(258, 41, 120, 23);
		panel.add(btnStartBattle);

		textFieldVillainLife = new JTextField();
		textFieldVillainLife.setBackground(Color.GREEN);
		textFieldVillainLife.setBounds(288, 102, 50, 20);
		panel.add(textFieldVillainLife);
		textFieldVillainLife.setColumns(10);

		btnNewBattle = new JButton("New Battle");
		btnNewBattle.setBackground(Color.LIGHT_GRAY);
		btnNewBattle.setBounds(258, 7, 120, 23);
		panel.add(btnNewBattle);


		JPanel panelHero1 = new JPanel();
		panelHero1.setBounds(10, 76, 76, 154);
		panel.add(panelHero1);
		panelHero1.setLayout(null);

		textFieldHeroLife1 = new JTextField();
		textFieldHeroLife1.setBackground(Color.GREEN);
		textFieldHeroLife1.setBounds(0, 28, 50, 20);
		panelHero1.add(textFieldHeroLife1);
		textFieldHeroLife1.setColumns(10);

		JLabel lblHero1 = new JLabel(new ImageIcon("hero1.png"));
		lblHero1.setBounds(0, 56, 50, 98);
		panelHero1.add(lblHero1);

		textHero1Name = new JTextField();
		textHero1Name.setBounds(0, 0, 71, 20);
		panelHero1.add(textHero1Name);
		textHero1Name.setColumns(10);

		panel_1 = new JPanel();
		panel_1.setBounds(86, 76, 76, 154);
		panel.add(panel_1);
		panel_1.setLayout(null);

		textFieldHeroLife2 = new JTextField();
		textFieldHeroLife2.setBackground(Color.GREEN);
		textFieldHeroLife2.setBounds(1, 28, 50, 20);
		panel_1.add(textFieldHeroLife2);
		textFieldHeroLife2.setColumns(10);

		JLabel lblHero2 = new JLabel(new ImageIcon("hero2.png"));
		lblHero2.setBounds(1, 55, 50, 98);
		panel_1.add(lblHero2);

		textHero2Name = new JTextField();
		textHero2Name.setBounds(0, 1, 71, 20);
		panel_1.add(textHero2Name);
		textHero2Name.setColumns(10);

		panel_2 = new JPanel();
		panel_2.setBounds(164, 76, 76, 152);

		panel.add(panel_2);
		panel_2.setLayout(null);

		textFieldHeroLife3 = new JTextField();
		textFieldHeroLife3.setBackground(Color.GREEN);
		textFieldHeroLife3.setBounds(0, 26, 50, 20);
		panel_2.add(textFieldHeroLife3);
		textFieldHeroLife3.setColumns(10);

		JLabel lblHero3 = new JLabel(new ImageIcon("hero3.png"));
		lblHero3.setBounds(2, 53, 50, 98);
		panel_2.add(lblHero3);

		textHero3Name = new JTextField();
		textHero3Name.setBounds(0, 2, 71, 20);
		panel_2.add(textHero3Name);
		textHero3Name.setColumns(10);

		JLabel lblVillain1 = new JLabel(new ImageIcon("villain.png"));
		lblVillain1.setBounds(261, 131, 121, 98);
		panel.add(lblVillain1);

		textVillianName = new JTextField();
		textVillianName.setBounds(286, 77, 86, 20);
		panel.add(textVillianName);
		textVillianName.setColumns(10);

		btnCreateTeam = new JButton("Create Team");
		btnCreateTeam.setBounds(10, 52, 222, 20);
		panel.add(btnCreateTeam);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 250, 414, 126);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 404, 104);
		panel_3.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

	public void ActionStartBattle(ActionListener a) {
		this.btnStartBattle.addActionListener(a);

	}

	public void ActionNewBattle(ActionListener a) {
		this.btnNewBattle.addActionListener(a);

	}

	public void ActionCreateTeam(ActionListener a) {
		this.btnCreateTeam.addActionListener(a);

	}

	public void ActionComboHero(ActionListener a){
		this.comboBoxHero.addActionListener(a);
	}
	public void ActionComboPlanet(ActionListener a){
		this.comboBoxPlanet.addActionListener(a);
	}
	public void ActionComboVillain(ActionListener a){
		this.comboBoxVillain.addActionListener(a);
	}
	public Character getComboBoxHero() {
		return (Character) comboBoxHero.getSelectedItem();
	}

	public ArrayList<Character> getHeros(ArrayList<Character> characters) {
		ArrayList<Character> heros=new ArrayList<>();
		for (int i = 0; i < characters.size(); i++) {
			if (!characters.get(i).isVillain()) {
				heros.add(characters.get(i));
			}
		}
		return heros;
	}

	public Character getComboBoxVillain() {

		return (Character) comboBoxVillain.getSelectedItem();
	}

	public ArrayList<Character> getVillains(ArrayList<Character> characters) {
		ArrayList<Character> villains=new ArrayList<>();
		for (int i = 0; i < characters.size(); i++) {
			if (characters.get(i).isVillain()) {
				villains.add(characters.get(i));
			}
		}
		return villains;
	}

	public Planet getComboBoxPlanet() {

		return (Planet) comboBoxPlanet.getSelectedItem();
	}

	public JTextField getTextFieldVillainLife() {
		return textFieldVillainLife;
	}

	public void setTextFieldVillainLife(JTextField textFieldVillainLife) {
		this.textFieldVillainLife = textFieldVillainLife;
	}

	public JTextField getTextFieldHeroLife1() {
		return textFieldHeroLife1;
	}

	public void setTextFieldHeroLife1(JTextField textFieldHeroLife1) {
		this.textFieldHeroLife1 = textFieldHeroLife1;
	}

	public JTextField getTextFieldHeroLife2() {
		return textFieldHeroLife2;
	}

	public void setTextFieldHeroLife2(JTextField textFieldHeroLife2) {
		this.textFieldHeroLife2 = textFieldHeroLife2;
	}

	public JTextField getTextFieldHeroLife3() {
		return textFieldHeroLife3;
	}

	public void setTextFieldHeroLife3(JTextField textFieldHeroLife3) {
		this.textFieldHeroLife3 = textFieldHeroLife3;
	}

	public JTextField getTextHero1Name() {
		return textHero1Name;
	}

	public void setTextHero1Name(JTextField textHero1Name) {
		this.textHero1Name = textHero1Name;
	}

	public JTextField getTextHero2Name() {
		return textHero2Name;
	}

	public void setTextHero2Name(JTextField textHero2Name) {
		this.textHero2Name = textHero2Name;
	}

	public JTextField getTextHero3Name() {
		return textHero3Name;
	}

	public void setTextHero3Name(JTextField textHero3Name) {
		this.textHero3Name = textHero3Name;
	}

	public JTextField getTextVillianName() {
		return textVillianName;
	}

	public void setTextVillianName(JTextField textVillianName) {
		this.textVillianName = textVillianName;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

	public JPanel getPanel_2() {
		return panel_2;
	}

	public void setPanel_2(JPanel panel_2) {
		this.panel_2 = panel_2;
	}
}
