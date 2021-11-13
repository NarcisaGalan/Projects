package utcn.ti.proiect_licenta.dto;

public class BeneficiarDTO {

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

    public BeneficiarDTO(){}
    public BeneficiarDTO(String numeBeneficiar, String numarTelefon, String adresaEmailBeneficiar, String tara, String judet, String localitate, String strada, String numarStrada, String reprezentant, String functieReprezentant, String cif, String cont, String banca) {
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
