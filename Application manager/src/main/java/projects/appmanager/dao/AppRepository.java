package projects.appmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.appmanager.models.App;

public interface AppRepository extends JpaRepository<App, Integer> {


}
