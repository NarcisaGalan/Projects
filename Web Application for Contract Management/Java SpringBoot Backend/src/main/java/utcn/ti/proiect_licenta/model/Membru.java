package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "membru")
public class Membru{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_membru", unique = true, nullable = false)
    private int idMembru;
    @Column(name = "nume",length=50, nullable = false)
    private String nume;
    @Column(name = "functie",length=50, nullable = false)
    private String functie;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_membru", referencedColumnName = "id_user")
    private User user;

    public Membru(){}

    public Membru(String nume, String functie) {
        this.nume = nume;
        this.functie = functie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIdMembru() {
        return idMembru;
    }

    public void setIdMembru(int idMembru) {
        this.idMembru = idMembru;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membru membru = (Membru) o;
        return Objects.equals(nume, membru.nume) &&
                Objects.equals(functie,membru.functie);

    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, functie);
    }

}
