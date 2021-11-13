package utcn.ti.proiect_licenta.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.model.StatFunctii;
import utcn.ti.proiect_licenta.service.StatFunctiiService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/statFunctii")
public class StatFunctiiController {
    @Autowired
    private StatFunctiiService statFunctiiService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addStatFunctii(@RequestBody StatFunctii statFunctii){
        try {
            this.statFunctiiService.save(statFunctii);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public  ResponseEntity<?> updateStatFunctii(@RequestBody StatFunctii statFunctii){
        try{
            StatFunctii statFunctiiDB = statFunctiiService.findById(statFunctii.getIdStatFunctii()).get();
            statFunctiiDB.setFunctie(statFunctii.getFunctie());
            statFunctiiDB.setDataInceput(statFunctii.getDataInceput());
            statFunctiiDB.setDataSfarsit(statFunctii.getDataSfarsit());
            statFunctiiDB.setIncetareActivitate(statFunctii.getIncetareActivitate());
            this.statFunctiiService.save(statFunctiiDB);
            return ResponseEntity.ok().build();

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/incetareActivitate/{incetareActivitate}")
    public  ResponseEntity<?> updateIncetareActivitate(@RequestBody List<Integer> idStatFunctiiList,@PathVariable String incetareActivitate){
        try{
            if((idStatFunctiiList != null) &&(!idStatFunctiiList.isEmpty())){
                statFunctiiService.updateIncetareActivitate(idStatFunctiiList,incetareActivitate);

                return ResponseEntity.ok().build();
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nu exista functii care sa fie modificate!Selectati un contract mai intai!");

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllStatFunctii() {
        try {
            List<StatFunctii> contractTertiList = this.statFunctiiService.findAll();

            return ResponseEntity.ok().body(contractTertiList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
