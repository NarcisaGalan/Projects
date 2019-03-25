package ModulContabilitate;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Spring;

public class salariuAngajatModel{

	private int salary;
	private int month;
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public salariuAngajatModel(int month,int salary) {
		this.salary=salary;
		this.setMonth(month);
	}

	public int getDate() {
		return salary;
	}

	public void setDate(int date) {
		this.salary = date;
	}

	public String getMonth() {
		
		return new DateFormatSymbols().getMonths()[month-1].toString();
	}

	public void setMonth(int month) {
		this.month=month;
	}

	
	
}
