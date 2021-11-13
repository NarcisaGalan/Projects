package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "angajat")
public class Angajat {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_angajat", unique = true, nullable = false)
    private int idAngajat;
    @Column(name = "nume",length=50, nullable = false)
    private String nume;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "stat_functii", referencedColumnName = "id_stat_functii")
    private StatFunctii statFunctii;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_angajat", referencedColumnName = "id_user")
    private User user;

    @JsonIgnore
    @ManyToMany(mappedBy = "listaAngajati")
    private Set<ContractTerti> listaContracte = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="departament_angajat", nullable=false)
    private Departament departament;

    public Angajat(){
    }
    public Angajat(String nume,Departament departament) {
        this.nume = nume;
        this.departament=departament;
    }

    public Set<ContractTerti> getListaContracte() {
        return listaContracte;
    }

    public void setListaContracte(Set<ContractTerti> listaContracte) {
        this.listaContracte = listaContracte;
    }

    public StatFunctii getStatFunctii() {
        return statFunctii;
    }

    public void setStatFunctii(StatFunctii statFunctii) {
        this.statFunctii = statFunctii;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angajat angajat = (Angajat) o;
        return idAngajat == angajat.idAngajat &&
                Objects.equals(nume, angajat.nume) ;


    }

    @Override
    public int hashCode() {
        return Objects.hash(idAngajat, nume, departament);
    }
}
