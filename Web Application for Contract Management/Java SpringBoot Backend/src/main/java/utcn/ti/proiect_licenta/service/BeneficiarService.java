package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.model.Beneficiar;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.repository.BeneficiarRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.BeneficiarServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarService implements BeneficiarServiceInterface {

    @Autowired
    private BeneficiarRepository beneficiarRepository;

    @Override
    public Beneficiar save(Beneficiar act) {
        return beneficiarRepository.save(act);
    }

    @Override
    public void delete(Beneficiar act){
        beneficiarRepository.delete(act);
    }

    @Override
    public Optional<Beneficiar> findById(int id){
        return beneficiarRepository.findById(id);
    }

    public List<Beneficiar> findAll(){return this.beneficiarRepository.findAll();}

    public List<Beneficiar> findByDenumireContaining(String denumire){
        return beneficiarRepository.findByDenumireContaining(denumire);
    }

    public Beneficiar findByDenumireAndCifCui(String denumire,String cifCui ){
        return beneficiarRepository.findByDenumireAndCifCui(denumire,cifCui);
    }

    public Beneficiar findByCifCui(String cifCui){
        return beneficiarRepository.findByCifCui(cifCui);
    }
}
