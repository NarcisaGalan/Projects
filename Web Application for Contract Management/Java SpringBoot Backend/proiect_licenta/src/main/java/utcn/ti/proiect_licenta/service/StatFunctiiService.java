package utcn.ti.proiect_licenta.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.model.StatFunctii;
import utcn.ti.proiect_licenta.repository.StatFunctiiRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.StatFunctiiServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatFunctiiService implements StatFunctiiServiceInterface {

    @Autowired
    private StatFunctiiRepository repository;

    @Override
    public List<StatFunctii> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<StatFunctii> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public StatFunctii save(StatFunctii stat) {
        return this.repository.save(stat);
    }

    @Override
    public void delete(StatFunctii stat){
        this.repository.delete(stat);
    }

    @Override
    public StatFunctii update(StatFunctii stat){
        return this.repository.save(stat);
    }


}
