package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.CerereImprumutDTO;
import utcn.ti.proiect_licenta.model.CerereImprumut;
import utcn.ti.proiect_licenta.service.CerereImprumutService;
import utcn.ti.proiect_licenta.service.SchemeFormulareService;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/cerereImprumut")
public class CerereImprumutController {

    @Autowired
    private CerereImprumutService cerereImprumutService;
    @Autowired
    private SchemeFormulareService schemeFormulareService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addCerereImprumut(@RequestBody CerereImprumutDTO cerereImprumutDTO){
        try {
            this.cerereImprumutService.save(cerereImprumutDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public  ResponseEntity<?> updateCerereImprumut(@RequestBody CerereImprumut cerereImprumut){
        try{
            if( this.cerereImprumutService.findById(cerereImprumut.getIdCerereImprumut()).isPresent()){
                //this.cerereImprumutService.save(cerereImprumut);
                return ResponseEntity.ok().build();
            }else
                return new ResponseEntity("INTERNAL_SERVER_ERROR" + "This CerereImprumut was not found in DB", HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCerereImprumut() {
        try {
            List<CerereImprumut> cerereImprumutList = this.cerereImprumutService.findAll();

            return ResponseEntity.ok().body(cerereImprumutList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/titluProiecte/{idAngajat}")
    public ResponseEntity<?> getTitluProiecte(@PathVariable Integer idAngajat) {
        try {
            List<String> listaProiecte = this.schemeFormulareService.findTitluProiectByUser(idAngajat);

            return ResponseEntity.ok().body(listaProiecte);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
