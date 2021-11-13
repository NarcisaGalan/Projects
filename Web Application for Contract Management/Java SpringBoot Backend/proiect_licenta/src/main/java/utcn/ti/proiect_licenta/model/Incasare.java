package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "incasare")
public class Incasare {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_incasare", unique = true, nullable = false)
    private Integer idIncasare;
    @Column(name = "nrDocument",length = 20,nullable = false)
    private String nrDocument;
    @Column(name = "dataDocument",nullable = false)
    private Date dataDocument;
    @Column(name = "tipDocument",length = 10)
    private String tipDocument;
    @Column(name = "valoareIncasata",nullable = false)
    private Double valoareIncasata;
    @Column(name = "valoareTVA")
    private float valoareTVA;
    @Column(name = "moneda",length = 3)
    private String moneda ;
    @Column(name = "explicatii",length = 200)
    private String explicatii;
    @Column(name = "incadrareIncasare",length = 10)
    private String incadrareIncasare;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "contract_incasare",  nullable=false)
    private ContractTerti contractTerti;

    public Incasare(){}

    public Incasare(String nrDocument, Date dataDocument, String tipDocument, Double valoareIncasata, float valoareTVA, String moneda, String explicatii, String incadrareIncasare) {
        this.nrDocument = nrDocument;
        this.dataDocument = dataDocument;
        this.tipDocument = tipDocument;
        this.valoareIncasata = valoareIncasata;
        this.valoareTVA = valoareTVA;
        this.moneda = moneda;
        this.explicatii = explicatii;
        this.incadrareIncasare = incadrareIncasare;

    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
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

    public Double getValoareIncasata() {
        return valoareIncasata;
    }

    public void setValoareIncasata(Double valoareIncasata) {
        this.valoareIncasata = valoareIncasata;
    }

    public float getValoareTVA() {
        return valoareTVA;
    }

    public void setValoareTVA(float valoareTVA) {
        this.valoareTVA = valoareTVA;
    }




    public String getExplicatii() {
        return explicatii;
    }

    public void setExplicatii(String explicatii) {
        this.explicatii = explicatii;
    }

    public String getIncadrareIncasare() {
        return incadrareIncasare;
    }

    public void setIncadrareIncasare(String incadrareIncasare) {
        this.incadrareIncasare = incadrareIncasare;
    }

    public ContractTerti getContractTerti() {
        return contractTerti;
    }

    public void setContractTerti(ContractTerti contractTerti) {
        this.contractTerti = contractTerti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incasare incasare = (Incasare) o;
        return idIncasare == incasare.idIncasare &&
                Objects.equals(nrDocument, incasare.nrDocument) &&
                Objects.equals(dataDocument, incasare.dataDocument) &&
                Objects.equals(tipDocument, incasare.tipDocument) &&
                Objects.equals(valoareIncasata, incasare.valoareIncasata) &&
                Objects.equals(valoareTVA, incasare.valoareTVA) &&
                Objects.equals(moneda, incasare.moneda) &&
                Objects.equals(explicatii, incasare.explicatii) &&
                Objects.equals(incadrareIncasare, incasare.incadrareIncasare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIncasare, nrDocument, dataDocument, tipDocument, valoareIncasata, valoareTVA, moneda, explicatii, incadrareIncasare);
    }
}
