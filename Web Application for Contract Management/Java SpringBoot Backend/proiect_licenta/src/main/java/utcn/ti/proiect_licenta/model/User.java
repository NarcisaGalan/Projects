package utcn.ti.proiect_licenta.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_user", unique = true, nullable = false)
    private int idUser;

    @Column(name = "email", unique = true, nullable = false,length = 50)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role",length = 10)
    private String role;


    @OneToOne(mappedBy = "user")
    private Angajat angajat;

    public User(){}

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password, String role, Angajat angajat) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.angajat = angajat;
    }

    public Angajat getAngajat() {
        return angajat;
    }

    public void setAngajat(Angajat angajat) {
        this.angajat = angajat;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
