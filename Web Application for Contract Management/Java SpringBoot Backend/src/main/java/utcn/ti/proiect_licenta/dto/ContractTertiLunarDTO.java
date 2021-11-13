package utcn.ti.proiect_licenta.dto;

import utcn.ti.proiect_licenta.model.ActAditional;

import java.sql.Date;
import java.util.Set;

public class ContractTertiLunarDTO {

    private Integer idContractTerti;
    private String numeBeneficiar;
    private String numeDirector;
    private String partener;
    private String titlu;
    private String  moneda;
    private Double valTotala;
    private Double valTVA;
    private Double valInLei;
    private String tip;
    private Integer numarContract;
    private Date dataContract;
    private Date dataInceput;
    private Date dataSfarsit;
    private Integer numarPagini;
    private Integer numarDeExemplare;
    private Integer exemplareBeneficiar;
    private Double regie;
    private Set<ActAditional> actAditionalList;


    public ContractTertiLunarDTO(Integer idContractTerti, String numeBeneficiar, String numeDirector, String partener, String titlu, String moneda, Double valTotala,  Double valTVA, Double valInLei, String tip, Integer numarContract, Date dataContract, Date dataInceput, Date dataSfarsit, Integer numarPagini, Integer numarDeExemplare, Integer exemplareBeneficiar,Double regie) {
        this.idContractTerti = idContractTerti;
        this.numeBeneficiar = numeBeneficiar;
        this.numeDirector = numeDirector;
        this.partener = partener;
        this.titlu = titlu;
        this.moneda = moneda;
        this.valTotala = valTotala;
        this.valTVA = valTVA;
        this.valInLei = valInLei;
        this.tip = tip;
        this.numarContract = numarContract;
        this.dataContract = dataContract;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        this.numarPagini = numarPagini;
        this.numarDeExemplare = numarDeExemplare;
        this.exemplareBeneficiar = exemplareBeneficiar;
        this.regie=regie;

    }

    public Double getRegie() {
        return regie;
    }

    public void setRegie(Double regie) {
        this.regie = regie;
    }

    public Set<ActAditional> getActAditionalList() {
        return actAditionalList;
    }

    public void setActAditionalList(Set<ActAditional> actAditionalList) {
        this.actAditionalList = actAditionalList;
    }

    public Integer getIdContractTerti() {
        return idContractTerti;
    }

    public void setIdContractTerti(Integer idContractTerti) {
        this.idContractTerti = idContractTerti;
    }

    public String getNumeBeneficiar() {
        return numeBeneficiar;
    }

    public void setNumeBeneficiar(String numeBeneficiar) {
        this.numeBeneficiar = numeBeneficiar;
    }

    public String getNumeDirector() {
        return numeDirector;
    }

    public void setNumeDirector(String numeDirector) {
        this.numeDirector = numeDirector;
    }

    public String getPartener() {
        return partener;
    }

    public void setPartener(String partener) {
        this.partener = partener;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getValTotala() {
        return valTotala;
    }

    public void setValTotala(Double valTotala) {
        this.valTotala = valTotala;
    }


    public Double getValTVA() {
        return valTVA;
    }

    public void setValTVA(Double valTVA) {
        this.valTVA = valTVA;
    }

    public Double getValInLei() {
        return valInLei;
    }

    public void setValInLei(Double valInLei) {
        this.valInLei = valInLei;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getNumarContract() {
        return numarContract;
    }

    public void setNumarContract(Integer numarContract) {
        this.numarContract = numarContract;
    }

    public Date getDataContract() {
        return dataContract;
    }

    public void setDataContract(Date dataContract) {
        this.dataContract = dataContract;
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

    public Integer getNumarPagini() {
        return numarPagini;
    }

    public void setNumarPagini(Integer numarPagini) {
        this.numarPagini = numarPagini;
    }

    public Integer getNumarDeExemplare() {
        return numarDeExemplare;
    }

    public void setNumarDeExemplare(Integer numarDeExemplare) {
        this.numarDeExemplare = numarDeExemplare;
    }

    public Integer getExemplareBeneficiar() {
        return exemplareBeneficiar;
    }

    public void setExemplareBeneficiar(Integer exemplareBeneficiar) {
        this.exemplareBeneficiar = exemplareBeneficiar;
    }


}
