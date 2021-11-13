package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.sql.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cheltuiala")
public class Cheltuiala {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_cheltuiala", unique = true, nullable = false)
    private int idCheltuiala;
    @Column(name = "nrDocument", length = 20, nullable = false)
    private String nrDocument;
    @Column(name = "dataDocument")
    private Date dataDocument;
    @Column(name = "tipDocument", length = 10)
    private String tipDocument;
    @Column(name = "valoareCheltuiala", nullable = false)
    private Double valoareCheltuiala;
    @Column(name ="valoareTVA")
    private float valoareTva;
    @Column(name = "moneda", length = 3)
    private String moneda;
    @Column(name = "explicatii",length = 200)
    private String explicatii;
    @Column(name = "incadrareCheltuiala",length = 10)
    private String incadrareCheltuiala;
    @Column(name="valoareInLei")
    private Double valoareInLei;
    @Column(name="valoareCursValutar")
    private Float valoareCursValutar;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="contract_cheltuiala", nullable=false)
    private ContractTerti contractTerti;

    public Cheltuiala(){}

    public Cheltuiala(String nrDocument, Date dataDocument, String tipDocument, Double valoareCheltuiala, float valoareTva, String moneda, String explicatii, String incadrareCheltuiala, Double valoareInLei) {
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

    public Cheltuiala(String nrDocument, Date dataDocument, String tipDocument, Double valoareCheltuiala, float valoareTva, String moneda, String explicatii, String incadrareCheltuiala) {
        this.nrDocument = nrDocument;
        this.dataDocument = dataDocument;
        this.tipDocument = tipDocument;
        this.valoareCheltuiala = valoareCheltuiala;
        this.valoareTva = valoareTva;
        this.moneda = moneda;
        this.explicatii = explicatii;
        this.incadrareCheltuiala = incadrareCheltuiala;
    }

    public Float getValoareCursValutar() {
        return valoareCursValutar;
    }

    public void setValoareCursValutar(Float valoareCursValutar) {
        this.valoareCursValutar = valoareCursValutar;
    }

    public Double getValoareInLei() {
        return valoareInLei;
    }

    public void setValoareInLei(Double valoareInLei) {
        this.valoareInLei = valoareInLei;
    }

    public ContractTerti getContractTerti() {
        return contractTerti;
    }

    public void setContractTerti(ContractTerti contractTerti) {
        this.contractTerti = contractTerti;
    }

    public int getIdCheltuiala() {
        return idCheltuiala;
    }

    public void setIdCheltuiala(int idCheltuiala) {
        this.idCheltuiala = idCheltuiala;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cheltuiala that = (Cheltuiala) o;
        return idCheltuiala == that.idCheltuiala &&
                Objects.equals(nrDocument, that.nrDocument) &&
                Objects.equals(dataDocument, that.dataDocument) &&
                Objects.equals(tipDocument, that.tipDocument) &&
                Objects.equals(valoareCheltuiala, that.valoareCheltuiala) &&
                Objects.equals(valoareTva, that.valoareTva) &&
                Objects.equals(moneda, that.moneda) &&
                Objects.equals(explicatii, that.explicatii) &&
                Objects.equals(incadrareCheltuiala, that.incadrareCheltuiala);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCheltuiala, nrDocument, dataDocument, tipDocument, valoareCheltuiala, valoareTva, moneda, explicatii, incadrareCheltuiala);
    }
}
