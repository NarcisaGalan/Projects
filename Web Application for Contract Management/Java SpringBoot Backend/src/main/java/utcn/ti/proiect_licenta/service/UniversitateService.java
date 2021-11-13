package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.model.Universitate;
import utcn.ti.proiect_licenta.repository.UniversitateRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.UniversitateServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class UniversitateService implements UniversitateServiceInterface {
    @Autowired
    private UniversitateRepository universitateRespository;

    @Override
    public Universitate save(Universitate universitate) {
        return universitateRespository.save(universitate);
    }

    @Override
    public void delete(Universitate universitate){
        universitateRespository.delete(universitate);
    }

    @Override
    public Optional<Universitate> findById(int id){
        return universitateRespository.findById(id);
    }

    @Override
    public List<Universitate> findAll() {
        return universitateRespository.findAll();
    }

}
