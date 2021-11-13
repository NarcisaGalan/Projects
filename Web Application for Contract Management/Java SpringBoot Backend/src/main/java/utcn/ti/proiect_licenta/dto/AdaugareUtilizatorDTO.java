package utcn.ti.proiect_licenta.dto;

public class AdaugareUtilizatorDTO {
    private String email ;
    private String parola ;
    private String nume ;
    private String role ;
    private String functie ;
    private String numeDepartament ;

    public AdaugareUtilizatorDTO(String email, String parola, String nume, String role, String functie, String numeDepartament) {
        this.email = email;
        this.parola = parola;
        this.nume = nume;
        this.role = role;
        this.functie = functie;
        this.numeDepartament = numeDepartament;
    }

    public String getNumeDepartament() {
        return numeDepartament;
    }

    public void setNumeDepartament(String numeDepartament) {
        this.numeDepartament = numeDepartament;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }
}
