package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import utcn.ti.proiect_licenta.dto.ContractTertiDTO;
import utcn.ti.proiect_licenta.dto.ContractTertiLunarDTO;
import utcn.ti.proiect_licenta.model.ActAditional;
import utcn.ti.proiect_licenta.model.Beneficiar;
import utcn.ti.proiect_licenta.model.ContractTerti;

import utcn.ti.proiect_licenta.model.StatFunctii;
import utcn.ti.proiect_licenta.repository.ActAditionalRepository;
import utcn.ti.proiect_licenta.repository.BeneficiarRepository;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.repository.CursValutarRepository;
import utcn.ti.proiect_licenta.service.*;

import java.sql.Date;
import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/contractTerti")
public class ContractTertiController {
    @Autowired
    private ContractTertiService contractTertiService;

    @Autowired
    private CursValutarService cursValutarService;



    @PostMapping(value = "/add")
    public ResponseEntity<?> addContractTerti(@RequestBody ContractTertiDTO contractTerti){
        try {
            contractTertiService.addContractTerti(contractTerti);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public  ResponseEntity<?> updateContractTerti(@RequestBody ContractTertiDTO contractTertiDTO){
        try{

          ContractTerti contractTerti1 = contractTertiService.findById(contractTertiDTO.getIdContractTerti()).get();
          Double valoareTotalaContractVeche = contractTerti1.getValoare();
          contractTerti1.setNumar(contractTertiDTO.getNumarContract());
          contractTerti1.setData(contractTertiDTO.getDataContract());
          contractTerti1.setTip(contractTertiDTO.getTip());
          contractTerti1.setTitluProiect(contractTertiDTO.getTitlu());
          contractTerti1.setValoare(contractTertiDTO.getValTotala());
          contractTerti1.setMoneda(contractTertiDTO.getMoneda());
          contractTerti1.setDataIncheierii(contractTertiDTO.getDataIncheiere());
          contractTerti1.setNrPagini(contractTertiDTO.getNumarPagini());
          contractTerti1.setNrExemplare(contractTertiDTO.getNumarDeExemplare());
          contractTerti1.setNrExemplareBeneficiar(contractTertiDTO.getExemplareBeneficiar());
          contractTerti1.setDataInceput(contractTertiDTO.getDataInceput());
          contractTerti1.setDataSfarsit(contractTertiDTO.getDataSfarsit());
          contractTerti1.setCoordonatorPartener(contractTertiDTO.getPartener());
          contractTerti1.setTva(contractTertiDTO.getValTVA());
          contractTerti1.setIdContractTerti(contractTertiDTO.getIdContractTerti());
            if(!valoareTotalaContractVeche.equals(contractTertiDTO.getValTotala()))
            {
                java.sql.Date dataCurenta = java.sql.Date.valueOf(new Date(System.currentTimeMillis()).toString());
                Double valoareInLei = cursValutarService.procesareCursValutar(contractTertiDTO.getMoneda(),dataCurenta,contractTertiDTO.getValTotala());
                contractTerti1.setValoareInLei(valoareInLei);

            }
                this.contractTertiService.save(contractTerti1);
                return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/byMonth/{data}")
    public ResponseEntity<?> getAllContractTerti(@PathVariable Date data) {
        try {
           List<ContractTertiLunarDTO> contractTertiLunarDTOList = contractTertiService.getAllByMonth(data);
            return ResponseEntity.ok().body(contractTertiLunarDTOList);
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



}
