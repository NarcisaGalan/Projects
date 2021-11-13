package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.RaportBuget;
import utcn.ti.proiect_licenta.model.*;
import utcn.ti.proiect_licenta.repository.CerereImprumutRepository;
import utcn.ti.proiect_licenta.repository.IncasareRepository;
import utcn.ti.proiect_licenta.service.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/raport")
public class RapoarteController {
    @Autowired
    ContractTertiService contractTertiService;
    @Autowired
    CheltuialaService cheltuialaService;
    @Autowired
    CerereImprumutService cerereImprumutService;
    @Autowired
    IncasareService incasareService;
    @Autowired
    CursValutarService cursValutarService;

    @GetMapping(value="/cursValutar/{moneda}/{data}")
    public ResponseEntity<?> getCursValutarByMonedaAndData(@PathVariable String moneda , @PathVariable Date data){
        try {
            CursValutar cursValutar;
            cursValutar = cursValutarService.findByDataAndMoneda(data,moneda);
            return ResponseEntity.ok().body(cursValutar);
        }catch (Exception e){
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/buget/{idContract}")
    public ResponseEntity<?> getRaportBuget(@PathVariable Integer idContract){
        try {

            RaportBuget raportBuget= contractTertiService.bugetContract(idContract);

            return ResponseEntity.ok().body(raportBuget);
        }catch (Exception e){
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/raportLunar/{startDate}/{endDate}/{idContract}")
    public ResponseEntity<?> getRaportLunar(@PathVariable Date startDate, @PathVariable Date endDate, @PathVariable Integer idContract) {
        try {
            ContractTerti contractTerti = contractTertiService.findById(idContract).get();
            List<Cheltuiala> cheltuialaList = this.cheltuialaService.getCheltuieliLunare(startDate,endDate,contractTerti);
            List<Incasare> incasareList = this.incasareService.getIncasariLunare(startDate,endDate,contractTerti);
            List<CerereImprumut> cerereImprumutList =this.cerereImprumutService.getImprumuturiLunare(startDate,endDate,contractTerti);


            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("cheltuieli", cheltuialaList);
            response.put("incasari", incasareList);
            response.put("cereriImprumut",cerereImprumutList);

            return ResponseEntity.ok().body(response );
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
