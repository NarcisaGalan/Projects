package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cerere_imprumut")
public class CerereImprumut {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_cerere_imprumut", unique = true, nullable = false)
    private int idCerereImprumut;
    @Column(name = "numar",nullable = false)
    private Integer numar;
    @Column(name = "data",nullable = false)
    private Date data;
    @Column(name="anul")
    private Integer anul;
    @Column(name = "etapa",length = 10)
    private String etapa;
    @Column(name="valoareaTotala")
    private Double valoareaTotala;
    @Column(name = "salarii")
    private Double salarii;
    @Column(name = "achizitii")
    private Double achizitii;
    @Column(name = "deplasari")
    private Double deplasari;
    @Column(name="taxe")
    private Double taxe;
    @Column(name = "dataReturnarii")
    private Date dataReturnarii;
    @Column(name = "aprobata")
    private Boolean aprobata;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="contract_imprumut", nullable=false)
    private ContractTerti contractTerti;

    public CerereImprumut(){}

    public CerereImprumut(Integer numar, Date data, Integer anul, String etapa, Double valoareaTotala, Double salarii, Double achizitii, Double deplasari, Double taxe, Date dataReturnarii, Boolean aprobata) {
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
    }



    public ContractTerti getContractTerti() {
        return contractTerti;
    }

    public void setContractTerti(ContractTerti contractTerti) {
        this.contractTerti = contractTerti;
    }

    public int getIdCerereImprumut() {
        return idCerereImprumut;
    }

    public void setIdCerereImprumut(int idCerereImprumut) {
        this.idCerereImprumut = idCerereImprumut;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CerereImprumut that = (CerereImprumut) o;
        return idCerereImprumut == that.idCerereImprumut &&
                Objects.equals(numar, that.numar) &&
                Objects.equals(data, that.data) &&
                Objects.equals(anul, that.anul) &&
                Objects.equals(etapa, that.etapa) &&
                Objects.equals(valoareaTotala, that.valoareaTotala) &&
                Objects.equals(salarii, that.salarii) &&
                Objects.equals(achizitii, that.achizitii) &&
                Objects.equals(deplasari, that.deplasari) &&
                Objects.equals(taxe, that.taxe) &&
                Objects.equals(dataReturnarii, that.dataReturnarii) &&
                Objects.equals(aprobata, that.aprobata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCerereImprumut, numar, data, anul, etapa, valoareaTotala, salarii, achizitii, deplasari, taxe, dataReturnarii, aprobata);
    }
}
