package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "departament")
public class Departament {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_departament", unique = true, nullable = false)
    private int idDepartament;

    @Column(name = "nume", length = 60)
    private String nume;

    @Column(name = "codDepartament", length = 10,nullable = false)
    private String codDepartament;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "facultate_departament")
    private Facultate facultate;

    @JsonIgnore
    @OneToMany(mappedBy="departament",cascade = CascadeType.ALL)
    private Set<Angajat> listaAngajati;

    public Departament(){}

    public Departament(String nume, String codDepartament, Facultate facultate) {
        this.nume = nume;
        this.codDepartament = codDepartament;
        this.facultate = facultate;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getIdDepartament() {
        return idDepartament;
    }

    public void setIdDepartament(int idDepartament) {
        this.idDepartament = idDepartament;
    }

    public String getCodDepartament() {
        return codDepartament;
    }

    public void setCodDepartament(String codDepartament) {
        this.codDepartament = codDepartament;
    }

    public Facultate getFacultate() {
        return facultate;
    }

    public void setFacultate(Facultate facultate) {
        this.facultate = facultate;
    }

    @Override
    public boolean equals(Object obj) {
        return ((this.nume.equals(((Departament) obj).nume))
                && this.codDepartament.equals(((Departament) obj).codDepartament) && this.facultate
                .equals(((Departament) obj).facultate));
    }
}