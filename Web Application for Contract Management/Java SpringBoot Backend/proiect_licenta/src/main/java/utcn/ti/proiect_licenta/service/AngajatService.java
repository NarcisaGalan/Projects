package utcn.ti.proiect_licenta.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.AngajatInfoDTO;
import utcn.ti.proiect_licenta.model.*;
import utcn.ti.proiect_licenta.repository.AngajatRepository;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.AngajatServiceInterface;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AngajatService implements AngajatServiceInterface {

    @Autowired
    private AngajatRepository repository;
    @Autowired
    private ContractTertiRepository contractTertiRepository;

    @Override
    public List<Angajat> findAll() {
        return this.repository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Angajat> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Angajat save(Angajat stat) {
        return this.repository.save(stat);
    }

    @Override
    public void delete(Angajat stat){
        this.repository.delete(stat);
    }

    @Override
    public Angajat update(Angajat stat){
        return this.repository.save(stat);
    }

    public Angajat getByName(String nume){return this.repository.findByNume(nume);}

    public Angajat getByStatFunctii(StatFunctii statFunctii){return this.repository.findByStatFunctii(statFunctii);}

    public AngajatInfoDTO getInfoAngajatById(Integer idAngajat){
        AngajatInfoDTO angajatInfoDTO = new AngajatInfoDTO();
        Angajat angajat= this.repository.findById(idAngajat).get();
        Departament departament = angajat.getDepartament();
        Facultate facultate = departament.getFacultate();
        Universitate universitate = facultate.getUniversitate();

        angajatInfoDTO.setIdAngajat(angajat.getIdAngajat());
        angajatInfoDTO.setNumeAngajat(angajat.getNume());
        angajatInfoDTO.setDepartament(departament);
        angajatInfoDTO.setFacultate(facultate);
        angajatInfoDTO.setUniversitate(universitate);

        return angajatInfoDTO;
    }

    public void addContractToAngajat(int idContract, int idAngajat){
        Angajat angajat = findById(idAngajat).get();

        ContractTerti contractTerti = contractTertiRepository.findById(idContract).get();

        angajat.getListaContracte().add(contractTerti);
        repository.save(angajat);
    }
}
