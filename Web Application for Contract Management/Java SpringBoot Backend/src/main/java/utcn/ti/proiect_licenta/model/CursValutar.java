package utcn.ti.proiect_licenta.model;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "curs_valutar")
public class CursValutar {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_curs_valutar", unique = true, nullable = false)
    private Integer idCursValutar;

    @Column(name = "moneda", length = 4)
    private String moneda;

    @Column(name = "valoare")
    private Double valoare;

    @Column(name = "data")
    private Date data;

    public CursValutar(){}

    public CursValutar(String moneda, Double valoare, Date data) {
        this.moneda = moneda;
        this.valoare = valoare;
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getValoare() {
        return valoare;
    }

    public void setValoare(Double valoare) {
        this.valoare = valoare;
    }

    @Override
    public boolean equals(Object obj) {
        return ((this.moneda.equals(((CursValutar) obj).moneda))
                && this.valoare.equals(((CursValutar) obj).valoare) && this.data
                .equals(((CursValutar) obj).data));
    }
}
