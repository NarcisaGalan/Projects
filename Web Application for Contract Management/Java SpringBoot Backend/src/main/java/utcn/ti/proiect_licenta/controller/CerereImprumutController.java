package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.CerereImprumutDTO;
import utcn.ti.proiect_licenta.model.CerereImprumut;
import utcn.ti.proiect_licenta.service.CerereImprumutService;
import utcn.ti.proiect_licenta.service.ContractTertiService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/cerereImprumut")
public class CerereImprumutController {

    @Autowired
    private CerereImprumutService cerereImprumutService;
    @Autowired
    private ContractTertiService contractTertiService;

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
    @PutMapping(value = "/update/{id}/{aprobare}")
    public  ResponseEntity<?> updateAprobare(@PathVariable Integer id, @PathVariable String aprobare ){
        try{
            CerereImprumut cerereImprumut = this.cerereImprumutService.findById(id).get();
            cerereImprumut.setAprobata(Boolean.valueOf(aprobare));
            cerereImprumutService.update(cerereImprumut);
            return ResponseEntity.ok().build();

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
    @GetMapping(value = "/allAprobate/{idContract}")
    public ResponseEntity<?> getAllAprobateByContract(@PathVariable Integer idContract){
        try {
            List<CerereImprumut> cerereImprumutAprobataList = cerereImprumutService.findAllAprobateByContract(idContract);
            return ResponseEntity.ok().body(cerereImprumutAprobataList);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/titluProiecte/{idAngajat}")
    public ResponseEntity<?> getTitluProiecte(@PathVariable Integer idAngajat) {
        try {
            List<String> listaProiecte = this.contractTertiService.findTitluProiectByAngajat(idAngajat);

            return ResponseEntity.ok().body(listaProiecte);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
