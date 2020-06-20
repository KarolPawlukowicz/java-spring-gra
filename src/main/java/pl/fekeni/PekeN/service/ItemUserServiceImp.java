package pl.fekeni.PekeN.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.fekeni.PekeN.entity.User;
import pl.fekeni.PekeN.entity.UserItem;
import pl.fekeni.PekeN.repository.UserItemRespository;
import pl.fekeni.PekeN.repository.UserRepository;

public class ItemUserServiceImp implements ItemUserService {

    @Autowired
    UserItemRespository userItemRespository;

    @Override
    public void addPurchase(Long userId, Long itemId){
        UserItem userItem = new UserItem();
        userItem.setAuthUserId(userId);
        userItem.setItemId(itemId);

        userItemRespository.save(userItem);
    }
}
