package utcn.ti.proiect_licenta.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.IncasareDTO;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.model.Incasare;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.repository.IncasareRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.IncasareServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncasareService implements IncasareServiceInterface {

    @Autowired
    private IncasareRepository repository;
    @Autowired
    private ContractTertiRepository contractTertiRepository;

    @Override
    public List<Incasare> findAll() {
        return this.repository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Incasare> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public  Incasare save(IncasareDTO incasareDTO) {
        Incasare incasare = new Incasare(incasareDTO.getNrDocument(),incasareDTO.getDataDocument(),incasareDTO.getTipDocument(),
                incasareDTO.getValoareIncasata(),incasareDTO.getValoareTVA(),incasareDTO.getMoneda(),incasareDTO.getExplicatii(),
                incasareDTO.getIncadrareIncasare());


            ContractTerti contractTerti = contractTertiRepository.findByTitluProiect(incasareDTO.getTitluProiect());
            incasare.setContractTerti(contractTerti);


        return repository.save(incasare);
    }

    @Override
    public void delete(Incasare stat) {
        this.repository.delete(stat);
    }

    @Override
    public Incasare update(Incasare stat) {
        return this.repository.save(stat);
    }

}
