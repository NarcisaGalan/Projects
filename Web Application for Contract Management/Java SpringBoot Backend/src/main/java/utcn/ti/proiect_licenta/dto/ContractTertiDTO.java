package utcn.ti.proiect_licenta.dto;

import java.sql.Date;

public class ContractTertiDTO {
    private Integer idContractTerti;
    private String cifCuiBeneficiar;
    private Integer idDirector;
    private String partener;
    private String titlu;
    private String  moneda;
    private Double valTotala;
    private Double valFaraTVA;
    private Double valTVA;
    private String tip;
    private Integer numarContract;
    private Date dataContract;
    private Date dataIncheiere;
    private Date dataInceput;
    private Date dataSfarsit;
    private Integer numarPagini;
    private Integer numarDeExemplare;
    private Integer exemplareBeneficiar;
    private Integer exemplareExecutant;

    public ContractTertiDTO() {
    }

    public ContractTertiDTO( String cifCuiBeneficiar, Integer idDirector, String partener, String titlu, String moneda, Double valTotala, Double valFaraTVA, Double valTVA, String tip, Integer numarContract, Date dataContract, Date dataIncheiere, Date dataInceput, Date dataSfarsit, Integer numarPagini, Integer numarDeExemplare, Integer exemplareBeneficiar, Integer exemplareExecutant) {

        this.cifCuiBeneficiar=cifCuiBeneficiar;
        this.idDirector = idDirector;
        this.partener = partener;
        this.titlu = titlu;
        this.moneda = moneda;
        this.valTotala = valTotala;
        this.valFaraTVA = valFaraTVA;
        this.valTVA = valTVA;
        this.tip = tip;
        this.numarContract = numarContract;
        this.dataContract = dataContract;
        this.dataIncheiere = dataIncheiere;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        this.numarPagini = numarPagini;
        this.numarDeExemplare = numarDeExemplare;
        this.exemplareBeneficiar = exemplareBeneficiar;
        this.exemplareExecutant = exemplareExecutant;
    }

    public Integer getIdContractTerti() {
        return idContractTerti;
    }

    public void setIdContractTerti(Integer idContractTerti) {
        this.idContractTerti = idContractTerti;
    }

    public String getCifCuiBeneficiar() {
        return cifCuiBeneficiar;
    }

    public void setCifCuiBeneficiar(String cifCuiBeneficiar) {
        this.cifCuiBeneficiar = cifCuiBeneficiar;
    }

    public Integer getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Integer idDirector) {
        this.idDirector = idDirector;
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

    public Double getValFaraTVA() {
        return valFaraTVA;
    }

    public void setValFaraTVA(Double valFaraTVA) {
        this.valFaraTVA = valFaraTVA;
    }

    public Double getValTVA() {
        return valTVA;
    }

    public void setValTVA(Double valTVA) {
        this.valTVA = valTVA;
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

    public Date getDataIncheiere() {
        return dataIncheiere;
    }

    public void setDataIncheiere(Date dataIncheiere) {
        this.dataIncheiere = dataIncheiere;
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

    public Integer getExemplareExecutant() {
        return exemplareExecutant;
    }

    public void setExemplareExecutant(Integer exemplareExecutant) {
        this.exemplareExecutant = exemplareExecutant;
    }

}
