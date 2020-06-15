package pl.fekeni.PekeN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fekeni.PekeN.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
}