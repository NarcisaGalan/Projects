package utcn.ti.proiect_licenta.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.UserDTO;
import utcn.ti.proiect_licenta.model.User;
import utcn.ti.proiect_licenta.repository.UserRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.UserServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {

        String password = passwordEncoder.encode(user.getPassword());
        User newUser =new  User(user.getEmail(),password,user.getRole());

       return userRepository.save(newUser);
    }

    public User update(User user,String parolaNoua) {

        String password = passwordEncoder.encode(parolaNoua);

        user.setPassword(password);

        return userRepository.save(user);
    }


    @Override
    public UserDTO findByEmail(String email) {

        User user = userRepository.findByEmail(email);
        UserDTO userDTO = new UserDTO(user.getIdUser(), user.getEmail(),user.getPassword(),user.getRole());
        //daca user-ul nu este angajat
        if(user.getAngajat()==null){
            userDTO.setIdAngajat(0);
            userDTO.setIdMembru(user.getMembru().getIdMembru());
        }else{
            //daca user-ul este angajat
            userDTO.setIdAngajat(user.getAngajat().getIdAngajat());
        }

        return userDTO;

    }
    public List<User> findAll(){
        return this.userRepository.findAll();
    }
    public Optional<User> findById(Integer id){
        return this.userRepository.findById(id);
    }
}