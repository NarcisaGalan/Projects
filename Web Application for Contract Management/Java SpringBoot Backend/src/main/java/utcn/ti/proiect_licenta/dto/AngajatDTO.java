package utcn.ti.proiect_licenta.dto;

import utcn.ti.proiect_licenta.model.Departament;

import javax.persistence.Column;

public class AngajatDTO {
    private int idAngajat;
    private String nume;
    private Departament departament;

    public AngajatDTO(){}

    public AngajatDTO(int idAngajat, String nume, Departament departament) {
        this.idAngajat = idAngajat;
        this.nume = nume;
        this.departament = departament;
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
    public boolean equals(Object obj) {
        return ((this.idAngajat == (((AngajatDTO) obj).idAngajat))
                && this.nume.equals(((AngajatDTO) obj).nume) && this.departament
                .equals(((AngajatDTO) obj).departament));
    }
}
