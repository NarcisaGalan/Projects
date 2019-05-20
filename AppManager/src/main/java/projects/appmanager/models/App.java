package projects.appmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app")
public class App {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "technologies")
    private String technologies;
    @Column(name = "version")
    private String version;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "app_user",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "app_id") }
    )
    @JsonIgnore
    Set<User> users = new HashSet<>();


    public App(){}

    public App(int id,String name, String technologies, String version) {
        this.id=id;
        this.name = name;
        this.technologies = technologies;
        this.version = version;
    }

    public App(String name, String technologies, String version, Set<User> users) {
        this.name = name;
        this.technologies = technologies;
        this.version = version;
        this.users = users;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
