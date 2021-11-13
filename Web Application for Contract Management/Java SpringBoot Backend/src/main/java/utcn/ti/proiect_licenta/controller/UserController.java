package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.AdaugareUtilizatorDTO;
import utcn.ti.proiect_licenta.dto.UserDTO;
import utcn.ti.proiect_licenta.model.Angajat;
import utcn.ti.proiect_licenta.model.Departament;
import utcn.ti.proiect_licenta.model.Membru;
import utcn.ti.proiect_licenta.model.User;
import utcn.ti.proiect_licenta.repository.DepartamentRepository;
import utcn.ti.proiect_licenta.repository.MembruRepository;
import utcn.ti.proiect_licenta.service.AngajatService;
import utcn.ti.proiect_licenta.service.EmailSender;
import utcn.ti.proiect_licenta.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private MembruRepository membruRepository;
    @Autowired
    private DepartamentRepository departamentRepository;
    @Autowired
    private AngajatService angajatService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addUser(@RequestBody AdaugareUtilizatorDTO adaugareUtilizatorDTO){
        try {

            User user = new User(adaugareUtilizatorDTO.getEmail(),adaugareUtilizatorDTO.getParola(),adaugareUtilizatorDTO.getRole());
            //salvam noul user in baza de date
            User userDB = this.userService.save(user);
            String userRole = adaugareUtilizatorDTO.getRole();
            if(userRole.equals("admin")){

                //cream un nou membru
                Membru membruNou = new Membru(adaugareUtilizatorDTO.getNume(),adaugareUtilizatorDTO.getFunctie());
                //adaugam legatura intre membru si user
                membruNou.setUser(userDB);
                //salvam membrul in db
                membruRepository.save(membruNou);

            }else {
                //cautam departamenul dupa nume in db
                Departament departament = departamentRepository.findByNume(adaugareUtilizatorDTO.getNumeDepartament());
                //cream un nou angajat
                Angajat angajatNou = new Angajat(adaugareUtilizatorDTO.getNume(),departament);
                //salvam angajatul in baza de date
                Angajat angajatDB = angajatService.save(angajatNou);

                //facem legatura user - angajat
                angajatService.addAngajatToUser(angajatDB.getIdAngajat(),userDB.getIdUser());

            }
            //trimitem email noului user cu credentiale pentru aplicatie
            emailSender.sendRegisterEmail(user.getEmail(),user.getPassword());

            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        try {
            List<User> userList = this.userService.findAll();

            return ResponseEntity.ok().body(userList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getRole")
    public ResponseEntity<?> getRole(@PathVariable Integer userId){
            try {
                String role =   this.userService.findById(userId).get().getRole();
                return ResponseEntity.ok().body(role);

            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity("INTERNAL_SERVER_ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @PutMapping("/changePassword/{idUser}")
    public ResponseEntity<?> changePassword(@PathVariable Integer idUser,@RequestBody String parolaNoua){
        try {
            User user = userService.findById(idUser).get();
            userService.update(user, parolaNoua);
            return ResponseEntity.ok().body(user);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
