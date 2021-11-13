package utcn.ti.proiect_licenta.service.serviceInterface;

import utcn.ti.proiect_licenta.model.ContractTerti;

import java.util.List;
import java.util.Optional;

public interface ContractTertiServiceInterface {
    List<ContractTerti> findAll();

    Optional<ContractTerti> findById(Integer id);

    ContractTerti save(ContractTerti contractTerti);

    void delete(ContractTerti contractTerti);

    ContractTerti update(ContractTerti contractTerti);
}
