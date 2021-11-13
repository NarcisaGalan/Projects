package utcn.ti.proiect_licenta.dto;

import utcn.ti.proiect_licenta.model.Departament;

import javax.persistence.Column;

public class AngajatDTO {
    private int idAngajat;
    private String nume;
    private Departament departament;


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
}
