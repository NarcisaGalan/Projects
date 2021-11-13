package utcn.ti.proiect_licenta.service.serviceInterface;

import utcn.ti.proiect_licenta.model.ActAditional;
import utcn.ti.proiect_licenta.model.ContractTerti;

import java.util.List;
import java.util.Set;

public interface ActAditionalServiceInterface {
    List<ActAditional> findAll();

    ActAditional findById(Integer id);

    ActAditional save(ActAditional stat);

    void delete(ActAditional stat);

    ActAditional update(ActAditional stat);

    List<ActAditional> findAllByContractTerti(ContractTerti contractTerti);
}
