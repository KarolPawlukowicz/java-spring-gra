package pl.fekeni.PekeN.service;

import pl.fekeni.PekeN.entity.User;

public interface UserService {
    public Iterable<User> getAllByOrderByLvlDesc();

    public void saveUser(User user);

    public void addStat(User user, String stat) throws Exception;

    public boolean isUserAlreadyPresent(User user);

    public Iterable<User> getAllUsers();

    public User getCurrentUser(String email);

    public User getUserById(Long id) throws Exception;

    public User updateUser(User user) throws Exception;

    public void work(User fromUser, String workType) throws Exception;

    public void attack(User fromUser, String monsterType) throws Exception;

    public void updateHpAllUsers();

    public int fight(User userMe, User userEnemy) throws Exception;
}
