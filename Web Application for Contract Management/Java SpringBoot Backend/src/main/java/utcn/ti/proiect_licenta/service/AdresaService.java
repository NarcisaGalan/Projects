package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.model.Adresa;
import utcn.ti.proiect_licenta.repository.AdresaRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.AdresaServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdresaService implements AdresaServiceInterface {
    @Autowired
    private AdresaRepository adresaRepository;

    @Override
    public Adresa save(Adresa adresa) {
        return adresaRepository.save(adresa);
    }

    @Override
    public void delete(Adresa adresa){
        adresaRepository.delete(adresa);
    }

    @Override
    public Optional<Adresa> findById(int id){
        return adresaRepository.findById(id);
    }

    @Override
    public List<Adresa> findAll() {
        return adresaRepository.findAll();
    }

   /* public Adresa findAdresaByBeneficiar(Beneficiar beneficiar){
        return this.adresaRepository.findByBeneficiar(beneficiar);
    }

    public Adresa findAdresaByUniversitate(Universitate universitate){
        return this.adresaRepository.findByUniversitate(universitate);
    }*/
}
