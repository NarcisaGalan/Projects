package utcn.ti.proiect_licenta.dto;

public class ResponseFile {
    private String nume;
    private String url;
    private String tip;
    private long dimensiune;

    public ResponseFile(String nume, String url, String tip, long dimensiune) {
        this.nume = nume;
        this.url = url;
        this.tip = tip;
        this.dimensiune = dimensiune;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public long getDimensiune() {
        return dimensiune;
    }

    public void setDimensiune(long dimensiune) {
        this.dimensiune = dimensiune;
    }
}