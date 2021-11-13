package utcn.ti.proiect_licenta.dto;

public class RaportBuget {
    private String numeProiect;
    private Double valoareContractInLei;
    private Double valoareCheltuitaInLei;
    private Double valoareImprumutata;
    private Double valoareIncasataInLei;
    private Double valoareDeIncasatInLei;
    private Double profitInLei;
    private String monedaContract;
    private Double valoareContract;

    public RaportBuget(String numeProiect, Double valoareContractInLei, Double valoareCheltuitaInLei, Double valoareImprumutata, Double valoareIncasataInLei, Double valoareDeIncasatInLei, Double profitInLei, String monedaContract, Double valoareContract) {
        this.numeProiect = numeProiect;
        this.valoareContractInLei = valoareContractInLei;
        this.valoareCheltuitaInLei = valoareCheltuitaInLei;
        this.valoareImprumutata = valoareImprumutata;
        this.valoareIncasataInLei = valoareIncasataInLei;
        this.valoareDeIncasatInLei = valoareDeIncasatInLei;
        this.profitInLei = profitInLei;
        this.monedaContract = monedaContract;
        this.valoareContract = valoareContract;
    }


    public String getNumeProiect() {
        return numeProiect;
    }

    public void setNumeProiect(String numeProiect) {
        this.numeProiect = numeProiect;
    }

    public Double getValoareContractInLei() {
        return valoareContractInLei;
    }

    public void setValoareContractInLei(Double valoareContractInLei) {
        this.valoareContractInLei = valoareContractInLei;
    }

    public Double getValoareCheltuitaInLei() {
        return valoareCheltuitaInLei;
    }

    public void setValoareCheltuitaInLei(Double valoareCheltuitaInLei) {
        this.valoareCheltuitaInLei = valoareCheltuitaInLei;
    }

    public Double getValoareImprumutata() {
        return valoareImprumutata;
    }

    public void setValoareImprumutata(Double valoareImprumutata) {
        this.valoareImprumutata = valoareImprumutata;
    }

    public Double getValoareIncasataInLei() {
        return valoareIncasataInLei;
    }

    public void setValoareIncasataInLei(Double valoareIncasataInLei) {
        this.valoareIncasataInLei = valoareIncasataInLei;
    }

    public Double getValoareDeIncasatInLei() {
        return valoareDeIncasatInLei;
    }

    public void setValoareDeIncasatInLei(Double valoareDeIncasatInLei) {
        this.valoareDeIncasatInLei = valoareDeIncasatInLei;
    }

    public Double getProfitInLei() {
        return profitInLei;
    }

    public void setProfitInLei(Double profitInLei) {
        this.profitInLei = profitInLei;
    }

    public String getMonedaContract() {
        return monedaContract;
    }

    public void setMonedaContract(String monedaContract) {
        this.monedaContract = monedaContract;
    }

    public Double getValoareContract() {
        return valoareContract;
    }

    public void setValoareContract(Double valoareContract) {
        this.valoareContract = valoareContract;
    }
}
