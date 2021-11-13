package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.CheltuialaDTO;
import utcn.ti.proiect_licenta.model.Cheltuiala;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.repository.CheltuialaRepository;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.CheltuialaServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheltuialaService implements CheltuialaServiceInterface {

    @Autowired
    private CheltuialaRepository repository;
    @Autowired
    private ContractTertiRepository contractTertiRepository;

    @Override
    public List<Cheltuiala> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Cheltuiala> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Cheltuiala save(CheltuialaDTO cheltuialaDTO) {
        Cheltuiala cheltuiala = new Cheltuiala(cheltuialaDTO.getNumarDocument(),cheltuialaDTO.getDataCheltuiala(),cheltuialaDTO.getTipDocument(),cheltuialaDTO.getValoareCheltuiala(),
                cheltuialaDTO.getValoareTVA(),cheltuialaDTO.getMoneda(),cheltuialaDTO.getExplicatii(),cheltuialaDTO.getIncadrareCheltuiala());

        ContractTerti contractTerti = contractTertiRepository.findByTitluProiect(cheltuialaDTO.getTitluProiect());
        cheltuiala.setContractTerti(contractTerti);


        return repository.save(cheltuiala);
    }

    @Override
    public void delete(Cheltuiala stat){
        this.repository.delete(stat);
    }

    @Override
    public Cheltuiala update(Cheltuiala stat){
        return this.repository.save(stat);
    }
}
