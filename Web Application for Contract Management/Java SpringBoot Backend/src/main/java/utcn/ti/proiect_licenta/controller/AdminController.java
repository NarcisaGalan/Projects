package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.UserDTO;
import utcn.ti.proiect_licenta.model.Cheltuiala;
import utcn.ti.proiect_licenta.model.Departament;
import utcn.ti.proiect_licenta.model.Membru;
import utcn.ti.proiect_licenta.repository.DepartamentRepository;
import utcn.ti.proiect_licenta.repository.MembruRepository;
import utcn.ti.proiect_licenta.service.CheltuialaService;
import utcn.ti.proiect_licenta.service.EmailSender;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    EmailSender emailSender;
    @Autowired
    MembruRepository membruRepository;
    @Autowired
    DepartamentRepository departamentRepository;


    @PostMapping(value="/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody UserDTO userDTO){
        try {
            emailSender.sendRegisterEmail(userDTO.getEmail(),userDTO.getPassword());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addMembru")
    public ResponseEntity<?> addMembru(@RequestBody Membru membru){
        try {
            membruRepository.save(membru);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allDepartamente")
    public ResponseEntity<?> getAllDepartamente() {
        try {
            List<Departament> departamentList = this.departamentRepository.findAll();

            return ResponseEntity.ok().body(departamentList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
