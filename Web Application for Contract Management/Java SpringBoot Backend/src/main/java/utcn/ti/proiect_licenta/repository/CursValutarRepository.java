package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.CursValutar;

import java.sql.Date;

public interface CursValutarRepository extends JpaRepository<CursValutar,Integer> {
    CursValutar findCursValutarByDataAndMoneda(Date data, String moneda);
    CursValutar findCursValutarByDataAfterAndMoneda(Date date,String moneda);

}
