package projects.appmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.appmanager.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}

