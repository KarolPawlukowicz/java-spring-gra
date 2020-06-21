package pl.fekeni.PekeN.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.fekeni.PekeN.entity.User;
import pl.fekeni.PekeN.service.ArenaService;
import pl.fekeni.PekeN.service.UserService;


@Controller
public class ArenaController {

    @Autowired
    ArenaService arenaService;

    @Autowired
    UserService userService;

    @PostMapping("/challangeUser/{id}")
    public String challangeUser(Model model, @PathVariable(name ="id")Long id)throws Exception{
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        arenaService.challangeUser(id, currentUser.getId());
        model.addAttribute("user", currentUser);
        model.addAttribute("userList", userService.getAllUsers());
        return "arena/arena";
    }

    @GetMapping("/arenaDetail")
    public String arenaDetail(Model model) throws Exception {
        User currentUser = userService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("user", currentUser);
        model.addAttribute("arenaList", arenaService.getAllArenas());

        return "arena/arenaDetail";
    }
}
