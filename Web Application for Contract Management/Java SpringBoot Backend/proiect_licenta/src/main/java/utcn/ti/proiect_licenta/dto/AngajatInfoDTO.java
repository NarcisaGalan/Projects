package utcn.ti.proiect_licenta.dto;

import utcn.ti.proiect_licenta.model.Departament;
import utcn.ti.proiect_licenta.model.Facultate;
import utcn.ti.proiect_licenta.model.Universitate;

public class AngajatInfoDTO {
	private Integer idAngajat;
	private String numeAngajat;
	private Departament departament;
	private Facultate facultate;
	private Universitate universitate;

	public AngajatInfoDTO(){}

	public AngajatInfoDTO(Integer idAngajat, String numeAngajat, Departament departament, Facultate facultate, Universitate universitate) {
		this.idAngajat = idAngajat;
		this.numeAngajat = numeAngajat;
		this.departament = departament;
		this.facultate = facultate;
		this.universitate = universitate;
	}

	public Integer getIdAngajat() {
		return idAngajat;
	}

	public void setIdAngajat(Integer idAngajat) {
		this.idAngajat = idAngajat;
	}

	public String getNumeAngajat() {
		return numeAngajat;
	}

	public void setNumeAngajat(String numeAngajat) {
		this.numeAngajat = numeAngajat;
	}

	public Departament getDepartament() {
		return departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

	public Facultate getFacultate() {
		return facultate;
	}

	public void setFacultate(Facultate facultate) {
		this.facultate = facultate;
	}

	public Universitate getUniversitate() {
		return universitate;
	}

	public void setUniversitate(Universitate universitate) {
		this.universitate = universitate;
	}
}
