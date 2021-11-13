package utcn.ti.proiect_licenta.dto;

import utcn.ti.proiect_licenta.model.Departament;
import java.sql.Date;

public class StatFunctiiAndAngajatDTO {

	private Integer idAngajat;
	private String nume;
	private String codDepartament;
	private Integer idFunctie;
	private String functie;
	private Date dataInceput;
	private Date dataSfarsit;
	private Boolean incetareActivitate;
	private Integer contractTertiId;


	public StatFunctiiAndAngajatDTO(Integer idAngajat, String nume, String codDepartament, String functie, Date dataInceput, Date dataSfarsit,Boolean incetareActivitate,Integer contractTertiId) {
		this.idAngajat=idAngajat;
		this.nume = nume;
		this.codDepartament = codDepartament;
		this.functie = functie;
		this.dataInceput = dataInceput;
		this.dataSfarsit = dataSfarsit;
		this.incetareActivitate=incetareActivitate;
		this.contractTertiId=contractTertiId;
	}

	public StatFunctiiAndAngajatDTO(Integer idAngajat, String nume, String codDepartament, Integer idFunctie, String functie, Date dataInceput, Date dataSfarsit, Boolean incetareActivitate, Integer contractTertiId) {
		this.idAngajat = idAngajat;
		this.nume = nume;
		this.codDepartament = codDepartament;
		this.idFunctie = idFunctie;
		this.functie = functie;
		this.dataInceput = dataInceput;
		this.dataSfarsit = dataSfarsit;
		this.incetareActivitate = incetareActivitate;
		this.contractTertiId = contractTertiId;
	}

	public Integer getIdFunctie() {
		return idFunctie;
	}

	public void setIdFunctie(Integer idFunctie) {
		this.idFunctie = idFunctie;
	}

	public Integer getIdAngajat() {
		return idAngajat;
	}

	public void setIdAngajat(Integer idAngajat) {
		this.idAngajat = idAngajat;
	}

	public Boolean getIncetareActivitate() {
		return incetareActivitate;
	}

	public void setIncetareActivitate(Boolean incetareActivitate) {
		this.incetareActivitate = incetareActivitate;
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
