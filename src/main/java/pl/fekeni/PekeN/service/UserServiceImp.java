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
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


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

    @Override
    public User getCurrentUser(String email){
        User currentUser = userRepository.findByEmail(email);
        return currentUser;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("El usuario para editar no existe."));
    }

    @Override
    public User updateUser(User fromUser) throws Exception {
        User toUser = getUserById(fromUser.getId());
        mapUser(fromUser, toUser);
        return userRepository.save(toUser);
    }

    @Override
    public void addStat(User fromUser, String stat) throws Exception{
        User toUser = getUserById(fromUser.getId());
        toUser.incrementStat(stat);

        mapUser(fromUser, toUser);
        userRepository.save(toUser);
    }

    /**
     * Map everythin but the password.
     * @param from
     * @param to
     */
    protected void mapUser(User from,User to) {
        to.setNickName(from.getNickName());
        to.setName(from.getName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setRoles(from.getRoles());

        to.setLvl(from.getLvl());
        to.setXp(from.getXp());
        to.setMoney(from.getMoney());
        to.setStrength(from.getStrength());

        to.setHealthPoints(from.getHealthPoints());
        to.setHealth(from.getXp());
        to.setCurrentHealth(from.getMoney());

        to.setDexterity(from.getLvl());
        to.setIntelligence(from.getXp());
        to.setArmor(from.getMoney());
        to.setDMG(from.getMoney());
    }

}