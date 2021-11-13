package utcn.ti.proiect_licenta.dto;

import java.sql.Date;

public class ContractTertiDTO {

    private String universitate;
    private String numeBeneficiar;
    private String numarTelefon;
    private String adresaEmailBeneficiar;
    private String tara;
    private String judet;
    private String localitate;
    private String strada;
    private String numarStrada;
    private String reprezentant ;
    private String functieReprezentant;
    private String cif;
    private String cont;
    private String banca;
    private String director;
    private String partener;
    private String titlu;
    private String  moneda;
    private Double valTotala;
    private Double valFaraTVA;
    private Integer valTVA;
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

    public ContractTertiDTO(String universitate, String numeBeneficiar, String numarTelefon, String adresaEmailBeneficiar, String tara, String judet, String localitate, String strada, String numarStrada, String reprezentant, String functieReprezentant, String cif, String cont, String banca, String director, String partener, String titlu, String moneda, Double valTotala, Double valFaraTVA, Integer valTVA, String tip, Integer numarContract, Date dataContract, Date dataIncheiere, Date dataInceput, Date dataSfarsit, Integer numarPagini, Integer numarDeExemplare, Integer exemplareBeneficiar, Integer exemplareExecutant) {
        this.universitate = universitate;
        this.numeBeneficiar = numeBeneficiar;
        this.numarTelefon = numarTelefon;
        this.adresaEmailBeneficiar = adresaEmailBeneficiar;
        this.tara = tara;
        this.judet = judet;
        this.localitate = localitate;
        this.strada = strada;
        this.numarStrada = numarStrada;
        this.reprezentant = reprezentant;
        this.functieReprezentant = functieReprezentant;
        this.cif = cif;
        this.cont = cont;
        this.banca = banca;
        this.director = director;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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

    public Integer getValTVA() {
        return valTVA;
    }

    public void setValTVA(Integer valTVA) {
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

    public String getUniversitate() {
        return universitate;
    }

    public void setUniversitate(String universitate) {
        this.universitate = universitate;
    }

    public String getNumeBeneficiar() {
        return numeBeneficiar;
    }

    public void setNumeBeneficiar(String numeBeneficiar) {
        this.numeBeneficiar = numeBeneficiar;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getAdresaEmailBeneficiar() {
        return adresaEmailBeneficiar;
    }

    public void setAdresaEmailBeneficiar(String adresaEmailBeneficiar) {
        this.adresaEmailBeneficiar = adresaEmailBeneficiar;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNumarStrada() {
        return numarStrada;
    }

    public void setNumarStrada(String numarStrada) {
        this.numarStrada = numarStrada;
    }

    public String getReprezentant() {
        return reprezentant;
    }

    public void setReprezentant(String reprezentant) {
        this.reprezentant = reprezentant;
    }

    public String getFunctieReprezentant() {
        return functieReprezentant;
    }

    public void setFunctieReprezentant(String functieReprezentant) {
        this.functieReprezentant = functieReprezentant;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getBanca() {
        return banca;
    }

    public void setBanca(String banca) {
        this.banca = banca;
    }
}
