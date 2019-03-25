package TabelRapoarteBonFiscal;

import java.util.ArrayList;
import java.util.List;

public class RapoarteBonFiscal{

	private String date;
	private List<String> serviciu = new ArrayList();
	private int id;
	private int idPacient;
	private int idMedic;
	private int PlataEfectuata;

	public RapoarteBonFiscal(String date, int id,int idPac,int idMed,int PL) {
		this.id = id;
		this.date = date;
		this.idPacient=idPac;
		this.idMedic=idMed;
		if(PL==1) {
			PlataEfectuata=1;
		}else {
			PlataEfectuata=0;
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}
	public int getIdMedic() {
		return idMedic;
	}
	public int getIdPacient() {
		return idPacient;
	}

	public List<String> getServiciu() {
		return serviciu;
	}

	public void addServiciu(String serv) {
		if (serv != null) {
			this.serviciu.add(serv);
		}
	}
	
	public boolean getPlataEfectuata() {
		if(this.PlataEfectuata!=1) {
			return false;
		}else {
			return true;
		}
		
				
	}
}
