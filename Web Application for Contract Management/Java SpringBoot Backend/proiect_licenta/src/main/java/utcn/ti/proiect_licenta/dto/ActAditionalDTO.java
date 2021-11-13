package utcn.ti.proiect_licenta.dto;


import java.sql.Date;

public class ActAditionalDTO {
	private Integer numar;
	private Date dataAct;
	private Date dataInceput;
	private Date dataSfarsit;
	private String titluProiect;

	public ActAditionalDTO(Integer numar, Date dataAct, Date dataInceput, Date dataSfarsit, String titluProiect) {
		this.numar = numar;
		this.dataAct = dataAct;
		this.dataInceput = dataInceput;
		this.dataSfarsit = dataSfarsit;
		this.titluProiect = titluProiect;
	}

	public Integer getNumar() {
		return numar;
	}

	public void setNumar(Integer numar) {
		this.numar = numar;
	}

	public Date getDataAct() {
		return dataAct;
	}

	public void setDataAct(Date dataAct) {
		this.dataAct = dataAct;
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

	public void setDataSfarsit(Date dataSfasit) {
		this.dataSfarsit = dataSfasit;
	}

	public String getTitluProiect() {
		return titluProiect;
	}

	public void setTitluProiect(String titluProiect) {
		this.titluProiect = titluProiect;
	}
}
