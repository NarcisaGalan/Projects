package utcn.ti.proiect_licenta.dto;

public class UserDTO {

    private int idUser;

    private int idAngajat;

    private int idMembru;

    private String email;

    private String password;

    private String role;

    private String token;

    public UserDTO(){};
    public UserDTO(int idUser,String email, String password, String role) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdMembru() {
        return idMembru;
    }

    public void setIdMembru(int idMembru) {
        this.idMembru = idMembru;
    }
}
