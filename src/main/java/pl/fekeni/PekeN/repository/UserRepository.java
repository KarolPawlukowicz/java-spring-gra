package pl.fekeni.PekeN.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fekeni.PekeN.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByEmail(String email);

    public Iterable<User> findAllByOrderByLvlDesc();
}