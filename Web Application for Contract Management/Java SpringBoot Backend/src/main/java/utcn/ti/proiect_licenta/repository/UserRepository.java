package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

}
