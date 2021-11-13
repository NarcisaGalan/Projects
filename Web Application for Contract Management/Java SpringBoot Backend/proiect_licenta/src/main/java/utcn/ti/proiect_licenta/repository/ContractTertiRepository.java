package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.ContractTerti;

public interface ContractTertiRepository extends JpaRepository<ContractTerti,Integer> {

    ContractTerti findByTitluProiect(String titluProiect);


}
