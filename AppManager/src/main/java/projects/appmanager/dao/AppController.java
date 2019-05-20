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

@Controller
public class AppController {
    @Autowired
    AppRepository appRepository;

    @Autowired
    AppService appService;

    Logger logger = Logger.getLogger(AppController.class.getName());

    @GetMapping("/app")
    public String showPage(Model model, @RequestParam(defaultValue = "0") int page){
        model.addAttribute("dataApp",appRepository.
                findAll(new PageRequest(page,4)));
        model.addAttribute("currentPage",page);
        return "app";
    }


    @GetMapping("/appById")
    @ResponseBody
    public App findAppById(Integer id) {
        logger.info("findAppById executed");
        return appService.findAppById(id);
    }

    @GetMapping("/findAllApps/")
    @ResponseBody
    public List<App> findAllApps() {
        List<App> apps = this.appService.findAllApps();
        logger.info("FindAllApps executed");

        return apps;
    }

    @PostMapping("/addApp")
    public String saveApp(App app) {
        appService.saveApp(app);
        logger.info("saveApp executed");
        return  "redirect:/app";
    }

    @PutMapping("/updateApp/{id}")
    @ResponseBody
    public App updateApp(@PathVariable int id, @RequestBody App app) {
        appService.updateApp(id,app);
        logger.info("UpdateApp executed");
        return app;
    }


    @GetMapping("/deleteApp")
    public String deleteApp(Integer id) {
        appService.deleteApp(id);
        logger.info("deleteApp executed");
        return "redirect:/app";
    }


    @PostMapping("/addUserToApp/{idApp}")
    @ResponseBody
    public void addUserToApp(@PathVariable int idApp, @RequestBody int idUser) {
        appService.addUserToApp(idApp,idUser);
        logger.info("FindAllApps executed");
    }
}
