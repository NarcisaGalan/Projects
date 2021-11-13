package utcn.ti.proiect_licenta.controller;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import utcn.ti.proiect_licenta.dto.AngajatInfoDTO;
import utcn.ti.proiect_licenta.dto.LoginDTO;
import utcn.ti.proiect_licenta.dto.LoginInfoDTO;
import utcn.ti.proiect_licenta.dto.UserDTO;
import utcn.ti.proiect_licenta.jwt.JwtToken;
import utcn.ti.proiect_licenta.jwt.JwtUserDetailsService;
import utcn.ti.proiect_licenta.model.Membru;
import utcn.ti.proiect_licenta.repository.MembruRepository;
import utcn.ti.proiect_licenta.service.AngajatService;
import utcn.ti.proiect_licenta.service.UserService;

import java.util.HashMap;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UserService userService;
    @Autowired
    private AngajatService angajatService;
    @Autowired
    private MembruRepository membruRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO userCredentials) {

        try {
            authenticate(userCredentials.getEmail(), userCredentials.getPassword());

            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userCredentials.getEmail());
            final String token = jwtToken.generateToken(userDetails);

            UserDTO userDTO = userService.findByEmail(userCredentials.getEmail());
            userDTO.setToken(token);
            //daca user-ul nu este angajat
            if(userDTO.getIdAngajat() == 0){
                HashMap<String,Object> response = new HashMap<>();
                Membru membru = this.membruRepository.findById(userDTO.getIdMembru()).get();
                response.put("membru",membru);
                response.put("userDTO",userDTO);
                return ResponseEntity.ok().body(response);
            }else{
                AngajatInfoDTO  angajatInfoDTO= this.angajatService.getInfoAngajatById(userDTO.getIdAngajat());
                LoginInfoDTO loginInfoDTO = new LoginInfoDTO(userDTO,angajatInfoDTO);

                return ResponseEntity.ok().body(loginInfoDTO);
            }


        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

}