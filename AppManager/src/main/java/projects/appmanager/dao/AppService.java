package projects.appmanager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import projects.appmanager.models.App;
import projects.appmanager.models.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppService {


    AppRepository appRepository;

    UserRepository userRepository;

    @Autowired
    public AppService(AppRepository appR, UserRepository userR) {

        this.appRepository = appR;
        this.userRepository = userR;
    }

    public App findAppById(Integer id) {

        return appRepository.findById(id).get();
    }

    public List<App> findAllApps() {
        List<App> apps = new ArrayList<>();
        appRepository.findAll().forEach(app -> apps.add(app));
        return apps;
    }


    public App saveApp(App app) {
        return appRepository.save(app);
    }

    public void deleteApp(Integer id) {

        appRepository.deleteById(id);
    }

    public void addUserToApp( int idApp,  int idUser) {

        App app = findAppById(idApp);

        User u = userRepository.findById(idUser).get();

        app.getUsers().add(u);
        appRepository.save(app);

    }
}
