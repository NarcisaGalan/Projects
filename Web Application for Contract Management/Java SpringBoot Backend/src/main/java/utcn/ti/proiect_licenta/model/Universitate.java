package utcn.ti.proiect_licenta.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "universitate")
public class Universitate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_universitate", unique = true, nullable = false)
    private int idUniversitate;
    @Column(name = "nume",length = 100)
    private String nume;
    @Column(name="codUniversitate",length = 10,nullable = false)
    private String codUniversitate;
    @Column(name = "telefon",length = 15)
    private String telefon;
    @Column(name = "fax",length = 15)
    private String fax;
    @Column(name = "codFiscal",length = 15)
    private String codFiscal;
    @Column(name = "codPostal",length = 15)
    private String codPostal;
    @Column(name="reprezentant1",length = 50)
    private String reprezentant1;
    @Column(name="reprezentant2",length = 50)
    private String reprezentant2;

    @OneToOne
    @JoinColumn(name="idAdresa")
    private Adresa adresa;

    @OneToOne
    @JoinColumn(name = "banca_universitate")
    private Banca banca;

    @OneToMany(mappedBy = "universitate",cascade = CascadeType.ALL)
    private Set<Facultate> facultati;



    public Universitate(){}

    public Universitate(String nume, String telefon, String fax, String codFiscal, String reprezentant1, String reprezentant2, Adresa adresa, Banca banca) {
        this.nume = nume;
        this.codUniversitate = codUniversitate;
        this.telefon = telefon;
        this.fax = fax;
        this.codFiscal = codFiscal;
        this.codPostal = codPostal;
        this.reprezentant1 = reprezentant1;
        this.reprezentant2 = reprezentant2;
        this.adresa = adresa;
        this.banca = banca;
    }

    public Universitate(String nume, String telefon, String fax, String codFiscal, String reprezentant1, String reprezentant2, Adresa adresa, Banca banca, Set<Facultate> facultati) {
        this.nume = nume;
        this.telefon = telefon;
        this.fax = fax;
        this.codFiscal = codFiscal;
        this.reprezentant1 = reprezentant1;
        this.reprezentant2 = reprezentant2;
        this.adresa = adresa;
        this.banca = banca;
        this.facultati = facultati;
    }


    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getCodUniversitate() {
        return codUniversitate;
    }

    public void setCodUniversitate(String codUniversitate) {
        this.codUniversitate = codUniversitate;
    }


    public String getReprezentant1() {
        return reprezentant1;
    }

    public void setReprezentant1(String reprezentant1) {
        this.reprezentant1 = reprezentant1;
    }

    public String getReprezentant2() {
        return reprezentant2;
    }

    public void setReprezentant2(String reprezentant2) {
        this.reprezentant2 = reprezentant2;
    }

    public Set<Facultate> getFacultati() {
        return facultati;
    }

    public void setFacultati(Set<Facultate> facultati) {
        this.facultati = facultati;
    }

    public int getIdUniversitate() {
        return idUniversitate;
    }

    public void setIdUniversitate(int idUniversitate) {
        this.idUniversitate = idUniversitate;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCodFiscal() {
        return codFiscal;
    }

    public void setCodFiscal(String codFiscal) {
        this.codFiscal = codFiscal;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Banca getBanca() {
        return banca;
    }

    public void setBanca(Banca banca) {
        this.banca = banca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Universitate universitate = (Universitate) o;
        return idUniversitate == universitate.idUniversitate &&
                Objects.equals(nume, universitate.nume) &&
                Objects.equals(telefon, universitate.telefon)&&
                Objects.equals(fax,universitate.fax) &&
                Objects.equals(codFiscal, universitate.codFiscal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, telefon, fax,codFiscal);
    }

}
