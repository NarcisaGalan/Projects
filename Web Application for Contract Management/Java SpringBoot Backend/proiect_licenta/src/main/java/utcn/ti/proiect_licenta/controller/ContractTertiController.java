package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import utcn.ti.proiect_licenta.dto.ContractBeneficiarExistentDTO;
import utcn.ti.proiect_licenta.dto.ContractTertiDTO;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.service.ContractTertiService;
import utcn.ti.proiect_licenta.service.PdfContentBuilder;
import utcn.ti.proiect_licenta.service.SchemeFormulareService;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/contractTerti")
public class ContractTertiController {
    @Autowired
    private ContractTertiService contractTertiService;
    @Autowired
    private SchemeFormulareService schemeFormulareService;

    @Autowired
    private PdfContentBuilder pdfContentBuilder;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addContractTerti(@RequestBody ContractTertiDTO contractTerti){
        try {
            this.schemeFormulareService.addContractTerti(contractTerti);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addBeneficiarExistent")
    public ResponseEntity<?> addContractTertiBeneficiarExistent(@RequestBody ContractBeneficiarExistentDTO contractTerti){
        try {
            this.schemeFormulareService.addContractTertiBeneficiarExistent(contractTerti);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public  ResponseEntity<?> updateContractTerti(@RequestBody ContractTerti contractTerti){
        try{
            if( this.contractTertiService.findById(contractTerti.getIdContractTerti()).isPresent()){
                this.contractTertiService.save(contractTerti);
                return ResponseEntity.ok().build();
            }else
                return new ResponseEntity("INTERNAL_SERVER_ERROR" + "This ContractTerti was not found in DB", HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllContractTerti() {
        try {
            List<ContractTerti> contractTertiList = this.contractTertiService.findAll();

            return ResponseEntity.ok().body(contractTertiList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all/{idUser}")
    public ResponseEntity<?> getAllContractTertiByUser(@PathVariable Integer idUser) {
        try {
            List<ContractTerti> contractTertiList = this.contractTertiService.findContracteByUser(idUser);

            return ResponseEntity.ok().body(contractTertiList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> testController(){
        ContractTertiDTO contractTertiDTO = new ContractTertiDTO();

        contractTertiDTO.setNumarContract(123331);
        Date myDate2 = new Date(System.currentTimeMillis());
        contractTertiDTO.setDataContract(myDate2);


        String htmlString = pdfContentBuilder.buildContractTerti(contractTertiDTO);
        try {
            pdfContentBuilder.generatePdfFromHtml(htmlString, "filename-contract.pdf");

//            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");

            String contract_test = "contract-test";


            InputStream input = getClass().getResourceAsStream("F:/Licenta/licenta/proiect_licenta/filename-contract.pdf");

            InputStreamResource inputStreamResource = new InputStreamResource(input);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(Files.size(Paths.get("F:/Licenta/licenta/proiect_licenta/filename-contract.pdf")));
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("FAIL" , HttpStatus.NOT_FOUND);
        }
    }
}
