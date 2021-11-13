package utcn.ti.proiect_licenta.dto;

import java.sql.Date;

public class CheltuialaDTO {
	private String titluProiect;
	private String numarDocument;
	private String tipDocument;
	private Date dataCheltuiala;
	private String incadrareCheltuiala;
	private String explicatii;
	private String moneda;
	private double valoareCheltuiala;
	private float valoareTVA;

	public CheltuialaDTO(String titluProiect, String numarDocument, String tipDocument, Date dataCheltuiala, String incadrareCheltuiala, String explicatii, String moneda, double valoareCheltuiala, Integer valoareTVA) {
		this.titluProiect = titluProiect;
		this.numarDocument = numarDocument;
		this.tipDocument = tipDocument;
		this.dataCheltuiala = dataCheltuiala;
		this.incadrareCheltuiala = incadrareCheltuiala;
		this.explicatii = explicatii;
		this.moneda = moneda;
		this.valoareCheltuiala = valoareCheltuiala;
		this.valoareTVA = valoareTVA;
	}

	public String getTitluProiect() {
		return titluProiect;
	}

	public String getNumarDocument() {
		return numarDocument;
	}

	public String getTipDocument() {
		return tipDocument;
	}

	public Date getDataCheltuiala() {
		return dataCheltuiala;
	}

	public String getIncadrareCheltuiala() {
		return incadrareCheltuiala;
	}

	public String getExplicatii() {
		return explicatii;
	}

	public String getMoneda() {
		return moneda;
	}

	public double getValoareCheltuiala() {
		return valoareCheltuiala;
	}

	public float getValoareTVA() {
		return valoareTVA;
	}
}
