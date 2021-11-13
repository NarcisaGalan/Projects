package utcn.ti.proiect_licenta.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.UserDTO;
import utcn.ti.proiect_licenta.dto.mapper.UserMapper;
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
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void save(User user) {
        System.out.println("PAROLAAAAA: "+user.getPassword());
        String password = passwordEncoder.encode(user.getPassword());
        User newUser =new  User(user.getEmail(),password,user.getRole());

        userRepository.save(newUser);
    }
    @Override
    public UserDTO findByEmail(String email) {

        User user = userRepository.findByEmail(email);
        UserDTO userDTO = userMapper.userToUserDTO(user);
        if(user.getAngajat()==null){
            userDTO.setIdAngajat(0);
        }else{
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