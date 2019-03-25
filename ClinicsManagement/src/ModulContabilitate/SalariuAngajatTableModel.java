package ModulContabilitate;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import Models.ButtonColumn;
import TabelRapoarteBonFiscal.RapoarteBonFiscal;

public class SalariuAngajatTableModel extends AbstractTableModel {

	private List<salariuAngajatModel> salarii;

	String[] columnNames = { "Luna", "Salar" };

	public void setSalarii(List<salariuAngajatModel> salarii) {
		this.salarii = salarii;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public Class<?> getColumnClass(int col) {
		return String.class;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return salarii.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		salariuAngajatModel currentSalary = (salariuAngajatModel) salarii.toArray()[rowIndex];
		switch (columnIndex) {
		case 0:
			return currentSalary.getMonth();
		case 1:
			return currentSalary.getDate();
		}
		return null;
	}

}
