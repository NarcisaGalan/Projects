package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.CerereImprumut;
import utcn.ti.proiect_licenta.model.ContractTerti;

import java.sql.Date;
import java.util.List;

public interface CerereImprumutRepository extends JpaRepository<CerereImprumut,Integer> {
    List<CerereImprumut> findAllByContractTerti(ContractTerti contractTerti);
    List<CerereImprumut> findAllByContractTertiAndAprobata(ContractTerti contractTerti, Boolean aprobata);
    List<CerereImprumut> findAllByDataBetweenAndContractTerti(Date start, Date end, ContractTerti contractTerti);
}
