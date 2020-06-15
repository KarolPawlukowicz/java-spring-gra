package pl.fekeni.PekeN.service;

import pl.fekeni.PekeN.entity.User;

public interface UserService {

    public void saveUser(User user);

    public boolean isUserAlreadyPresent(User user);
}
