package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.model.Beneficiar;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.service.BeneficiarService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/beneficiar")
public class BeneficiarController {


    @Autowired
    private BeneficiarService beneficiarService;
    @Autowired
    private ContractTertiRepository contractTertiRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addBeneficiar(@RequestBody Beneficiar beneficiar){
        try {
            this.beneficiarService.save(beneficiar);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/saveBeneficiar")
    public ResponseEntity<?> saveBeneficiar(@RequestBody Beneficiar beneficiar){
        try {
            Beneficiar beneficiarDB= beneficiarService.findByCifCui(beneficiar.getCifCui());

            //daca e exact acelasi beneficiar, nu se salveaza
            if(beneficiarDB.equals(beneficiar) && beneficiarDB.getAdresa().equals(beneficiar.getAdresa())
                            && beneficiarDB.getBanci().equals(beneficiar.getBanci())){
                return new ResponseEntity<>("Beneficiar existent in DB", HttpStatus.ACCEPTED);
            }
            //daca au acelasi CIF/CUI si totusi nu sunt egale, inseamna ca se face update
            if ((!beneficiarDB.equals(beneficiar) || !beneficiarDB.getBanci().equals(beneficiar.getBanci())
                    || !beneficiarDB.getAdresa().equals(beneficiar.getAdresa()) )&& beneficiarDB.getCifCui().equals(beneficiar.getCifCui())){
                beneficiar.setIdBeneficiar(beneficiarDB.getIdBeneficiar());
                beneficiarService.save(beneficiar);
                return ResponseEntity.ok().build();
            }
            //daca nu e nici una dintre variantele de mai sus, inseamna ca e un beneficiar nou, si il adaugam in baza de date

            beneficiarService.save(beneficiar);
            return ResponseEntity.ok().build();

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public  ResponseEntity<?> updateBeneficiar(@RequestBody Beneficiar beneficiar){
        try{
           Beneficiar beneficiarDB=  this.beneficiarService.findByCifCui(beneficiar.getCifCui());

             return ResponseEntity.ok().build();

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBeneficiar() {
        try {
            List<Beneficiar> beneficiari = this.beneficiarService.findAll();

            return ResponseEntity.ok().body(beneficiari);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{idContract}")
    public ResponseEntity<?> getBeneficiarByContract(@PathVariable Integer idContract) {
        try {
            ContractTerti contractTerti= contractTertiRepository.findById(idContract).get();
            Beneficiar beneficiar = contractTerti.getBeneficiar();
            return ResponseEntity.ok().body(beneficiar);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchByDenumireContaining/{denumire}")
    public ResponseEntity<?> searchByDenumireContaining(@PathVariable String denumire){
        try{
            List<Beneficiar> listaBeneficiari= beneficiarService.findByDenumireContaining(denumire);
            return  ResponseEntity.ok().body(listaBeneficiari);
        }catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Nici un beneficiar existent cu datele cautate");
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByDenumireAndCifCui/{denumire}/{cifCui}")
    public ResponseEntity<?> getByDenumireAndCifCui(@PathVariable String denumire,@PathVariable String cifCui){
        try{
            Beneficiar beneficiar = beneficiarService.findByDenumireAndCifCui(denumire,cifCui);
            return  ResponseEntity.ok().body(beneficiar);

        }catch (NoSuchElementException e) {
		    return ResponseEntity.badRequest().body("Beneficiar NOT FOUND");
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
