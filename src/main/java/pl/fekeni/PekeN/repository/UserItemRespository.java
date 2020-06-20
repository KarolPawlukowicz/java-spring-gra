package pl.fekeni.PekeN.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fekeni.PekeN.entity.UserItem;

@Repository
public interface UserItemRespository extends CrudRepository<UserItem, Long> {
}
