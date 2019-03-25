package TabelProgramariMedicZiCurenta;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import Models.ButtonColumn;

public class MyTableModel extends AbstractTableModel {

	private List<Pacient> pacients;
	private JButton button = new JButton("Raport");
	String[] columnNames = { "Nume pacient", "Ora programare", "Procedura", "Adaugare raport" };

	public void setPacients(List<Pacient> pacients) {
		this.pacients = pacients;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public Class<?> getColumnClass(int col) {
		if (col < 3) {
			return String.class;
		} else {
			return ButtonColumn.class;
		}
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return pacients.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pacient currentPerson = (Pacient) pacients.toArray()[rowIndex];
		switch (columnIndex) {
		case 0:
			return currentPerson.getName();
		case 1:
			return currentPerson.getTime();
		case 2:
			return currentPerson.getServiciu();
		case 3:
			return "Raport medical";
		}
		return null;
	}

}
