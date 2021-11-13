package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.ContractTertiDTO;
import utcn.ti.proiect_licenta.dto.StatFunctiiAndAngajatDTO;
import utcn.ti.proiect_licenta.model.*;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.repository.DepartamentRepository;
import utcn.ti.proiect_licenta.service.AngajatService;
import utcn.ti.proiect_licenta.service.ContractTertiService;
import utcn.ti.proiect_licenta.service.StatFunctiiService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/angajat")
public class AngajatController {

    @Autowired
    private AngajatService angajatService;

    @Autowired
    private StatFunctiiService statFunctiiService;
    @Autowired
    private ContractTertiRepository contractTertiRepository;
    @Autowired
    private DepartamentRepository departamentRepository;
    @Autowired
    private ContractTertiService contractTertiService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addAngajat(@RequestBody Angajat angajat){
        try {
            this.angajatService.save(angajat);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateAngajat(Angajat angajat){
        try {
            if(angajatService.findById(angajat.getIdAngajat()).get().equals(angajat)){
                this.angajatService.save(angajat);
                return ResponseEntity.ok().build();
            }else
                return new ResponseEntity("ANGAJAT_NOT_FOUND",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/getInfoAngajatById")
    public ResponseEntity<?> getNumeAngajatAndUniversityInfoById(@PathVariable Integer idAngajat){
        try{
            Angajat angajat = this.angajatService.findById(idAngajat).get();
            return ResponseEntity.ok().body(angajat);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getByName")
    public ResponseEntity<?> getAngajatByName(@PathVariable String nume){
        try {
           Angajat angajat= this.angajatService.getByName(nume);
            return ResponseEntity.ok().body(angajat);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("SERVER_INTERNAL_ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getByStatFunctii/{idStatFunctii}")
    public ResponseEntity<?> getAngajatByStatFunctii(@PathVariable Integer idStatFunctii){
        try {
            StatFunctii statFunctii = this.statFunctiiService.findById(idStatFunctii).get();
            Angajat angajat=this.angajatService.getByStatFunctii(statFunctii);
            return ResponseEntity.ok().body(angajat);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("SERVER_INTERNAL_ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get functie si angajat by Contract
    @GetMapping(value = "/{idContract}")
    public ResponseEntity<?> getAngajatAndStatFunctii(@PathVariable Integer idContract){
        try {
            Set<Angajat> angajatiByContract = this.contractTertiRepository.findById(idContract).get().getListaAngajati();
            List<StatFunctiiAndAngajatDTO> angajatiLista = new ArrayList();
            for(Angajat angajat : angajatiByContract) {
                StatFunctii statFunctii = angajat.getStatFunctii();
                StatFunctiiAndAngajatDTO statFunctiiAndAngajatDTO = new StatFunctiiAndAngajatDTO(angajat.getIdAngajat(),angajat.getNume(), angajat.getDepartament().getCodDepartament(),
                       statFunctii.getIdStatFunctii(), statFunctii.getFunctie(), statFunctii.getDataInceput(), statFunctii.getDataSfarsit(),idContract);
                angajatiLista.add(statFunctiiAndAngajatDTO);
            }

            return ResponseEntity.ok().body(angajatiLista);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("SERVER_INTERNAL_ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addAngajatAndStatFunctii")
    public ResponseEntity<?> addAngajatAndStatFunctii(@RequestBody StatFunctiiAndAngajatDTO obiect){
        try {
            StatFunctii statFunctii = new StatFunctii(obiect.getFunctie(),obiect.getDataInceput(),obiect.getDataSfarsit());
            StatFunctii functieAngajat= statFunctiiService.save(statFunctii);
            Angajat angajat = new Angajat(obiect.getNume(),departamentRepository.findByCodDepartament(obiect.getCodDepartament()));
            angajat.setStatFunctii(functieAngajat);
            angajatService.save(angajat);
            ContractTerti contractTerti = this.contractTertiRepository.findById(obiect.getContractTertiId()).get();

            contractTertiService.addAngajatToContract(obiect.getContractTertiId(),angajatService.getByName(obiect.getNume()).getIdAngajat());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
