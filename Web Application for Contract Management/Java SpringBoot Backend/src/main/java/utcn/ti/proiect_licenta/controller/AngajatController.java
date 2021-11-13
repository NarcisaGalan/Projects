package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.AngajatDTO;
import utcn.ti.proiect_licenta.dto.StatFunctiiAndAngajatDTO;
import utcn.ti.proiect_licenta.model.*;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.service.AngajatService;
import utcn.ti.proiect_licenta.service.ContractTertiService;
import utcn.ti.proiect_licenta.service.StatFunctiiService;

import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/angajat")
public class AngajatController {

    @Autowired
    private AngajatService angajatService;

    @Autowired
    private StatFunctiiService statFunctiiService;
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
            ContractTerti contractTerti = this.contractTertiService.findById(idContract).get();
            Set<StatFunctii> functiiByContract = contractTerti.getStatFunctiiList();
            List<StatFunctiiAndAngajatDTO> angajatiLista = new ArrayList();
            for(StatFunctii statFunctii : functiiByContract) {
                Angajat angajat = statFunctii.getAngajat();
                StatFunctiiAndAngajatDTO statFunctiiAndAngajatDTO = new StatFunctiiAndAngajatDTO(angajat.getIdAngajat(),angajat.getNume(), angajat.getDepartament().getCodDepartament()
                       ,statFunctii.getIdStatFunctii(),statFunctii.getFunctie(), statFunctii.getDataInceput(), statFunctii.getDataSfarsit(),statFunctii.getIncetareActivitate(),idContract);
                angajatiLista.add(statFunctiiAndAngajatDTO);
            }
            Beneficiar beneficiar = contractTerti.getBeneficiar();
            HashMap<String, Object> response = new HashMap<String,Object>();
            response.put("angajati",angajatiLista);
            response.put("beneficiar",beneficiar.getDenumire());
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("SERVER_INTERNAL_ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addAngajatAndStatFunctii")
    public ResponseEntity<?> addStatFunctiiSiAngajat(@RequestBody StatFunctiiAndAngajatDTO obiect){
        try {

            angajatService.addStatFunctiiSiAngajat(obiect);

           return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchAngajati/byContracteDirector/{idDirector}/{numeAngajat}")
    public ResponseEntity<?> searchAngajatByContracteDirector(@PathVariable Integer idDirector, @PathVariable String numeAngajat){
        try{
            List<AngajatDTO> listaAngajati= angajatService.findByContractDirectorContaining(idDirector,numeAngajat);
            return  ResponseEntity.ok().body(listaAngajati);
        }catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Nici un beneficiar existent cu datele cautate");
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
