package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.ActAditional;
import utcn.ti.proiect_licenta.model.ContractTerti;

import java.util.List;
import java.util.Set;

public interface ActAditionalRepository extends JpaRepository<ActAditional, Integer> {

    List<ActAditional> findAllByContractTerti(ContractTerti contractTerti);
}
