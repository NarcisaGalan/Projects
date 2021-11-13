package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "contract_terti")
public class ContractTerti {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_contract_terti", unique = true, nullable = false)
    private int idContractTerti;
    @Column(name = "numar",nullable = false,unique = true)
    private Integer numar;
    @Column(name = "data",nullable = false)
    private Date data;
    @Column(name = "tip",length = 10)
    private String tip;
    @Column(name = "titluProiect",length = 200,nullable = false)
    private String titluProiect;
    @Column(name = "valoare",nullable = false)
    private Double valoare;
    @Column(name = "moneda",length = 3)
    private String moneda;
    @Column(name = "dataIncheierii")
    private Date dataIncheierii;
    @Column(name="nrPagini")
    private Integer nrPagini;
    @Column(name = "nrExemplare")
    private Integer nrExemplare;
    @Column(name = "nrExemplareBeneficiar")
    private Integer nrExemplareBeneficiar;
    @Column(name = "dataInceput")
    private Date dataInceput;
    @Column(name = "dataSfarsit")
    private Date dataSfarsit;
    @Column(name = "coordonatorPartener")
    private String coordonatorPartener;
    @Column(name = "regie")
    private Double regie;
    @Column(name = "tva")
    private Integer tva;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable
    @JsonIgnore
    Set<Angajat> listaAngajati = new HashSet<>();


    @OneToMany(mappedBy="contractTerti",cascade = CascadeType.ALL)
    private Set<CerereImprumut> cerereImprumutLista;

    @OneToMany(mappedBy="contractTerti",cascade = CascadeType.ALL)
    private Set<Cheltuiala> cheltuialaLista;

    @OneToMany(mappedBy="contractTerti",cascade = CascadeType.ALL)
    private Set<ActAditional> actAditionalLista;

    @OneToMany(mappedBy="contractTerti",cascade = CascadeType.ALL)
    private Set<Incasare> incasareLista;



    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="beneficiar_contract")
    private Beneficiar beneficiar;


    public ContractTerti(){}

    public ContractTerti(Integer numar, Date data, String tip, String titluProiect, Double valoare, String moneda, Date dataIncheierii, Integer nrPagini, Integer nrExemplare, Integer nrExemplareBeneficiar, Date dataInceput, Date dataSfarsit, String coordonatorPartener, Integer tva) {
        this.numar = numar;
        this.data = data;
        this.tip = tip;
        this.titluProiect = titluProiect;
        this.valoare = valoare;
        this.moneda = moneda;
        this.dataIncheierii = dataIncheierii;
        this.nrPagini = nrPagini;
        this.nrExemplare = nrExemplare;
        this.nrExemplareBeneficiar = nrExemplareBeneficiar;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        this.coordonatorPartener = coordonatorPartener;
        this.regie = regie;
        this.tva = tva;
    }

    public Set<CerereImprumut> getCerereImprumutLista() {
        return cerereImprumutLista;
    }

    public void setCerereImprumutLista(Set<CerereImprumut> cerereImprumutLista) {
        this.cerereImprumutLista = cerereImprumutLista;
    }

    public Set<ActAditional> getActAditionalLista() {
        return actAditionalLista;
    }

    public void setActAditionalLista(Set<ActAditional> actAditionalLista) {
        this.actAditionalLista = actAditionalLista;
    }

    public Set<Angajat> getListaAngajati() {
        return listaAngajati;
    }

    public void setListaAngajati(Set<Angajat> listaAngajati) {
        this.listaAngajati = listaAngajati;
    }

    public Set<Cheltuiala> getCheltuialaLista() {
        return cheltuialaLista;
    }

    public void setCheltuialaLista(Set<Cheltuiala> cheltuialaLista) {
        this.cheltuialaLista = cheltuialaLista;
    }

    public Set<Incasare> getIncasareLista() {
        return incasareLista;
    }

    public void setIncasareLista(Set<Incasare> incasareLista) {
        this.incasareLista = incasareLista;
    }

    public Beneficiar getBeneficiar() {
        return beneficiar;
    }

    public void setBeneficiar(Beneficiar beneficiar) {
        this.beneficiar = beneficiar;
    }


    public int getIdContractTerti() {
        return idContractTerti;
    }

    public void setIdContractTerti(int idContractTerti) {
        this.idContractTerti = idContractTerti;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTitluProiect() {
        return titluProiect;
    }

    public void setTitluProiect(String titluProiect) {
        this.titluProiect = titluProiect;
    }
    public Double getValoare() {
        return valoare;
    }

    public void setValoare(Double valoare) {
        this.valoare = valoare;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Date getDataIncheierii() {
        return dataIncheierii;
    }

    public void setDataIncheierii(Date dataIncheierii) {
        this.dataIncheierii = dataIncheierii;
    }

    public Integer getNrPagini() {
        return nrPagini;
    }

    public void setNrPagini(Integer nrPagini) {
        this.nrPagini = nrPagini;
    }

    public Integer getNrExemplare() {
        return nrExemplare;
    }

    public void setNrExemplare(Integer nrExemplare) {
        this.nrExemplare = nrExemplare;
    }

    public Integer getNrExemplareBeneficiar() {
        return nrExemplareBeneficiar;
    }

    public void setNrExemplareBeneficiar(Integer nrExemplareBeneficiar) {
        this.nrExemplareBeneficiar = nrExemplareBeneficiar;
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

    public String getCoordonatorPartener() {
        return coordonatorPartener;
    }

    public void setCoordonatorPartener(String coordonatorPartener) {
        this.coordonatorPartener = coordonatorPartener;
    }

    public Double getRegie() {
        return regie;
    }

    public void setRegie(Double regie) {
        this.regie = regie;
    }

    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

}
