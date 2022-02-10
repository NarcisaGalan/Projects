package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.model.Adresa;

import utcn.ti.proiect_licenta.service.AdresaService;
import utcn.ti.proiect_licenta.service.BeneficiarService;
import utcn.ti.proiect_licenta.service.UniversitateService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/adresa")
public class AdresaController {
    @Autowired
    private AdresaService adresaService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addAdresa(@RequestBody Adresa adresa){
        try {
            this.adresaService.save(adresa);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public  ResponseEntity<?> updateAdresa(@RequestBody Adresa adresa){
        try{
           if( this.adresaService.findById(adresa.getIdAdresa()).isPresent()){
               this.adresaService.save(adresa);
               return ResponseEntity.ok().build();
           }else
               return new ResponseEntity("INTERNAL_SERVER_ERROR" + "This address was not found in DB", HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAdreses() {
        try {
            List<Adresa> adrese = this.adresaService.findAll();

            return ResponseEntity.ok().body(adrese);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



}