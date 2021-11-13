package utcn.ti.proiect_licenta.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.model.Angajat;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.repository.AngajatRepository;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.repository.UserRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.ContractTertiServiceInterface;

import java.util.*;

@Service
public class ContractTertiService implements ContractTertiServiceInterface {

    @Autowired
    private ContractTertiRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AngajatRepository angajatRepository;

    @Override
    public List<ContractTerti> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<ContractTerti> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public ContractTerti save(ContractTerti stat) {
        return this.repository.save(stat);
    }

    @Override
    public void delete(ContractTerti stat){
        this.repository.delete(stat);
    }

    @Override
    public ContractTerti update(ContractTerti stat){
        return this.repository.save(stat);
    }

    public List<ContractTerti> findContracteByUser(Integer idUser) {

        List<ContractTerti> allContracts = this.repository.findAll();

        //lista contracte cu acelasi user
        List<ContractTerti> contractByUser= new ArrayList();
        //gaseste angajatul corespunzator user-ului
        Angajat userAngajat = angajatRepository.findByUser(userRepository.findById(idUser).get());
        allContracts.forEach((contract)->{
            //set de angajati pentru fiecare contract
            Set<Angajat> angajatiSet = contract.getListaAngajati();
            List<Angajat> angajati = new ArrayList<>(angajatiSet);
            //daca contractul contine id-ul user-ului inseamna ca acesta este director si se adauga la lista
            angajati.forEach((angajat) ->{
                if (angajat.equals(userAngajat)) {
                    contractByUser.add(contract);
                }
            } );

        });
        for (int i = 0; i < allContracts.size(); i++) {



        }


        return contractByUser;
    }

    public void addAngajatToContract(int idContract, int idAngajat) {

        ContractTerti contractTerti = findById(idContract).get();

        Angajat angajat = angajatRepository.findById(idAngajat).get();

        contractTerti.getListaAngajati().add(angajat);
        repository.save(contractTerti);

    }
}
