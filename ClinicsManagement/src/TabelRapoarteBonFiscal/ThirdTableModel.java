package TabelRapoarteBonFiscal;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import Models.ButtonColumn;

public class ThirdTableModel extends AbstractTableModel {

	private List<RapoarteBonFiscal> raports;
	private JButton button = new JButton("Raport");
	String[] columnNames = { "Raport", "Data raport","Stare factura"," " };

	public void setRaports(List<RapoarteBonFiscal> raports) {
		this.raports = raports;
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
		return raports.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RapoarteBonFiscal currentRaport = (RapoarteBonFiscal) raports.toArray()[rowIndex];
		switch (columnIndex) {
		case 0:
			return currentRaport.getServiciu();
		case 1:
			return currentRaport.getDate();
		case 2:
			if(currentRaport.getPlataEfectuata()==true) {
				return "Platit";
			}else {
				return "Neplatit";
			}
		case 3:
			if(currentRaport.getPlataEfectuata()==false) {
				return "Emitere bon fiscal";
			}else {
				return "Bonul a fost emis";
			}
		}
		return null;
	}

}
