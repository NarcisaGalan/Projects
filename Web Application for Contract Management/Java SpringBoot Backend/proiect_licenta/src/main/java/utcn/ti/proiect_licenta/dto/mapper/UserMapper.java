package utcn.ti.proiect_licenta.dto.mapper;

import org.mapstruct.Mapper;
import utcn.ti.proiect_licenta.dto.UserDTO;
import utcn.ti.proiect_licenta.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

}
