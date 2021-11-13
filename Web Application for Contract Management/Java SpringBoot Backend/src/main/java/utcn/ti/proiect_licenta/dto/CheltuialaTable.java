package utcn.ti.proiect_licenta.dto;

import java.sql.Date;

public class CheltuialaTable {
    private String nrDocument;
    private Date dataDocument;
    private String tipDocument;
    private Double valoareCheltuiala;
    private float valoareTva;
    private String moneda;
    private String explicatii;
    private String incadrareCheltuiala;
    private Double valoareInLei;


    public CheltuialaTable(String nrDocument, Date dataDocument, String tipDocument, Double valoareCheltuiala, float valoareTva, String moneda, String explicatii, String incadrareCheltuiala, Double valoareInLei) {
        this.nrDocument = nrDocument;
        this.dataDocument = dataDocument;
        this.tipDocument = tipDocument;
        this.valoareCheltuiala = valoareCheltuiala;
        this.valoareTva = valoareTva;
        this.moneda = moneda;
        this.explicatii = explicatii;
        this.incadrareCheltuiala = incadrareCheltuiala;
        this.valoareInLei = valoareInLei;
    }

    public Double getValoareInLei() {
        return valoareInLei;
    }

    public void setValoareInLei(Double valoareInLei) {
        this.valoareInLei = valoareInLei;
    }

    public String getNrDocument() {
        return nrDocument;
    }

    public void setNrDocument(String nrDocument) {
        this.nrDocument = nrDocument;
    }

    public Date getDataDocument() {
        return dataDocument;
    }

    public void setDataDocument(Date dataDocument) {
        this.dataDocument = dataDocument;
    }

    public String getTipDocument() {
        return tipDocument;
    }

    public void setTipDocument(String tipDocument) {
        this.tipDocument = tipDocument;
    }

    public Double getValoareCheltuiala() {
        return valoareCheltuiala;
    }

    public void setValoareCheltuiala(Double valoareCheltuiala) {
        this.valoareCheltuiala = valoareCheltuiala;
    }

    public float getValoareTva() {
        return valoareTva;
    }

    public void setValoareTva(float valoareTva) {
        this.valoareTva = valoareTva;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getExplicatii() {
        return explicatii;
    }

    public void setExplicatii(String explicatii) {
        this.explicatii = explicatii;
    }

    public String getIncadrareCheltuiala() {
        return incadrareCheltuiala;
    }

    public void setIncadrareCheltuiala(String incadrareCheltuiala) {
        this.incadrareCheltuiala = incadrareCheltuiala;
    }
}
