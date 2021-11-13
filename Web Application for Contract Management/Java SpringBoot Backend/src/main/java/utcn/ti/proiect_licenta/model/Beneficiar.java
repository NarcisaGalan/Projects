package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "beneficiar")
public class Beneficiar {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_beneficiar", unique = true, nullable = false)
    private int idBeneficiar;
    @Column(name="denumire",length=50, nullable = false, unique = true)
    private String denumire;
    @Column(name= "cif_cui",length=20, nullable = false, unique = true)
    private String cifCui;
    @Column(name="telefon",length=15)
    private String telefon;
    @Column(name="adresa_email",length=20)
    private String adresaEmail;
    @Column(name= "reprezentant",length=50)
    private String reprezentant;
    @Column(name="functie_reprezentant",length=50)
    private String functieReprezentant;


    @OneToOne
    @JoinColumn(name = "adresa_beneficiar",referencedColumnName = "id_adresa")
    private Adresa adresa;

    @OneToMany(mappedBy="beneficiar",cascade = CascadeType.ALL)
    private Set<Banca> banci;

    @JsonIgnore
    @OneToMany(mappedBy = "beneficiar",cascade = CascadeType.ALL)
    private Set<ContractTerti> listaContractTerti;

    public Beneficiar(){};

    public Beneficiar(String denumire, String cifCui, String telefon, String adresaEmail, String reprezentant, String functieReprezentant) {
        this.denumire = denumire;
        this.cifCui = cifCui;
        this.telefon = telefon;
        this.adresaEmail = adresaEmail;
        this.reprezentant = reprezentant;
        this.functieReprezentant = functieReprezentant;
        this.adresa = adresa;
    }

    public Beneficiar(String denumire, String cifCui, String telefon, String adresaEmail, String reprezentant, String functieReprezentant, Adresa adresa, Set<Banca> banci) {
        this.denumire = denumire;
        this.cifCui = cifCui;
        this.telefon = telefon;
        this.adresaEmail = adresaEmail;
        this.reprezentant = reprezentant;
        this.functieReprezentant = functieReprezentant;
        this.adresa = adresa;
        this.banci = banci;
    }

    public void editBeneficiar(String denumire,String telefon,String adresaEmail, String reprezentant,String functieReprezentant){
        this.denumire = denumire;
        this.telefon=telefon;
        this.adresaEmail=adresaEmail;
        this.reprezentant=reprezentant;
        this.functieReprezentant=functieReprezentant;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Set<Banca> getBanci() {
        return banci;
    }

    public void setBanci(Set<Banca> banci) {
        this.banci = banci;
    }

    public Set<ContractTerti> getListaContractTerti() {
        return listaContractTerti;
    }

    public void setListaContractTerti(Set<ContractTerti> listaContractTerti) {
        this.listaContractTerti = listaContractTerti;
    }

    public int getIdBeneficiar() {
        return idBeneficiar;
    }

    public void setIdBeneficiar(int idBeneficiar) {
        this.idBeneficiar = idBeneficiar;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getCifCui() {
        return cifCui;
    }

    public void setCifCui(String cifCui) {
        this.cifCui = cifCui;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresaEmail() {
        return adresaEmail;
    }

    public void setAdresaEmail(String adresaEmail) {
        this.adresaEmail = adresaEmail;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beneficiar that = (Beneficiar) o;
        return  Objects.equals(denumire, that.denumire) &&
                Objects.equals(cifCui, that.cifCui) &&
                Objects.equals(telefon, that.telefon) &&
                Objects.equals(adresaEmail, that.adresaEmail) &&
                Objects.equals(reprezentant, that.reprezentant) &&
                Objects.equals(functieReprezentant, that.functieReprezentant) ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(denumire, cifCui, telefon, adresaEmail, reprezentant, functieReprezentant);
    }

}
