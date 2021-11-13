package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "banca")
public class Banca {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_banca", unique = true, nullable = false)
    private int idBanca;

    @Column(name = "nume",length=50, nullable = false)
    private String nume;
    @Column(name = "contBancar",length=50, nullable = false)
    private String contBancar;

    @JsonIgnore
    @OneToOne(mappedBy = "banca")
    private Universitate universitate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="beneficiar_banca")
    private Beneficiar beneficiar;

    public Banca(){}

    public Banca(String nume, String contBancar) {
        this.nume = nume;
        this.contBancar = contBancar;
    }

    public Universitate getUniversitate() {
        return universitate;
    }

    public void setUniversitate(Universitate universitate) {
        this.universitate = universitate;
    }

    public Beneficiar getBeneficiar() {
        return beneficiar;
    }

    public void setBeneficiar(Beneficiar beneficiar) {
        this.beneficiar = beneficiar;
    }

    public int getIdBanca() {
        return idBanca;
    }

    public void setIdBanca(int idBanca) {
        this.idBanca = idBanca;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getContBancar() {
        return contBancar;
    }

    public void setContBancar(String contBancar) {
        this.contBancar = contBancar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banca banca = (Banca) o;
        return Objects.equals(nume, banca.nume) &&
                Objects.equals(contBancar, banca.contBancar);

    }

    @Override
    public int hashCode() {
        return Objects.hash( nume, contBancar);
    }
}
