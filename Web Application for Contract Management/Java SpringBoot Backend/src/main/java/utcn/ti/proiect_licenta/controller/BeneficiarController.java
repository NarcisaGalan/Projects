package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.BeneficiarDTO;
import utcn.ti.proiect_licenta.model.Adresa;
import utcn.ti.proiect_licenta.model.Banca;
import utcn.ti.proiect_licenta.model.Beneficiar;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.repository.AdresaRepository;
import utcn.ti.proiect_licenta.repository.BancaRepository;
import utcn.ti.proiect_licenta.repository.BeneficiarRepository;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.service.BeneficiarService;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/beneficiar")
public class BeneficiarController {


    @Autowired
    private BeneficiarService beneficiarService;
    @Autowired
    BeneficiarRepository beneficiarRepository;
    @Autowired
    private ContractTertiRepository contractTertiRepository;
    @Autowired
    private AdresaRepository adresaRepository;
    @Autowired
    private BancaRepository bancaRepository;


    @PostMapping(value = "/saveBeneficiar")
    public ResponseEntity<?> saveBeneficiar(@RequestBody BeneficiarDTO beneficiarDTO){
        try {
            //initializam toate obiectele noi trimise din front
            Adresa adresa =new Adresa(beneficiarDTO.getTara(),beneficiarDTO.getJudet(),beneficiarDTO.getLocalitate(),beneficiarDTO.getStrada(),beneficiarDTO.getNumarStrada());
            Banca banca = new Banca(beneficiarDTO.getBanca(),beneficiarDTO.getCont());
            Beneficiar beneficiarNou =new Beneficiar(beneficiarDTO.getNumeBeneficiar(),beneficiarDTO.getCif(),beneficiarDTO.getNumarTelefon(),
                    beneficiarDTO.getAdresaEmailBeneficiar(),beneficiarDTO.getReprezentant(),beneficiarDTO.getFunctieReprezentant());

            //daca exista un beneficiar cu acelasi cif
            if (beneficiarRepository.existsBeneficiarByCifCui(beneficiarDTO.getCif())){
                Beneficiar benefiiciarOriginal= beneficiarService.findByCifCui(beneficiarDTO.getCif());
                Boolean bancaExistenta = benefiiciarOriginal.getBanci().contains(banca);
                //daca beneficiarul transmis e egal cu cel din db, atunci nu se schimba nimic
                if(benefiiciarOriginal.equals(beneficiarNou) && benefiiciarOriginal.getAdresa().equals(adresa) ){
                    System.out.println("Beneficiar existennt!!!!");
                    return new ResponseEntity<>("Beneficiar existent in DB", HttpStatus.ACCEPTED);
                }else{

                    //inseamna ca se face editare
                    //update adresa
                    adresa.setIdAdresa(benefiiciarOriginal.getAdresa().getIdAdresa());
                    adresaRepository.save(adresa);
                    //update beneficiar
                    beneficiarNou.setIdBeneficiar(benefiiciarOriginal.getIdBeneficiar());
                    beneficiarNou.setAdresa(adresa);
                    //daca lista de banci a beneficiarului nu contine banca transmisă, se adaugă
                    if(!bancaExistenta){
                        benefiiciarOriginal.getBanci().add(banca);
                        bancaRepository.save(banca);
                        System.out.println("a intrat in if la banca");
                    }

                    beneficiarService.save(benefiiciarOriginal);
                    return ResponseEntity.ok().build();
                }

            }

            //daca nu e nici una dintre variantele de mai sus, inseamna ca e un beneficiar nou, si il adaugam in baza de date
            adresa= adresaRepository.save(adresa);
            banca = bancaRepository.save(banca);
            beneficiarNou.setAdresa(adresa);
            Set<Banca> bancaSet = new HashSet();
            bancaSet.add(banca);
            beneficiarNou.setBanci(bancaSet);
            beneficiarService.save(beneficiarNou);
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
