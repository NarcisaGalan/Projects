package utcn.ti.proiect_licenta.dto;


import java.sql.Date;

public class CerereImprumutDTO {

	private Integer numar;
	private Date data;
	private Integer anul;
	private String etapa;
	private Double valoareaTotala;
	private Double salarii;
	private Double achizitii;
	private Double deplasari;
	private Double taxe;
	private Date dataReturnarii;
	private Boolean aprobata;
	private String titluProiect;
	private  Integer idUniversitate;
	private  Integer idFacultate;
	private String numeDepartament;

	public CerereImprumutDTO(){}

	public CerereImprumutDTO( Integer numar, Date data, Integer anul, String etapa, Double valoareaTotala, Double salarii, Double achizitii, Double deplasari, Double taxe, Date dataReturnarii, Boolean aprobata, String titluProiect) {

		this.numar = numar;
		this.data = data;
		this.anul = anul;
		this.etapa = etapa;
		this.valoareaTotala = valoareaTotala;
		this.salarii = salarii;
		this.achizitii = achizitii;
		this.deplasari = deplasari;
		this.taxe = taxe;
		this.dataReturnarii = dataReturnarii;
		this.aprobata = aprobata;
		this.titluProiect = titluProiect;
	}

	public Integer getIdUniversitate() {
		return idUniversitate;
	}

	public void setIdUniversitate(Integer idUniversitate) {
		this.idUniversitate = idUniversitate;
	}

	public Integer getIdFacultate() {
		return idFacultate;
	}

	public void setIdFacultate(Integer idFacultate) {
		this.idFacultate = idFacultate;
	}

	public String getNumeDepartament() {
		return numeDepartament;
	}

	public void setNumeDepartament(String numeDepartament) {
		this.numeDepartament = numeDepartament;
	}

	public Integer getNumar() {
		return numar;
	}

	public void setNumar(Integer numar) {
		this.numar = numar;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getAnul() {
		return anul;
	}

	public void setAnul(Integer anul) {
		this.anul = anul;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public Double getValoareaTotala() {
		return valoareaTotala;
	}

	public void setValoareaTotala(Double valoareaTotala) {
		this.valoareaTotala = valoareaTotala;
	}

	public Double getSalarii() {
		return salarii;
	}

	public void setSalarii(Double salarii) {
		this.salarii = salarii;
	}

	public Double getAchizitii() {
		return achizitii;
	}

	public void setAchizitii(Double achizitii) {
		this.achizitii = achizitii;
	}

	public Double getDeplasari() {
		return deplasari;
	}

	public void setDeplasari(Double deplasari) {
		this.deplasari = deplasari;
	}

	public Double getTaxe() {
		return taxe;
	}

	public void setTaxe(Double taxe) {
		this.taxe = taxe;
	}

	public Date getDataReturnarii() {
		return dataReturnarii;
	}

	public void setDataReturnarii(Date dataReturnarii) {
		this.dataReturnarii = dataReturnarii;
	}

	public Boolean getAprobata() {
		return aprobata;
	}

	public void setAprobata(Boolean aprobata) {
		this.aprobata = aprobata;
	}

	public String getTitluProiect() {
		return titluProiect;
	}

	public void setTitluProiect(String titluProiect) {
		this.titluProiect = titluProiect;
	}
}
