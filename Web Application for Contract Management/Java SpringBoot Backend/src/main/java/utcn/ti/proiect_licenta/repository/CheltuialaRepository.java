package utcn.ti.proiect_licenta.repository;

import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utcn.ti.proiect_licenta.model.Cheltuiala;
import utcn.ti.proiect_licenta.model.ContractTerti;

import java.sql.Date;
import java.util.List;

public interface CheltuialaRepository extends JpaRepository<Cheltuiala,Integer> {
    List<Cheltuiala> findAllByContractTerti(ContractTerti contractTerti);
    List<Cheltuiala> findAllByDataDocumentBetweenAndContractTerti(Date start, Date end, ContractTerti contractTerti);
}
