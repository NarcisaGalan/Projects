package utcn.ti.proiect_licenta.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.AngajatDTO;
import utcn.ti.proiect_licenta.dto.AngajatInfoDTO;
import utcn.ti.proiect_licenta.dto.StatFunctiiAndAngajatDTO;
import utcn.ti.proiect_licenta.model.*;
import utcn.ti.proiect_licenta.repository.AngajatRepository;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.repository.UserRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.AngajatServiceInterface;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AngajatService implements AngajatServiceInterface {

    @Autowired
    private AngajatRepository repository;
    @Autowired
    private ContractTertiService contractTertiService;
    @Autowired
    private StatFunctiiService statFunctiiService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Angajat> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Angajat> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Angajat save(Angajat angajat) {
        return this.repository.save(angajat);
    }

    @Override
    public void delete(Angajat angajat){
        this.repository.delete(angajat);
    }

    @Override
    public Angajat update(Angajat angajat){
        return this.repository.save(angajat);
    }

    public Angajat getByName(String nume){return this.repository.findByNume(nume);}

    public Angajat getByStatFunctii(StatFunctii statFunctii){return this.repository.findByFunctii(statFunctii);}

    public AngajatInfoDTO getInfoAngajatById(Integer idAngajat){
        AngajatInfoDTO angajatInfoDTO = new AngajatInfoDTO();
        Angajat angajat= repository.findById(idAngajat).get();
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

    //cautare angajati in functie de contractele directorului si dupa nume angajat
    public List<AngajatDTO> findByContractDirectorContaining(Integer idDirector, String numeAngajat){
        //lista de angajati
        List<AngajatDTO> listaAngajati = new ArrayList<>();
        //lista de contracte ale directorului
        List<ContractTerti> listaContracte = contractTertiService.findContracteByUser(idDirector);

        //parcurgem lista de contracte ale directorului, si luam toti angajatii care au colaborat la ele
        for (ContractTerti contractTerti: listaContracte) {
            List<StatFunctii> functiiByContract = new ArrayList<>(contractTerti.getStatFunctiiList());
            for (StatFunctii functieAngajat : functiiByContract) {
                Angajat angajat = functieAngajat.getAngajat();
                AngajatDTO angajatDTO = new AngajatDTO(angajat.getIdAngajat(),angajat.getNume(),angajat.getDepartament());

                //pentru a nu include duplicate, un angajat poate sa lucreze la mai multe proiecte
                if((!listaAngajati.contains(angajatDTO))){
                    if(angajat.getNume().contains(numeAngajat)){
                        System.out.println(angajatDTO.getNume());
                        listaAngajati.add(angajatDTO);
                    }
                }
            }
        }

        return listaAngajati;
    }

    public void addStatFunctiiSiAngajat(StatFunctiiAndAngajatDTO obiect){
        //tot timpul o sa existe un angajat, pentru ca trebuie sa fie angajat UTCN
        Angajat angajat = this.repository.findByNume(obiect.getNume());
        ContractTerti contractTerti = contractTertiService.findById(obiect.getContractTertiId()).get();
        StatFunctii statFunctii = new StatFunctii(obiect.getFunctie(),obiect.getDataInceput(),obiect.getDataSfarsit(),false);
        statFunctii.setAngajat(angajat);
        statFunctii.setContractTerti(contractTerti);
        StatFunctii statFunctiiDB = statFunctiiService.save(statFunctii);
        contractTerti.getStatFunctiiList().add(statFunctiiDB);
        contractTertiService.save(contractTerti);
    }
    public void addAngajatToUser(Integer idAngajat, Integer idUser){
        Angajat angajat = repository.findById(idAngajat).get();
        User user = userRepository.findById(idUser).get();

        angajat.setUser(user);
        user.setAngajat(angajat);

        repository.save(angajat);
        userRepository.save(user);

    }



}
