package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.model.ActAditional;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.repository.ActAditionalRepository;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.ActAditionalServiceInterface;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActAditionalService implements ActAditionalServiceInterface {

    @Autowired
    private ActAditionalRepository repository;
    @Autowired
    private ContractTertiRepository contractTertiRepository;



    @Override
    public List<ActAditional> findAll() {
        return this.repository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public ActAditional findById(Integer id) {
        return this.repository.findById(id).get();
    }

    @Override
    public ActAditional save(ActAditional stat) {
        return this.repository.save(stat);
    }

    @Override
    public void delete(ActAditional stat) {
        this.repository.delete(stat);
    }

    @Override
    public ActAditional update(ActAditional actAditional) {
       ActAditional actAditional1= repository.findById(actAditional.getIdActAditional()).get();
       actAditional.setContractTerti(actAditional1.getContractTerti());
        return this.repository.save(actAditional);
    }

    @Override
    public List<ActAditional> findAllByContractTerti(ContractTerti contractTerti){
        return this.repository.findAllByContractTerti(contractTerti);
    }


}
