package pl.fekeni.PekeN.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.fekeni.PekeN.entity.User;
import pl.fekeni.PekeN.service.UserService;

@Component
public class HealthPointsUpdate {

    @Autowired
    UserService userService;

    @Scheduled(fixedRate = 60000)
    public void udateHP() {
        userService.updateHpAllUsers();
    }
}
