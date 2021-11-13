package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.IncasareDTO;
import utcn.ti.proiect_licenta.model.Incasare;
import utcn.ti.proiect_licenta.repository.IncasareRepository;
import utcn.ti.proiect_licenta.service.IncasareService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/incasare")
public class IncasareController {
    @Autowired
    private IncasareService incasareService;


    @PostMapping(value = "/add")
    public ResponseEntity<?> addIncasare(@RequestBody IncasareDTO incasareDTO){
        try {
            this.incasareService.save(incasareDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/{idContract}")
    public ResponseEntity<?> getAllIncasareByContract(@PathVariable Integer idContract) {
        try {
            List<Incasare> incasareList= this.incasareService.findAllByContract(idContract);

            return ResponseEntity.ok().body(incasareList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
