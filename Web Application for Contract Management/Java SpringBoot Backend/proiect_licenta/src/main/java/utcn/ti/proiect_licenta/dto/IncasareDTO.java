package utcn.ti.proiect_licenta.dto;

import java.sql.Date;

public class IncasareDTO {
	private String titluProiect;
	private String  nrDocument ;
	private Date dataDocument ;
	private String  tipDocument ;
	private Double  valoareIncasata ;
	private  float  valoareTVA ;
	private String  moneda ;
	private String  explicatii ;
	private String  incadrareIncasare ;

	public IncasareDTO(String titluProiect, String nrDocument, Date dataDocument, String tipDocument, Double valoareIncasata, float valoareTVA, String moneda, String explicatii, String incadrareIncasare) {
		this.titluProiect = titluProiect;
		this.nrDocument = nrDocument;
		this.dataDocument = dataDocument;
		this.tipDocument = tipDocument;
		this.valoareIncasata = valoareIncasata;
		this.valoareTVA = valoareTVA;
		this.moneda = moneda;
		this.explicatii = explicatii;
		this.incadrareIncasare = incadrareIncasare;
	}

	public String getTitluProiect() {
		return titluProiect;
	}

	public String getNrDocument() {
		return nrDocument;
	}

	public Date getDataDocument() {
		return dataDocument;
	}

	public String getTipDocument() {
		return tipDocument;
	}

	public Double getValoareIncasata() {
		return valoareIncasata;
	}

	public float getValoareTVA() {
		return valoareTVA;
	}

	public String getMoneda() {
		return moneda;
	}

	public String getExplicatii() {
		return explicatii;
	}

	public String getIncadrareIncasare() {
		return incadrareIncasare;
	}
}
