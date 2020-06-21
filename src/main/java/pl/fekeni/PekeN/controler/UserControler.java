package pl.fekeni.PekeN.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.fekeni.PekeN.entity.User;
import pl.fekeni.PekeN.repository.RoleRepository;
import pl.fekeni.PekeN.service.ItemService;
import pl.fekeni.PekeN.service.UserService;

import org.springframework.ui.Model;

import javax.validation.Valid;



import org.springframework.security.core.Authentication;


@Controller
public class UserControler {

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }



    @GetMapping("/editUser/{id}")
    public String getEditUserForm(Model model, @PathVariable(name ="id")Long id)throws Exception{
        User userToEdit = userService.getUserById(id);

        model.addAttribute("userForm", userToEdit);
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles",roleRepository.findAll());
        model.addAttribute("formTab","active");
        model.addAttribute("editMode","true");

        return "user-form/user-view";
    }



    @PostMapping("/attack")
    public String attack(@RequestParam(value = "whatToAttack") String monsterType, Model model) {
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentUser);
        try {
            userService.attack(currentUser, monsterType);
        } catch (Exception e) {
            System.out.println("siema");
        }
        return "user-form/userHunt";
    }

    @PostMapping("/goToWork")
    public String goToWork(@RequestParam(value = "workType") String workType, Model model) {
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentUser);
        try {
            userService.work(currentUser, workType);
        } catch (Exception e) {
            System.out.println("siema");
        }
        return "user-form/userWork";
    }


    @PostMapping("/fight")
    public ResponseEntity<String> fight(Model model) throws Exception {
   // public String fight(Model model) throws Exception {
        System.out.println("walcze walki: ");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        User user = userService.getCurrentUser(currentUserEmail);

        User enemyUser = userService.getUserById(user.getChallange());

        int fightResult = userService.fight(user, enemyUser);

        model.addAttribute("user", enemyUser);

        System.out.println("walcze controller wynik walki: " + fightResult);

        if (fightResult < 2) {
            return new ResponseEntity<>(
                    "You lost fight",
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                "You won fight",
                HttpStatus.OK);

    //    return "redirect:/home";

    }



    @PostMapping("/updateStat")
    public String updateStat(@RequestParam(value = "statistic") String chosenStat, Model model) {
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentUser);

        String stat = chosenStat;

        try {
            userService.addStat(currentUser, stat);
        } catch (Exception e) {
            System.out.println("siema");
        }

        return "user-form/userTrening";
    }




    }
