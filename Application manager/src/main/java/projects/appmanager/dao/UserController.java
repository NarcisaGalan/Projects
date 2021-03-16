package projects.appmanager.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projects.appmanager.models.App;
import projects.appmanager.models.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AppRepository appRepository;


   private final static Logger logger = Logger.getLogger(AppController.class);


    @GetMapping("/")
    public String showPage(Model model, @RequestParam(defaultValue = "0") int page){
        model.addAttribute("data",userRepository.
                findAll(new PageRequest(page,4)));
        model.addAttribute("currentPage",page);
        return "index";
    }


    @GetMapping("/userById")
    @ResponseBody
    public User findUserByID(Integer id) {

        logger.info("findUserByID executed");
        logger.warn("Can cause null pointer exception");

            return userRepository.findById(id).get();

    }

    @GetMapping("/findAppFromUser")
    @ResponseBody
    public Set<App> findAppFromUser(Integer idUser) {
        logger.info(" findAppFromUser executed");
        User user= findUserByID(idUser);
        return user.getApps();
    }

    @GetMapping("/findAllUsers")
    @ResponseBody
    public List<User> findAllUsers() {

        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(user -> users.add(user));
        logger.info("findAllUsers executed");

        return users;
    }

    @PostMapping("/addUser")
    public String addUser(User user) {
        if (user != null) {
            userRepository.save(user);
            logger.info("addUser executed");
        }else{
            logger.error("User=null");
        }
        return "redirect:/";
    }

    @PutMapping("/updateUser/{id}")
    @ResponseBody
    public User updateUser(@PathVariable int id, @RequestBody User user) {

        logger.info("updateUser executed");
        return userRepository.save(user);

    }

    @GetMapping("/deleteUser")
    public String deleteUser(Integer id) {

        if(id!=null) {
            userRepository.deleteById(id);
            logger.info("deleteUser executed");
        }else
            logger.error("user undefined");
        return "redirect:/";

    }

    @PostMapping("/addAppToUser/{idUser}")
    @ResponseBody
    public void addAppToUser(@PathVariable int idUser, @RequestBody int idApp) {

        User user = findUserByID(idUser);

        App app = appRepository.findById(idApp).get();

        user.getApps().add(app);
        userRepository.save(user);
        logger.info("addUserToApp executed");
    }

}