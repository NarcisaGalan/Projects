package utcn.ti.proiect_licenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "files")
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(name = "nume", length = 200, nullable = false)
    private String nume;
    @Column(name = "tip")
    private String tip;
    @Lob
    private byte[] data;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contractTerti", referencedColumnName = "id_contract_terti")
    private ContractTerti contractTerti;

    public FileDB() {
    }

    public FileDB(String nume, String tip, byte[] data) {
        this.nume = nume;
        this.tip = tip;
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ContractTerti getContractTerti() {
        return contractTerti;
    }

    public void setContractTerti(ContractTerti contractTerti) {
        this.contractTerti = contractTerti;
    }

    public String getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}



