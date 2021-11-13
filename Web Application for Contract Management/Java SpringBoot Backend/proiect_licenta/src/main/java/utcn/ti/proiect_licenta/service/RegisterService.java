package utcn.ti.proiect_licenta.service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.AngajatDTO;
import utcn.ti.proiect_licenta.dto.UserDTO;
import utcn.ti.proiect_licenta.model.Angajat;
import utcn.ti.proiect_licenta.model.User;
import utcn.ti.proiect_licenta.repository.AngajatRepository;
import utcn.ti.proiect_licenta.repository.UserRepository;


@Service
public class RegisterService {
    @Autowired
   private AngajatRepository angajatRepository;

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerNewAngajat(UserDTO userDTO ,AngajatDTO angajatDTO) throws Exception {
        User user = getUserWithRoleAndEncryptedPassword(userDTO);
        Angajat angajat=new Angajat();
        angajat.setDepartament(angajatDTO.getDepartament());
        angajat.setNume(angajatDTO.getNume());
        try {
            userRepository.save(user);
            angajatRepository.save(angajat);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private User getUserWithRoleAndEncryptedPassword(UserDTO userDTO) {
        User user = new User(userDTO.getEmail(), userDTO.getPassword(), "angajat");
        user.setPassword(this.bCryptPasswordEncoder.encode(userDTO.getPassword()));

        return user;
    }
}
