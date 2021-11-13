package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import utcn.ti.proiect_licenta.dto.AngajatDTO;
import utcn.ti.proiect_licenta.dto.UserDTO;
import utcn.ti.proiect_licenta.service.RegisterService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class RegistrationController {
    @Autowired
    RegisterService registerService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<?> registerNewClient(@RequestPart UserDTO userDTO,@RequestPart AngajatDTO angajatDTO) {
        try {

            registerService.registerNewAngajat(userDTO,angajatDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
