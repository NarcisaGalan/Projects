package TabelIstoricPacienti;

import java.util.ArrayList;
import java.util.List;

public class RaportInitial {

	private String date;
	private List<String> serviciu = new ArrayList();
	private int id;
	private int idPacient;
	private int idMedic;

	public RaportInitial(String date, int id,int idPac,int idMed) {
		this.id = id;
		this.date = date;
		this.idPacient=idPac;
		this.idMedic=idMed;
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
}
