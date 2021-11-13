package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.UserDTO;
import utcn.ti.proiect_licenta.model.User;
import utcn.ti.proiect_licenta.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            this.userService.save(user);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public  ResponseEntity<?> updateUser(@RequestBody User user){
        try{
            if( this.userService.findById(user.getIdUser()).isPresent()){
                //this.userService.save(user);
                return ResponseEntity.ok().build();
            }else
                return new ResponseEntity("INTERNAL_SERVER_ERROR" + "This user was not found in DB", HttpStatus.INTERNAL_SERVER_ERROR);

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
    //CHANGE PASSWORD
}
