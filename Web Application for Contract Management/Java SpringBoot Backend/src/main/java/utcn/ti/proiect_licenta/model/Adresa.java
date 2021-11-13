package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "adresa")
public class Adresa {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_adresa", unique = true, nullable = false)
    private int idAdresa;
    @Column(name = "tara", length=50, nullable = false)
    private String tara;
    @Column(name = "judet", length=2)
    private String judet;
    @Column(name = "localiate",length=50, nullable = false)
    private String localitate;
    @Column(name = "strada",length=50)
    private String strada;
    @Column(name = "detaliiAdresaNr")
    private String detaliiAdresaNr;

    @JsonIgnore
    @OneToOne(mappedBy = "adresa")
    private Beneficiar beneficiar;

    @JsonIgnore
    @OneToOne(mappedBy = "adresa")
    private Universitate universitate;

    public Adresa(){}

    public Adresa(String tara, String judet, String localitate, String strada, String detaliiAdresaNr) {
        this.tara = tara;
        this.judet = judet;
        this.localitate = localitate;
        this.strada = strada;
        this.detaliiAdresaNr = detaliiAdresaNr;
    }

    public int getIdAdresa() {
        return idAdresa;
    }

    public void setIdAdresa(int idAdresa) {
        this.idAdresa = idAdresa;
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

    public String getDetaliiAdresaNr() {
        return detaliiAdresaNr;
    }

    public void setDetaliiAdresaNr(String detaliiAdresaNr) {
        this.detaliiAdresaNr = detaliiAdresaNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresa adresa = (Adresa) o;
        return  Objects.equals(tara, adresa.tara) &&
                Objects.equals(judet, adresa.judet) &&
                Objects.equals(localitate, adresa.localitate) &&
                Objects.equals(strada, adresa.strada) &&
                Objects.equals(detaliiAdresaNr, adresa.detaliiAdresaNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tara, judet, localitate, strada, detaliiAdresaNr);
    }

}
