package utcn.ti.proiect_licenta.dto;

import utcn.ti.proiect_licenta.model.Departament;
import java.sql.Date;

public class StatFunctiiAndAngajatDTO {

	private String nume;
	private String codDepartament;
	private String functie;
	private Date dataInceput;
	private Date dataSfarsit;
	private Integer contractTertiId;

	public StatFunctiiAndAngajatDTO(){};

	public StatFunctiiAndAngajatDTO(Integer idAngajat, String nume, String codDepartament,Integer idFunctie, String functie, Date dataInceput, Date dataSfarsit,Integer contractTertiId) {

		this.nume = nume;
		this.codDepartament = codDepartament;
		this.functie = functie;
		this.dataInceput = dataInceput;
		this.dataSfarsit = dataSfarsit;
		this.contractTertiId=contractTertiId;
	}

	public Integer getContractTertiId() {
		return contractTertiId;
	}

	public void setContractTertiId(Integer contractTertiId) {
		this.contractTertiId = contractTertiId;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getCodDepartament() {
		return codDepartament;
	}

	public void setCodDepartament(String codDepartament) {
		this.codDepartament = codDepartament;
	}

	public String getFunctie() {
		return functie;
	}

	public void setFunctie(String functie) {
		this.functie = functie;
	}

	public Date getDataInceput() {
		return dataInceput;
	}

	public void setDataInceput(Date dataInceput) {
		this.dataInceput = dataInceput;
	}

	public Date getDataSfarsit() {
		return dataSfarsit;
	}

	public void setDataSfarsit(Date dataSfarsit) {
		this.dataSfarsit = dataSfarsit;
	}
}
