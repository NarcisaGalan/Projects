package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.model.Incasare;

import java.sql.Date;
import java.util.List;

public interface IncasareRepository extends JpaRepository<Incasare,Integer> {
	Incasare findByNrDocument(String nrDocument);
	List<Incasare> findAllByContractTerti(ContractTerti contractTerti);
	List<Incasare> findAllByDataDocumentBetweenAndContractTerti(Date start, Date end, ContractTerti contractTerti);
}
