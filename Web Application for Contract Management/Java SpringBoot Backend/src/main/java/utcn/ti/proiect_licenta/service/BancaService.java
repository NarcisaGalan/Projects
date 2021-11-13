package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.model.Banca;
import utcn.ti.proiect_licenta.repository.BancaRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.BancaServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class BancaService implements BancaServiceInterface {
    @Autowired
    private BancaRepository bancaRepository;

    @Override
    public Banca save(Banca banca) {
        return bancaRepository.save(banca);
    }

    @Override
    public void delete(Banca banca){
        bancaRepository.delete(banca);
    }

    @Override
    public Optional<Banca> findById(int id){
        return bancaRepository.findById(id);
    }

    @Override
    public List<Banca> findAll() {
        return bancaRepository.findAll();
    }

}
