package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.CheltuialaDTO;
import utcn.ti.proiect_licenta.dto.CheltuialaTable;
import utcn.ti.proiect_licenta.model.Cheltuiala;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.service.CheltuialaService;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/cheltuiala")
public class CheltuialaController {
    @Autowired
    private CheltuialaService cheltuialaService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addCheltuiala(@RequestBody CheltuialaDTO cheltuialaDTO){
        try {
            this.cheltuialaService.save(cheltuialaDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCheltuiala() {
        try {
            List<Cheltuiala> cheltuialaList = this.cheltuialaService.findAll();

            return ResponseEntity.ok().body(cheltuialaList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/allTable/{idContract}")
    public ResponseEntity<?> getAllCheltuialaTableByContractTerti(@PathVariable Integer idContract) {
        try {
            List<CheltuialaTable> listacaheltuieli = this.cheltuialaService.getCheltuieliTableByContractTerti(idContract);
            return ResponseEntity.ok().body(listacaheltuieli);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}