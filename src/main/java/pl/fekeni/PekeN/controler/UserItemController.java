package pl.fekeni.PekeN.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.fekeni.PekeN.entity.User;
import pl.fekeni.PekeN.repository.UserItemRespository;
import pl.fekeni.PekeN.service.ItemUserService;
import pl.fekeni.PekeN.service.UserService;

@Controller
public class UserItemController {

    @GetMapping("/kupPrzedmiot/{id}")
    public String kupPrzedmiot(Model model, @PathVariable(name ="id")Long itemId)throws Exception{
      //  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      //  String currentUserEmail = authentication.getName();
      //  User user = userService.getCurrentUser(currentUserEmail);
       // System.out.println("from: " + user.getNickName());



        return "home";
    }
}
