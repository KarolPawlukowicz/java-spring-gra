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
import pl.fekeni.PekeN.service.UserService;

import org.springframework.ui.Model;

import javax.validation.Valid;



import org.springframework.security.core.Authentication;


@Controller
public class UserControler {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/ranking")
    public String ranking(Model model){
        model.addAttribute("userForm", new User());
        model.addAttribute("userList", userService.getAllUsers());
      //  model.addAttribute("listTab", "active");
        return "ranking";
    }

    @GetMapping("/arena")
    public String arena(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        User user = userService.getCurrentUser(currentUserEmail);

        model.addAttribute("user", user);
        model.addAttribute("userList", userService.getAllUsers());
        //  model.addAttribute("listTab", "active");
        return "arena";
    }


    @GetMapping("/arenaDetail")
    public String arenaDetail(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        User user = userService.getCurrentUser(currentUserEmail);

        User enemyUser = userService.getUserById(user.getChallange());

        model.addAttribute("user", enemyUser);

        return "arenaDetail";
    }


    @GetMapping("/userForm")
    public String userForm(Model model){
        model.addAttribute("userForm", new User());
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("listTab", "active");
        return "user-form/user-view";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        User user = userService.getCurrentUser(currentUserEmail);

        model.addAttribute("user", user);

        return "user-form/userHome";
    }

    @GetMapping("/trening")
    public String trening(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        User user = userService.getCurrentUser(currentUserEmail);

        model.addAttribute("user", user);

        return "user-form/userTrening";
    }

    @GetMapping("/polowanie")
    public String polowanie(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        User user = userService.getCurrentUser(currentUserEmail);

        model.addAttribute("user", user);

        return "user-form/userPolowanie";
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


    @PostMapping("/editUser")
    public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            model.addAttribute("userForm", user);
            model.addAttribute("formTab","active");
            model.addAttribute("editMode","true");
        }else {
            try {
                userService.updateUser(user);
                model.addAttribute("userForm", new User());
                model.addAttribute("listTab","active");
            } catch (Exception e) {
                model.addAttribute("formErrorMessage",e.getMessage());
                model.addAttribute("userForm", user);
                model.addAttribute("formTab","active");
                model.addAttribute("userList", userService.getAllUsers());
                model.addAttribute("roles",roleRepository.findAll());
                model.addAttribute("editMode","true");
            }
        }

        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles",roleRepository.findAll());
        return "user-form/user-view";

    }

    @GetMapping("/userForm/cancel")
    public String cancelEditUser(ModelMap model) {
        return "redirect:/userForm";
    }

    @GetMapping("/wyzwijGracza/{id}")
    public String wyzwijGracza(Model model, @PathVariable(name ="id")Long id)throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        User user = userService.getCurrentUser(currentUserEmail);
        System.out.println("from: " + user.getNickName());

        User userToChallange = userService.getUserById(id);
        System.out.println("to: " + userToChallange.getNickName());

        userService.challangeUser(userToChallange, user.getId());

        return "home";
    }


    @PostMapping("/updateStat")
    public String updateStat(@RequestParam(value = "statistic") String chosenStat) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        User user = userService.getCurrentUser(currentUserEmail);

        String stat = chosenStat;

        try {
            userService.addStat(user, stat);
        } catch (Exception e) {
            System.out.println("siema");
        }

        return "redirect:/home";
    }

    @PostMapping("/work")
    public String work(@RequestParam(value = "workType") String workType) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        User user = userService.getCurrentUser(currentUserEmail);

        try {
            userService.work(user, workType);
        } catch (Exception e) {
            System.out.println("siema");
        }

        return "redirect:/home";
    }


    @PostMapping("/attack")
    public String attack(@RequestParam(value = "whatToAttack") String monsterType) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        User user = userService.getCurrentUser(currentUserEmail);

        try {
            userService.attack(user, monsterType);
        } catch (Exception e) {
            System.out.println("siema");
        }

        return "redirect:/home";
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




    }
