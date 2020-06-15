package pl.fekeni.PekeN.service;


import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.fekeni.PekeN.entity.Role;
import pl.fekeni.PekeN.entity.User;
import pl.fekeni.PekeN.repository.RoleRepository;
import pl.fekeni.PekeN.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        user.setLvl(1);
        user.setXp(0);
        user.setMoney(100);
        user.setStrength(5);
        user.setDexterity(5);
        user.setIntelligence(5);
        user.setHealth(5);
        user.setHealthPoints(100);
        user.setCurrentHealth(100);
        user.setArmor(20);
        user.setDMG(5);
        userRepository.save(user);
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {
        boolean isUserAlreadyExists = false;
        User existingUser = userRepository.findByEmail(user.getEmail());
        // If user is found in database, then then user already exists.
        if(existingUser != null){
            isUserAlreadyExists = true;
        }
        return isUserAlreadyExists;
    }

}