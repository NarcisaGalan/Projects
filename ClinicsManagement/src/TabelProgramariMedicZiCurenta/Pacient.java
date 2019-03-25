package TabelProgramariMedicZiCurenta;

import java.util.ArrayList;
import java.util.List;

public class Pacient {

	private String name;
	private String time;
	private List<String> serviciu = new ArrayList();
	private int id;

	public Pacient(String name, int id, String time) {
		this.name = name;
		this.id = id;
		this.time = time;
		int i = 0;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public int getId() {
		return id;
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
