package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.model.Departament;
import utcn.ti.proiect_licenta.model.Facultate;
import utcn.ti.proiect_licenta.model.Universitate;
import utcn.ti.proiect_licenta.repository.DepartamentRepository;
import utcn.ti.proiect_licenta.repository.FacultateRepository;
import utcn.ti.proiect_licenta.repository.UniversitateRepository;
import utcn.ti.proiect_licenta.service.UniversitateService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/universitate")
public class UniversitateController {
    @Autowired
    private UniversitateService universitateService;

    @Autowired
    private UniversitateRepository universitateRepository;

    @Autowired
    private FacultateRepository facultateRepository;

    @Autowired
    private DepartamentRepository departamentRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addUniversitate(@RequestBody Universitate universitate){
        try {
            this.universitateService.save(universitate);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/add/facultate")
    public ResponseEntity<?> addFacultate(@RequestBody Facultate facultate){
        try {
            this.facultateRepository.save(facultate);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/add/departament")
    public ResponseEntity<?> addDepartament(@RequestBody Departament departament){
        try {
            this.departamentRepository.save(departament);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/update")
    public  ResponseEntity<?> updateUniversitate(@RequestBody Universitate universitate){
        try{
            if( this.universitateService.findById(universitate.getIdUniversitate()).isPresent()){
                this.universitateService.save(universitate);
                return ResponseEntity.ok().build();
            }else
                return new ResponseEntity("INTERNAL_SERVER_ERROR" + "This universitate was not found in DB", HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUniversitate() {
        try {
            List<Universitate> universitateList = this.universitateService.findAll();

            return ResponseEntity.ok().body(universitateList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byCode/{codUniversitate}")
    public ResponseEntity<?> getByCode(@PathVariable String codUniversitate){
        try {
            Universitate universitate=this.universitateRepository.findByCodUniversitate(codUniversitate);
            return ResponseEntity.ok().body(universitate);
        }catch (Exception e ){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idUniversitate}/facultate")
    public ResponseEntity<?> getAllFacultateByUniversitate(@PathVariable Integer idUniversitate) {
        try {
            Universitate universitate = this.universitateService.findById(idUniversitate).get();
            List<Facultate> facultateList = this.facultateRepository.findAllByUniversitate(universitate);

            return ResponseEntity.ok().body(facultateList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idFacultate}/departament")
    public ResponseEntity<?> getAllDepartamentByFacultate(@PathVariable Integer idFacultate) {
        try {
            Facultate facultate = this.facultateRepository.findById(idFacultate).get();
            List<Departament> departamentList = this.departamentRepository.findAllByFacultate(facultate);

            return ResponseEntity.ok().body(departamentList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
