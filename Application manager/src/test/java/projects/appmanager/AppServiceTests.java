package projects.appmanager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import projects.appmanager.dao.AppRepository;
import projects.appmanager.dao.AppService;
import projects.appmanager.dao.UserRepository;
import projects.appmanager.models.App;
import projects.appmanager.models.User;


import static org.mockito.Mockito.when;

    @RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(locations = "classpath:pom.xml")
    @SpringBootTest
    public class AppServiceTests {

        @Autowired
        AppRepository appRepository;
        @Autowired
        UserRepository userRepository;

        App app=new App(20,"Eclipse","IDEA","v.10");
        User user=new User("Narcisa","g.narcisa","123");
        @InjectMocks
        AppService appService;


        @Test
        public void saveAppTest(){
            //when(appRepository.save(app)).thenReturn(app);
            App app2=appService.saveApp(app);
            App app3=appService.findAppById(app2.getId());
            Assert.assertEquals(app3.getName(),app2.getName());
            appRepository.delete(app2);
        }
        @Test
        public void deleteAppTest(){
            //when(appRepository.deleteById(id)).thenReturn(app);
            App a1=appRepository.save(app);
            appRepository.findById(a1.getId()).get();


            appService.deleteApp(a1.getId());
            App a2=appRepository.findById(a1.getId()).get();
            Assert.assertEquals(a1.getName(),a2.getName());
        }

        @Test
        public void addUserToAppTest(){

        App a=appRepository.save(app);
        User u=userRepository.save(user);

        appRepository.findById(a.getId());
        userRepository.findById(u.getId());

        appService.addUserToApp(a.getId(),u.getId());

        App a1=appRepository.findById(a.getId()).get();


        }

}
