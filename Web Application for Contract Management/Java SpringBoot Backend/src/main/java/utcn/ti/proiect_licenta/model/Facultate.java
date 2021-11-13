package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "facultate")
public class Facultate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_facultate", unique = true, nullable = false)
    private int idFacultate;

    @Column(name = "nume",length = 60)
    private String nume;

    @Column(name = "codFacultate",length = 10,nullable = false)
    private String codFacultate;

    @OneToMany(mappedBy = "facultate")
    private Set<Departament> departamente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "universitate")
    private Universitate universitate;

    public Facultate(){}

    public Facultate(String nume, String codFacultate, Universitate universitate) {
        this.nume = nume;
        this.codFacultate = codFacultate;
        this.universitate = universitate;
    }

    public Facultate(String nume, String codFacultate, Set<Departament> departamente, Universitate universitate) {
        this.nume = nume;
        this.codFacultate = codFacultate;
        this.departamente = departamente;
        this.universitate = universitate;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Set<Departament> getDepartamente() {
        return departamente;
    }

    public void setDepartamente(Set<Departament> departamente) {
        this.departamente = departamente;
    }

    public int getIdFacultate() {
        return idFacultate;
    }

    public void setIdFacultate(int idFacultate) {
        this.idFacultate = idFacultate;
    }

    public String getCodFacultate() {
        return codFacultate;
    }

    public void setCodFacultate(String codFacultate) {
        this.codFacultate = codFacultate;
    }

    public Universitate getUniversitate() {
        return universitate;
    }

    public void setUniversitate(Universitate universitate) {
        this.universitate = universitate;
    }

    @Override
    public boolean equals(Object obj) {
        return ((this.nume.equals(((Facultate) obj).nume))
                && this.codFacultate.equals(((Facultate) obj).codFacultate) && this.universitate
                .equals(((Facultate) obj).universitate));
    }
}
