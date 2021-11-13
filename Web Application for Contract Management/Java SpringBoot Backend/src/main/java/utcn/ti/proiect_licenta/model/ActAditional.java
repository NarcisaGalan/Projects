package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.sql.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "act_aditional")
public class ActAditional {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_act_aditional", unique = true, nullable = false)
    private int idActAditional;

    @Column(name = "numar", nullable=false)
    private Integer numar;
    @Column(name = "data", nullable=false)
    private Date data;
    @Column(name = "data_inceput")
    private Date dataInceput;
    @Column(name = "data_sfarsit")
    private Date dataSfarsit;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="contract_terti_act_aditional")
    private ContractTerti contractTerti;

    public ActAditional(){}



    public ActAditional(Integer numar, Date data, Date dataInceput, Date dataSfarsit) {
        this.numar = numar;
        this.data = data;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;

    }

    public ActAditional(Integer numar, Date data, Date dataInceput, Date dataSfarsit, ContractTerti contractTerti) {
        this.numar = numar;
        this.data = data;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        this.contractTerti = contractTerti;
    }

    public ContractTerti getContractTerti() {
        return contractTerti;
    }

    public void setContractTerti(ContractTerti contractTerti) {
        this.contractTerti = contractTerti;
    }

    public int getIdActAditional() {
        return idActAditional;
    }

    public void setIdActAditional(int idActAditional) {
        this.idActAditional = idActAditional;
    }

    public Integer getNumar() {
        return numar;
    }

    public void setNumar(Integer numar) {
        this.numar = numar;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActAditional that = (ActAditional) o;
        return idActAditional == that.idActAditional &&
                Objects.equals(numar, that.numar) &&
                Objects.equals(data, that.data) &&
                Objects.equals(dataInceput, that.dataInceput) &&
                Objects.equals(dataSfarsit, that.dataSfarsit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActAditional, numar, data, dataInceput, dataSfarsit);
    }


}
