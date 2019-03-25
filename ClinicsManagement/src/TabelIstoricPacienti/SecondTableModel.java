package TabelIstoricPacienti;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import Models.ButtonColumn;

public class SecondTableModel extends AbstractTableModel {

	private List<RaportInitial> raports;
	private JButton button = new JButton("Raport");
	String[] columnNames = { "Raport", "Data raport"," "," " };

	public void setRaports(List<RaportInitial> raports) {
		this.raports = raports;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public Class<?> getColumnClass(int col) {
		if (col < 2) {
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
		return raports.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RaportInitial currentRaport = (RaportInitial) raports.toArray()[rowIndex];
		switch (columnIndex) {
		case 0:
			return currentRaport.getServiciu();
		case 1:
			return currentRaport.getDate();
		case 2:
			return "Vizualizeaza raport medical";
		case 3:
			return "Vizualizeaza analiza";
		}
		return null;
	}

}
