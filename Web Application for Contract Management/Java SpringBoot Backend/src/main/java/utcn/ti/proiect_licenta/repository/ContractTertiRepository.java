package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.ContractTerti;

import java.sql.Date;
import java.util.List;

public interface ContractTertiRepository extends JpaRepository<ContractTerti,Integer> {

    ContractTerti findByTitluProiect(String titluProiect);

    List<ContractTerti> findAllByDataSfarsitAfter(Date dataSfarsit);


}
