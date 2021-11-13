package utcn.ti.proiect_licenta.service.serviceInterface;

import utcn.ti.proiect_licenta.dto.UserDTO;
import utcn.ti.proiect_licenta.model.User;

public interface UserServiceInterface {
    void save(User user);

    UserDTO findByEmail(String username);
}