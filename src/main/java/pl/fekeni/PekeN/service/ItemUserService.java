package pl.fekeni.PekeN.service;

import pl.fekeni.PekeN.entity.User;

public interface ItemUserService {
     public void addPurchase(Long userId, Long itemId);
}
