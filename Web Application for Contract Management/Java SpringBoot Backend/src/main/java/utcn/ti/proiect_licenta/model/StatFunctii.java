package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "stat_functii")
public class StatFunctii {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_stat_functii", unique = true, nullable = false)
    private int idStatFunctii;
    @Column(name = "functie",length = 30,nullable = false)
    private String functie;
    @Column(name = "dataInceput")
    private Date dataInceput;
    @Column(name = "dataSfarsit")
    private Date dataSfarsit;
    @Column (name = "incetareActivitate")
    private Boolean incetareActivitate ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="angajat", nullable=false)
    private Angajat angajat;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="contract_functii", nullable=false)
    private ContractTerti contractTerti;

    public StatFunctii(){}
    public StatFunctii(String functie, Date dataInceput, Date dataSfarsit) {

        this.functie = functie;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
    }

    public StatFunctii(String functie, Date dataInceput, Date dataSfarsit, Boolean incetareActivitate) {
        this.functie = functie;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        this.incetareActivitate = incetareActivitate;
    }


    public Boolean getIncetareActivitate() {
        return incetareActivitate;
    }

    public void setIncetareActivitate(Boolean incetareActivitate) {
        this.incetareActivitate = incetareActivitate;
    }

    public ContractTerti getContractTerti() {
        return contractTerti;
    }

    public void setContractTerti(ContractTerti contractTerti) {
        this.contractTerti = contractTerti;
    }

    public int getIdStatFunctii() {
        return idStatFunctii;
    }

    public void setIdStatFunctii(int idStatFunctii) {
        this.idStatFunctii = idStatFunctii;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public Date getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(Date dataInceput) {
        this.dataInceput = dataInceput;
    }

    public Date getDataSfarsit() {
        return dataSfarsit;
    }

    public void setDataSfarsit(Date dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }

    public Angajat getAngajat() {
        return angajat;
    }

    public void setAngajat(Angajat angajat) {
        this.angajat = angajat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatFunctii that = (StatFunctii) o;
        return idStatFunctii == that.idStatFunctii &&

                Objects.equals(functie, that.functie) &&
                Objects.equals(dataInceput, that.dataInceput) &&
                Objects.equals(dataSfarsit, that.dataSfarsit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStatFunctii, functie, dataInceput, dataSfarsit);
    }
}