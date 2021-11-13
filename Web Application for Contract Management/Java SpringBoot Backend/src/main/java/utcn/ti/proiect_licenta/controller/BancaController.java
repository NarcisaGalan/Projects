package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.model.Banca;
import utcn.ti.proiect_licenta.service.BancaService;


import java.util.List;
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/banca")
public class BancaController {

    @Autowired
    private BancaService bancaService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addBanca(@RequestBody Banca banca){
        try {
            this.bancaService.save(banca);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public  ResponseEntity<?> updateBanca(@RequestBody Banca banca){
        try{
            if( this.bancaService.findById(banca.getIdBanca()).isPresent()){
                this.bancaService.save(banca);
                return ResponseEntity.ok().build();
            }else
                return new ResponseEntity("INTERNAL_SERVER_ERROR" + "This Banca was not found in DB", HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBancs() {
        try {
            List<Banca> banci = this.bancaService.findAll();

            return ResponseEntity.ok().body(banci);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
