package pl.fekeni.PekeN.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.fekeni.PekeN.entity.User;
import pl.fekeni.PekeN.service.ItemService;
import pl.fekeni.PekeN.service.UserService;

import javax.validation.Valid;

@Controller
public class UserActionController {

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;



    @GetMapping("/userInfo")
    public String userInfo(Model model){
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentUser);
        return "user-form/userHome";
    }

    @GetMapping("/training")
    public String training(Model model){
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentUser);
        return "user-form/userTrening";
    }

    @GetMapping("/hunt")
    public String hunt(Model model){
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentUser);
        return "user-form/userHunt";
    }

    @GetMapping("/work")
    public String work(Model model){
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentUser);
        return "user-form/userWork";
    }


    @GetMapping("/arena")
    public String arena(Model model){
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentUser);
        model.addAttribute("userList", userService.getAllUsers());
        return "arena/arena";
    }

    @GetMapping("/ranking")
    public String ranking(Model model){
        model.addAttribute("userList", userService.getAllByOrderByLvlDesc());
        return "ranking";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("itemList", itemService.getAllItems());
        return "shop";
    }

 /*   @GetMapping("/home")
    public String home(Model model){
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentUser);
        return "home";
    }*/



}
