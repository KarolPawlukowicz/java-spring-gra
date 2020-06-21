package pl.fekeni.PekeN.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.fekeni.PekeN.entity.Arena;
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
    public Iterable<User> getAllByOrderByLvlDesc(){
        return userRepository.findAllByOrderByLvlDesc();
    }


    @Override
    public void saveUser(User user) {
        Long challange = -1l;
        int statistic = 5 , healthPoints = 100, money = 100, dmg = 5, armor = 20;

        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        user.setArmor(armor);
        user.setDMG(dmg);
        user.setLvl(1);
        user.setXp(0);
        user.setMoney(money);
        user.setStrength(statistic);
        user.setDexterity(statistic);
        user.setIntelligence(statistic);
        user.setHealth(statistic);
        user.setHealthPoints(healthPoints);
        user.setCurrentHealth(healthPoints);
        user.setChallange(challange);

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
        return userRepository.findById(id).orElseThrow(() -> new Exception("Uzytkownik nie istnieje"));
    }

    @Override
    public User updateUser(User fromUser) throws Exception {
        User toUser = getUserById(fromUser.getId());
        mapUser(fromUser, toUser);
        return userRepository.save(toUser);
    }

    @Override
    public void addStat(User fromUser, String stat) throws Exception{
        fromUser.incrementStat(stat);
        fromUser.decreaseGold(5);
        userRepository.save(fromUser);
    }

    @Override
    public int fight(User userMe, User userEnemy) throws Exception{
        int fightResult = 0;

        if(userMe.getDMG() > userEnemy.getDMG()){
            fightResult++;
        }
        if(userMe.getArmor() > userEnemy.getArmor()){
            fightResult++;
        }
        if(userMe.getCurrentHealth() > userEnemy.getCurrentHealth()){
            fightResult++;
        }
        Long newChallange = -1l;
        userMe.setChallange(newChallange);

        User toUser = getUserById(userMe.getId());

        mapUser(userMe, toUser);
        userRepository.save(toUser);

        System.out.println("walcze service wynik walki: " + fightResult);

        return fightResult;
    }


    @Override
    public void work(User fromUser, String workType) throws Exception {
        int howMuch = 0;
        if(workType.equals("kowal")){
            howMuch = 100;
        } else if(workType.equals("stajenny")){
            howMuch = 200;
        }
        fromUser.increaseGold(howMuch);
        userRepository.save(fromUser);
    }


    @Override
    public void attack(User fromUser, String monsterType) throws Exception {
        int increaseGold = 0, decreaseHP = 0, increaseExp = 0;
        if(monsterType.equals("dzikiPies")){
            increaseGold = 100;
            decreaseHP = 10;
            increaseExp = 1;
        } else if(monsterType.equals("alfaWilk")){
            increaseGold = 200;
            decreaseHP = 20;
            increaseExp = 2;
        }
        fromUser.increaseGold(increaseGold);
        fromUser.decreaseHP(decreaseHP);
        fromUser.increaseExp(increaseExp);

        userRepository.save(fromUser);
    }

    @Override
    public void updateHpAllUsers(){
        Iterable<User> userList = this.getAllUsers();

        for(User u: userList){
            if(u.getCurrentHealth() <= u.getHealthPoints()) {
                u.increaseHP(10);
                userRepository.save(u);
            }
            if(u.getCurrentHealth() > u.getHealthPoints()){
                u.setCurrentHealth(u.getHealthPoints());
                userRepository.save(u);
            }
        }

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
        to.setHealth(from.getHealth());
        to.setCurrentHealth(from.getCurrentHealth());

        to.setDexterity(from.getDexterity());
        to.setIntelligence(from.getIntelligence());
        to.setArmor(from.getArmor());
        to.setDMG(from.getDMG());
    }

}